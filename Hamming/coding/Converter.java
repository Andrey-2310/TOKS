package coding;

import com.google.common.collect.Lists;
import exception.InvalidByteListException;

import java.util.*;

public class Converter {
    List<Integer> fromStringToBitList(String data) {
        List<Integer> stringBits = new ArrayList<>();
        for (byte nextByte : data.getBytes()) {
            List<Integer> bitListForCurrentByte = gettingBitsFromNumber(nextByte);
            completeBitsListToByte(bitListForCurrentByte);
            stringBits.addAll(bitListForCurrentByte);
        }
        return stringBits;
    }

    String fromBitListToString(List<Integer> stringBits) {
        if (stringBits.size() % Byte.SIZE != 0) try {
            throw new InvalidByteListException("the length of list is not correct");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int amountOfBytes = stringBits.size() / Byte.SIZE;
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < amountOfBytes; i++) {
            List<Integer> nextByte = stringBits.subList(i * Byte.SIZE, (i + 1) * Byte.SIZE );
            resultString.append(Character.toString((char) gettingNumberFromBitsList(nextByte)));
        }
        return new String(resultString);
    }

    public List<Integer> gettingBitsFromNumber(byte number) {
        List<Integer> bitListForCurrentByte = new ArrayList<>();
        int powerOfTwo = 0;
        while (number / Math.pow(2, powerOfTwo) >= 1) {
            powerOfTwo++;
        }
        for (powerOfTwo -= 1; powerOfTwo >= 0; powerOfTwo--) {
            if (number / (int) Math.pow(2, powerOfTwo) >= 1) {
                bitListForCurrentByte.add(1);
                number -= Math.pow(2, powerOfTwo);
            } else bitListForCurrentByte.add(0);
        }
        return bitListForCurrentByte;
    }

    private byte gettingNumberFromBitsList(List<Integer> bitsList) {
        byte curByte = 0;
        Collections.reverse(bitsList);
        for (int i = 0; i < bitsList.size(); i++) {
            if (bitsList.get(i).equals(1))
                curByte += Math.pow(2, i);
        }
        return curByte;
    }

    private void completeBitsListToByte(List<Integer> bitListForCurrentByte) {
        bitListForCurrentByte.addAll(0, new ArrayList<>(
                Collections.nCopies(Byte.SIZE - bitListForCurrentByte.size(), 0)));
    }

    public List<Integer> fromBitStringToBitList(String bitString){
        List<Integer> bitList = new ArrayList<>();
        for(int i = 0; i< bitString.length(); i++){
            bitList.add(Integer.parseInt(bitString.substring(i, i+1)));
        }
        return bitList;
    }
    public String fromBitListToBitString(List<Integer> bitList){
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer nextBit: bitList){
            stringBuilder.append(nextBit.toString());
        }
        return new String(stringBuilder);
    }
}
