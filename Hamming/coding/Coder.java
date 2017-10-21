package coding;

import java.util.List;

public interface Coder {
    List<Integer> encode(String data);
    String decode(List<Integer> data);
}
