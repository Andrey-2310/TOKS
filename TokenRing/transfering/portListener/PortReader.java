package transfering.portListener;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import transfering.ComChat;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class PortReader implements SerialPortEventListener {

    private ComChat chat;

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                System.out.println(event.getEventValue()+ ": " + chat.getInputPort().getPortName());
                chat.packageManaging(chat.getInputPort().readString(event.getEventValue()));
            } catch (SerialPortException ex) {
                ex.printStackTrace();
            }
        }
    }
}
