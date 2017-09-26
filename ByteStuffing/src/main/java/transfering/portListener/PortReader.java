package transfering.portListener;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import lombok.AllArgsConstructor;
import transfering.ComPort;

import java.util.ArrayList;
import java.util.Arrays;

@AllArgsConstructor
public class PortReader implements SerialPortEventListener {

    private ComPort port;

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                String readString =  port.getPort().readString(event.getEventValue());
                port.setPacket(port.getStuffing().decode(new ArrayList<>(Arrays.asList(port.toObject(
                      readString.getBytes())))));
                if (port.getPort().getInputBufferBytesCount() > 0) {
                    throw new SerialPortException(port.getPort().getPortName(), "serialEvent", "There are unread byte(s)");
                }
            } catch (SerialPortException ex) {
                ex.printStackTrace();
            }
        }
    }
}
