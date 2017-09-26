package transfering.packaging.stuffing;

import transfering.packaging.PackagingAssistant;
import java.util.List;

public class ByteStuffing implements Stuffing {

    private final PackagingAssistant packagingAssistant = new PackagingAssistant();

    @Override
    public List<Byte> encode( final List<Byte> packet) {
        packagingAssistant.checkCorrectPacket("Encoding", packet);
        List<Byte> data = packagingAssistant.unpack(packet);
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).equals(packagingAssistant.getStartByte())) {
                data.remove(i);
                data.addAll(i, packagingAssistant.getAddToDataBytes());
            }
        }
        List<Byte> encodedPacket = packagingAssistant.pack(data);
        encodedPacket.add(packagingAssistant.getAddEndByte());
        return encodedPacket;
    }

    @Override
    public List<Byte> decode(List<Byte> packet) {
        packagingAssistant.checkCorrectPacket("Decoding", packet);
        // Deleting esc-symbol
        List<Byte> decodedPacket =packet.subList(0, packet.size()-1);
        List<Byte> data = packagingAssistant.unpack(decodedPacket);
        for (int i = 0; i < data.size() - 1; i++) {
            if (data.get(i).equals(packagingAssistant.getAddToDataBytes().get(0)) &&
                    data.get(i+1).equals(packagingAssistant.getAddToDataBytes().get(1))){
                data.remove(i+1);
                data.set(i, packagingAssistant.getStartByte());
            }
        }
        return packagingAssistant.pack(data);
    }


}
