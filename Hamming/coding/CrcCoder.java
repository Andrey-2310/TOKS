package coding;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CrcCoder implements Coder {
    Converter converter = new Converter();

    @Override
    public List<Integer> encode(String data) {
        getListOfCodedBits((byte) data.charAt(0));
        return Collections.emptyList();
    }

    @Override
    public String decode(List<Integer> data) {
        return null;
    }

    private List<Integer> getListOfCodedBits(byte data) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter interval bits: \nFirst value: ");
        int firstValue = scanner.nextInt();
        System.out.println("Second value: ");
        int secondValue = scanner.nextInt();
        List<Integer> bitsList = converter.gettingBitsFromNumber(data);
        if (firstValue < bitsList.size() && secondValue < bitsList.size()) {
            if (secondValue < firstValue) {
                return Lists.reverse(bitsList.subList(secondValue, firstValue));
            }
            return bitsList.subList(firstValue, secondValue);
        }
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "CrcCoder";
    }
}
