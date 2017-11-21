package enumeration;

import jssc.SerialPort;
import lombok.Getter;

@Getter
public enum Parity {
    NONE("NONE", SerialPort.PARITY_NONE),
    ODD("ODD", SerialPort.PARITY_ODD),
    EVEN("EVEN", SerialPort.PARITY_EVEN);

    private String name;
    private int amountsOfBits;

    Parity(String name, int amountOfBits) {
        this.name = name;
        this.amountsOfBits = amountOfBits;
    }
}
