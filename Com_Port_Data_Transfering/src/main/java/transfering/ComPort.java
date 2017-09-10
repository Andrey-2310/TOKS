package transfering;

import com.mifmif.common.regex.Generex;
import enumeration.Parity;
import jssc.*;
import lombok.Setter;
import lombok.ToString;
import transfering.portListener.PortReader;
import ui.dataListener.DataListener;


@ToString
@lombok.Getter
@Setter
public class ComPort {

    private SerialPort port;
    private int portSpeed;
    private Parity portParity;
    private String data;

    private DataListener dataListener;
    private final Generex generex = new Generex("[a-zA-Z0-9]{3,10}");

    public ComPort(String portName) {
        port = new SerialPort(portName);
        portSpeed = SerialPort.BAUDRATE_9600;
        portParity = Parity.NONE;
        openPort();
    }

    public void setData(String data) {
        this.data = data;
        dataListener.update(data);
    }

    public ComPort setDataListener(DataListener dataListener) {
        this.dataListener = dataListener;
        return this;
    }

    private void openPort() {
        try {
            this.port.openPort();
            setParams();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    private void setParams() {
        try {
            this.port.setParams(portSpeed,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    portParity.getAmountsOfBits());
            this.port.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            this.port.addEventListener(new PortReader(this), SerialPort.MASK_RXCHAR);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }


    public void closePort() {
        try {
            this.port.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void sendBytes() {
        try {
            this.port.writeBytes(data.getBytes());
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void generateData() {
        setData(generex.random(1, 10));
    }

}

