package transfering.packaging.stuffing;

import java.util.List;

public interface Stuffing {
    List<Byte> encode(List<Byte> data);
    List<Byte> decode(List<Byte> data);
}
