package execution.managing;

import coding.Coder;
import coding.Converter;
import coding.CrcCoder;
import coding.HemmingCoder;
import exception.IncorrectIndexException;

import java.util.*;

public class Manager {

    private Optional<Coder> coder;
    private String encodedData;
    private Scanner scanner;
    private Converter converter;

    public Manager() {
        this.coder = Optional.empty();
        this.encodedData = "";
        this.scanner = new Scanner(System.in);
        this.converter = new Converter();
    }

    public void managingCoding() {
        System.out.println("1) Set coder    2) Get coder\n3) Encode string 4) Decode string");
        switch (scanner.nextInt()) {
            case 1:
                setCoder();
                break;
            case 2:
                getCoder();
                break;
            case 3:
                System.out.println("Enter string to encode");
                coder.ifPresent(coder -> encodedData = converter.fromBitListToBitString(coder.encode(scanner.next())));
                System.out.println(encodedData + " - encoded data");
                break;
            case 4:
                System.out.println("Enter string to decode");
                coder.ifPresent(coder -> System.out.println(coder.decode(converter.fromBitStringToBitList(scanner.next()))
                        + " - decoded data"));
                break;
            default:
                managingCoding();
        }
        managingCoding();
    }

    private void setCoder() {
        System.out.println("1) Hemming Coder\n2) CRC Coder");
        try {
            switch (scanner.nextInt()) {
                case 1:
                    coder = Optional.of(new HemmingCoder());
                    break;
                case 2:
                    coder = Optional.of(new CrcCoder());
                    break;
                default:
                    throw new IncorrectIndexException();
            }
        } catch (IncorrectIndexException e) {
            e.printStackTrace();
        }
        encodedData = "";
    }


    private void getCoder() {
        if (coder.isPresent())
            System.out.println(coder.toString());
        else System.out.println("Coder has null value");
    }
}
