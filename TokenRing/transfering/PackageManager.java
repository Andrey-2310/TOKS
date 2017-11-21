package transfering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PackageManager {
    private final Byte SD = new Byte(hexToIntString("7E"));
    private final Byte ED = new Byte(hexToIntString("7D"));
    private final Byte TOKEN = new Byte(hexToIntString("01"));
    private final Byte FRAME = new Byte(hexToIntString("10"));

    Byte[] toPackage(Byte[] data, Byte source, Byte destination) {
        List<Byte> pack = new ArrayList<>(Arrays.asList(SD, FRAME, source, destination));
        pack.addAll(new ArrayList<>(Arrays.asList(data)));
        pack.add(ED);
        return pack.toArray(new Byte[pack.size()]);
    }

    public Byte[] getTOKEN() {
        return new Byte[]{SD, TOKEN, ED};
    }

    boolean isToken(Byte[] pack) {
        return pack[1].equals(TOKEN);
    }

    boolean isFrame(Byte[] pack) {
        return pack[1].equals(FRAME);
    }

    Byte getSource(Byte[] pack){
        return pack[2];
    }

     Byte getDestination(Byte[] pack){
        return pack[3];
    }

     Byte[] getData(Byte[] pack){
        return Arrays.asList(pack).subList(4, pack.length-1).toArray(new Byte[pack.length-5]);
    }

    String getData(String pack){
         return pack.substring(4, pack.length()-1);
    }

    private String hexToIntString(String hex) {
        return String.valueOf(Integer.parseInt(hex, 16));
    }

}
