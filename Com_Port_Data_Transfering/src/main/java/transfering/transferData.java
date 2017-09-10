package transfering;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class transferData {
    public static void main(String[] args) {

        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
        }
        //В конструктор класса передаём имя порта с которым мы будем работать
        SerialPort serialPort = new SerialPort("COM1");

        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры. Можно использовать и такую строку serialPort.setParams(9600, 8, 1, 0);
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Записываем данные в порт
            serialPort.writeBytes("Test".getBytes());
            //Читаем данные в количестве 10 байт. Будте внимательны с методом readBytes(), если во входном буфере
            //будет меньше байт, то метод будет ожидать нужного количества. Лучше использовать его совместно с
            //интерфейсом SerialPortEventListener.
            byte[] buffer = serialPort.readBytes(10);
            //Закрываем порт
            System.out.println(buffer);
            serialPort.closePort();
        } catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }
}
