package ui;

import javafx.beans.value.ChangeListener;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;
import ui.PortInterface.PortInterface;

import javax.sound.sampled.Port;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ModuleController {
    static void initializeActions(PortInterface firstPort, PortInterface secondPort) {
       initializeOneSide(firstPort, secondPort);
       initializeOneSide(secondPort, firstPort);
    }

    private static void initializeOneSide(PortInterface firstPort, PortInterface secondPort){
        initializeGenerateNewBiteButton(firstPort);
        initializeSendButton(firstPort, secondPort);
        changeSpeed(firstPort);
        setOnChangeListener(firstPort);
    }


    private static void initializeGenerateNewBiteButton(PortInterface port) {
        port.getGenerateNewByteButton().setOnAction((event) -> {
            port.getComPort().generateData();
            port.getCurrentData().setText(port.getComPort().getData());
        });
    }

    private static void initializeSendButton(PortInterface firstPort, PortInterface secondPort) {
        firstPort.getSendButton().setOnAction(event ->
            firstPort.getComPort().sendBytes());
    }

    private static void changeSpeed(PortInterface port) {
        port.getSpeedComboBox().setOnAction(event -> {
            port.getComPort().setPortSpeed(
                    Integer.parseInt(port.getSpeedComboBox().getSelectionModel().getSelectedItem().toString()));
        });
    }
    private static void setOnChangeListener(PortInterface port){
        port.getCurrentData().textProperty().addListener((observable, oldValue, newValue) -> {
           port.getComPort().setData(newValue);
        });
    }
}
