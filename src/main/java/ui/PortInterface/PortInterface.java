package ui.PortInterface;

import enumeration.Parity;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;
import transfering.ComPort;
import ui.dataListener.DataListenerImpl;

@Getter
@Setter
public class PortInterface {
    private ComPort comPort;
    private Pane layer;

    private ImageView computerBackground;
    private Image computerImage;
    private TextArea currentData;
    private Button generateNewByteButton;
    private ComboBox<Integer> speedComboBox;
    private ComboBox<Parity> parityComboBox;
    private Button sendButton;
    private Button cleanButton;

    PortInterface(String comPortName) {
        this.comPort = new ComPort(comPortName).setDataListener(new DataListenerImpl(this));
        this.computerImage = new Image("computer.png",
                200.0, 150.0, false, true);
        this.computerBackground = new ImageView(this.computerImage);
        this.currentData = new TextArea();
        this.generateNewByteButton = new Button("Generate new string");
        this.speedComboBox = new ComboBox<>(FXCollections.observableArrayList(300, 1200, 2400, 4800,
                9600, 19200, 38400, 57600));
        this.parityComboBox = new ComboBox<>(FXCollections.observableArrayList(Parity.NONE, Parity.ODD, Parity.EVEN));
        this.sendButton = new Button("Send");
        this.cleanButton = new Button("Clear");
        creatingGui();
    }

    private void creatingGui() {
        this.computerBackground.setLayoutY(41.0);

        this.currentData.setMaxSize(186.0, 110.0);
        this.currentData.setFont(new Font(20));
        this.currentData.setLayoutY(49.0);


        this.generateNewByteButton.setPrefSize(170.0, 25.0);
        this.generateNewByteButton.setLayoutY(209.0);

        this.cleanButton.setPrefSize(60.0, 25.0);
        this.cleanButton.setLayoutY(209.0);

        this.speedComboBox.getSelectionModel().select(4);
        this.speedComboBox.setPrefSize(90.0, 25.0);
        this.speedComboBox.setLayoutY(283.0);

        this.parityComboBox.setPrefSize(80.0, 25.0);
        this.parityComboBox.setLayoutY(283.0);
        this.parityComboBox.getSelectionModel().select(0);


        this.sendButton.setPrefSize(60.0, 25.0);
        this.sendButton.setLayoutY(283.0);

        this.layer= new Pane();
    }

    public PortInterface concreteFixing(double offset) {
        this.sendButton.setLayoutX(210.0 + offset);
        this.speedComboBox.setLayoutX(120.0 + offset);
        this.parityComboBox.setLayoutX(40.0 + offset);
        this.generateNewByteButton.setLayoutX(40.0+ offset);
        this.currentData.setLayoutX(58.0 + offset);
        this.computerBackground.setLayoutX(50.0 + offset);
        this.cleanButton.setLayoutX(210.0 +offset);
        return this;
    }

    public Parent getLayerCustom(){
        this.layer.getChildren().addAll(
                parityComboBox,
                speedComboBox,
                sendButton,
                generateNewByteButton,
                computerBackground,
                currentData,
                cleanButton
                );
        return this.layer;
    }
}
