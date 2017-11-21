package transfering;

import com.mifmif.common.regex.Generex;
import jssc.*;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;
import transfering.portListener.PortReader;
import ui.dataListener.DataListener;

import java.util.ArrayList;
import java.util.List;


@ToString
@lombok.Getter
@Setter
public class ComChat {

    private SerialPort inputPort;
    private SerialPort outputPort;
    private Byte basis;
    private boolean isManager;
    private Byte[] currentData;
    private Byte[] tempData;
    private List<Byte[]> buffer;
    private List<DataListener> dataListeners;
    private PackageManager packageManager;

    private final Generex generex = new Generex("[a-zA-Z0-9]{3,10}");
    private final String MANAGER_NAME = "COM1";

    public ComChat(String inputPortName, String outputPortName) {
        inputPort = new SerialPort(inputPortName);
        outputPort = new SerialPort(outputPortName);
        isManager = inputPort.getPortName().equals(MANAGER_NAME);
        buffer = new ArrayList<>();
        dataListeners = new ArrayList<>();
        packageManager = new PackageManager();
        basis = createBasis(inputPort.getPortName());
        connect();
    }

    private void connect() {
        if (!inputPort.isOpened()) installPort(inputPort);
        if (!outputPort.isOpened()) installPort(outputPort);
        try {
            inputPort.addEventListener(new PortReader(this), SerialPort.MASK_RXCHAR);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    private void installPort(SerialPort port) {
        openPort(port);
        setParams(port);
    }

    private void openPort(SerialPort port) {
        try {
            port.openPort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    private void setParams(SerialPort port) {
        try {
            port.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            port.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentData(String pack) {
        this.currentData = this.tempData;
        dataListeners.get(1).update(packageManager.getData(pack));
    }

    public void setTempData(String pack) {
        this.tempData = packageManager.getData(toObject(pack.getBytes()));
        dataListeners.get(0).update(packageManager.getData(pack));
    }

    public ComChat addDataListener(List<DataListener> dataListeners) {
        this.dataListeners.addAll(dataListeners);
        return this;
    }

    public void disconnect() {
        try {
            inputPort.closePort();
            outputPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void packageManaging(String pack) {
        chatDelay();
        Byte[] tempPack = toObject(pack.getBytes());
        if (packageManager.isToken(tempPack)) {
            if (!buffer.isEmpty()) {
                sendBytes(buffer.get(0));
                buffer.remove(0);
            } else {
                sendBytes(tempPack);
            }
        } else if (packageManager.isFrame(tempPack)) {
            setTempData(pack);
            if (packageManager.getSource(tempPack).equals(basis)) {
                sendBytes(packageManager.getTOKEN());
            } else {
                sendBytes(tempPack);
            }
            if (packageManager.getDestination(tempPack).equals(basis)) {
                setCurrentData(pack);
            }
        }
    }

    public void sendBytes(Byte[] data) {
        try {
            outputPort.writeBytes(toPrimitive(data));
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void addToBuffer(String data, String destination) {
        buffer.add(packageManager.toPackage(toObject(data.getBytes()), basis, createBasis(destination)));
    }

    public String generateData() {
        return generex.random(1, 10);
    }

    private Byte createBasis(String portName) {
        return (byte) portName.charAt(3);
    }

    public Byte[] toObject(byte[] bytesPrim) {
        return ArrayUtils.toObject(bytesPrim);
    }

    public byte[] toPrimitive(Byte[] bytesObj) {
        return ArrayUtils.toPrimitive(bytesObj);
    }

    private void chatDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

