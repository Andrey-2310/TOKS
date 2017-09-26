package transfering.packaging;

import jssc.SerialPortException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class PackagingAssistant {

    private final Byte startByte = new Byte(hexToIntString("7E"));

    private final List<Byte> addToDataBytes = new ArrayList<>(
            Arrays.asList(new Byte(hexToIntString("7D")), new Byte(hexToIntString("5E"))));

    private final Byte endByte = new Byte(hexToIntString("7D"));

    private final Byte addEndByte = new Byte(hexToIntString("5D"));

    public List<Byte> pack(List<Byte> data) {
        data.add(0, startByte);
        data.add(endByte);
        return data;
    }

    public List<Byte> unpack (List<Byte> data) {
        return data.subList(1, data.size() - 1);
    }

    private String hexToIntString(String hex) {
        return String.valueOf(Integer.parseInt(hex, 16));
    }

    public void checkCorrectPacket(String method, List<Byte> localData) {
        try {
            if (!localData.get(0).equals(startByte)) {
                throw new SerialPortException("", method, "package isn't constructed correctly");
            }
            switch (method) {
                case "Decoding":
                    if (!localData.get(localData.size() - 2).equals(endByte) ||
                            !localData.get(localData.size() - 1).equals(addEndByte)) {
                        throw new SerialPortException("", method, "package isn't constructed correctly");
                    }
                    break;
                case "Encoding":
                    if (!localData.get(localData.size() - 1).equals(endByte)) {
                        throw new SerialPortException("", method, "package isn't constructed correctly");
                    }
                    break;
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}
