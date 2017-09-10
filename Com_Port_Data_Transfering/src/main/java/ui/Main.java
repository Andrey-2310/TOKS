package ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import lombok.Getter;
import ui.PortInterface.PortInterface;
import ui.PortInterface.PortInterfaceFactory;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class Main extends Application {

    private PortInterface firstPort;
    private PortInterface secondPort;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        PortInterfaceFactory.fulfillMapPort();
        firstPort = PortInterfaceFactory.INTERFACE_FACTORY.getPortInterface("COM1");
        secondPort = PortInterfaceFactory.INTERFACE_FACTORY.getPortInterface("COM2");
        ModuleController.initializeActions(new ArrayList<>(Arrays.asList(firstPort, secondPort)));
        initializeStage(primaryStage);
    }

    private void initializeStage(Stage primaryStage){
        Scene scene = new Scene(createWindow());
        primaryStage.setTitle("COM Ports chat program");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> {
            firstPort.getComPort().closePort();
            secondPort.getComPort().closePort();
        });
        primaryStage.show();
    }
    private Parent createWindow() {
        Pane root = new Pane();
        root.setPrefSize(600, 350);
        root.getChildren().add(secondPort.getLayerCustom());
        root.getChildren().add(firstPort.getLayerCustom());
        root.getChildren().add(createSeparator());
        return root;
    }

    private Line createSeparator() {
        Line separator = new Line();
        separator.setLayoutX(300);
        separator.setEndY(0);
        separator.setStartY(420);
        separator.setVisible(true);
        return separator;
    }
}
