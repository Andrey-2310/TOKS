package transfering.portListener;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import lombok.AllArgsConstructor;
import transfering.ComPort;

@AllArgsConstructor
public class PortReader implements SerialPortEventListener {

    private ComPort port;

    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                //Получаем ответ от устройства, обрабатываем данные и т.д.
                port.setData(port.getPort().readString(event.getEventValue()));
            } catch (SerialPortException ex) {
                ex.printStackTrace();
            }
        }
    }
}
