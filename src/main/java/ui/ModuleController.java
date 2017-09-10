package ui;


import ui.PortInterface.PortInterface;

import java.util.List;

public class ModuleController {
    static void initializeActions(List<PortInterface> ports) {
        for (PortInterface port : ports) {
            initializeOneSide(port);
        }
    }

    private static void initializeOneSide(PortInterface port) {
        initializeGenerateNewStringButton(port);
        initializeClearButton(port);
        initializeSendButton(port);
        changeSpeed(port);
        changeParity(port);
        setOnChangeListener(port);
    }

    private static  void initializeClearButton(PortInterface port){
        port.getCleanButton().setOnAction(event -> {
            port.getComPort().setData("");
        });
    }

    private static void initializeGenerateNewStringButton(PortInterface port) {
        port.getGenerateNewByteButton().setOnAction((event) -> {
            port.getComPort().generateData();
        });
    }

    private static void initializeSendButton(PortInterface port) {
        port.getSendButton().setOnAction(event -> {
            port.getComPort().sendBytes();
        });

    }

    private static void changeParity(PortInterface port) {
        port.getParityComboBox().setOnAction(event -> {
            port.getComPort().setPortParity(port.getParityComboBox().getSelectionModel().getSelectedItem());
            System.out.println(port.getComPort().getPortParity().toString());
        });
    }

    private static void changeSpeed(PortInterface port) {
        port.getSpeedComboBox().setOnAction(event -> {
            port.getComPort().setPortSpeed(
                    Integer.parseInt(port.getSpeedComboBox().getSelectionModel().getSelectedItem().toString()));
        });
    }

    private static void setOnChangeListener(PortInterface port) {
        port.getCurrentData().textProperty().addListener((observable, oldValue, newValue) -> {
            port.getComPort().setData(newValue);
        });
    }
}
