package transfering;

import com.mifmif.common.regex.Generex;
import enumeration.Parity;
import jssc.*;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;
import transfering.packaging.PackagingAssistant;
import transfering.packaging.stuffing.ByteStuffingSc;
import transfering.portListener.PortReader;
import transfering.packaging.stuffing.ByteStuffing;
import transfering.packaging.stuffing.Stuffing;
import ui.dataListener.DataListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@ToString
@lombok.Getter
@Setter
public class ComPort {

    private SerialPort port;
    private int portSpeed;
    private Parity portParity;
    private List<Byte> packet;

    private DataListener dataListener;
    private final Generex generex = new Generex("[a-zA-Z0-9]{3,10}");
    private final Stuffing stuffing = new ByteStuffingSc();
    private final PackagingAssistant packagingAssistant = new PackagingAssistant();

    public ComPort(String portName) {
        port = new SerialPort(portName);
        portSpeed = SerialPort.BAUDRATE_9600;
        portParity = Parity.NONE;
        openPort();
    }

    public void setPacket(String data) {
        this.packet = packagingAssistant.pack(new ArrayList<>(Arrays.asList(toObject(data.getBytes()))));
        dataListener.update(data);
    }

    public void setPacket(List<Byte> packet) {
        this.packet = packet;
        List<Byte> data = packagingAssistant.unpack(packet);
        dataListener.update(new String(toPrimitive(data.toArray(new Byte[data.size()]))));
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
            List<Byte> encodedPacket = stuffing.encode(packet);
            this.port.writeBytes(toPrimitive(encodedPacket.toArray(new Byte[encodedPacket.size()])));
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void generateData() {
        setPacket(generex.random(1, 10));
    }

    public Byte[] toObject(byte[] bytesPrim) {
        return ArrayUtils.toObject(bytesPrim);
    }

    public byte[] toPrimitive(Byte[] bytesObj) {
        return ArrayUtils.toPrimitive(bytesObj);
    }
}

