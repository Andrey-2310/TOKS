package coding;

import java.util.ArrayList;
import java.util.List;

public class HemmingCoder implements Coder {

    private final static Converter converter = new Converter();

    public List<Integer> encode(String stringToEncode) {
        List<Integer> data = converter.fromStringToBitList(stringToEncode);
        int amountOfControlBits = getAmountOfControlBits(data);
        insertControlBits(data, amountOfControlBits);
        calculateControlBits(data, amountOfControlBits);
        System.out.println(data+ ": Calculated list");
        return data;
    }

    public String decode(List<Integer> receivedData) {
        int amountOfControlBits = getAmountOfControlBits(receivedData);
        List<Integer> calculatedData = calculateControlBits(
                nullControlBits(receivedData, amountOfControlBits), amountOfControlBits);
        List<Integer> incorrectIndexes = comparison(receivedData, calculatedData, amountOfControlBits);
        if(!incorrectIndexes.isEmpty()) {
            correctingData(calculatedData, incorrectIndexes);
        }
        deleteControlBits(calculatedData, amountOfControlBits);
        return converter.fromBitListToString(calculatedData);
    }

    private int getAmountOfControlBits(List<Integer> data) {
        int amountOfControlBits = 0;
        while (data.size() / Math.pow(2, amountOfControlBits) >= 1) {
            amountOfControlBits++;
        }
        return amountOfControlBits;
    }

    private void insertControlBits(List<Integer> data, int amountOfControlBits) {
        for (int i = 0; i < amountOfControlBits; i++) {
            data.add((int) Math.pow(2, i) - 1, 0);
        }
    }

    private void deleteControlBits(List<Integer> data, int amountOfControlBits) {
        for(int i=amountOfControlBits-1; i>=0; i--){
            data.remove((int)Math.pow(2, i)-1);
        }
    }

    private List<Integer> nullControlBits(final List<Integer> data, int amountOfControlBits) {
        List<Integer> nullControlBitsData = new ArrayList<>(data);
        for (int i = 0; i < amountOfControlBits; i++) {
            nullControlBitsData.set((int) Math.pow(2, i) - 1, 0);
        }
        return nullControlBitsData;
    }

    private List<Integer> calculateControlBits(List<Integer> data, int amountOfControlBits) {
        for (int i = 0; i < amountOfControlBits; i++) {
            int N = (int) Math.pow(2, i);
            int amountOfOneBits = 0;
            for (int j = N - 1; j < data.size(); j += N) {
                for (int k = 1; k <= N; k++, j++) {
                    if (j < data.size())
                        if (data.get(j) == 1)
                            amountOfOneBits++;
                }
            }
            if (amountOfOneBits % 2 == 0) data.set(N - 1, 0);
            else data.set(N - 1, 1);
        }
        return data;
    }

    private List<Integer> comparison(List<Integer> receivedData, List<Integer> calculatedData, int amountOfControlBits) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < amountOfControlBits; i++) {
            int N = (int) Math.pow(2, i) - 1;
            if (!receivedData.get(N).equals(calculatedData.get(N))) {
                indexes.add(N);
            }
        }
        return indexes;
    }

    private void correctingData(List<Integer> calculatedData, List<Integer> incorrectIndexes) {
        for (Integer incorrectIndexe : incorrectIndexes) {
            if (calculatedData.get(incorrectIndexe) == 1) calculatedData.set(incorrectIndexe, 0);
            else calculatedData.set(incorrectIndexe, 1);
        }
        int badReceivedBitIndex = incorrectIndexes.stream().mapToInt(Integer::intValue).sum() + incorrectIndexes.size() - 1;
        if (calculatedData.get(badReceivedBitIndex) == 1) calculatedData.set(badReceivedBitIndex, 0);
        else calculatedData.set(badReceivedBitIndex, 1);
    }

    @Override
    public String toString() {
        return "HemmingCoder";
    }
}
