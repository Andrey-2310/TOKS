package transfering.portListener;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import transfering.ComPort;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class PortReader implements SerialPortEventListener {

    private ComPort port;

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                Byte readByte = port.getPort().readBytes(1)[0];
                if (Objects.equals(readByte, port.getEndByte())) {
                    port.setData(fromListToString(port.getBuffer()));
                    port.getBuffer().clear();
                } else if (Objects.equals(readByte, port.getJamByte())) {
                    port.getBuffer().remove(port.getBuffer().size()-1);
                } else port.getBuffer().add(readByte);
                if(!port.getBuffer().isEmpty()) {
                    System.out.println(port.getPort().getPortName() + ": " + fromListToString(port.getBuffer()));
                }
            } catch (SerialPortException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String fromListToString(List<Byte> buffer) {
        return new String(ArrayUtils.toPrimitive(buffer.toArray(new Byte[buffer.size()])));
    }
}
