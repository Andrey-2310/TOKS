package ui.PortInterface;

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
    private ComboBox speedComboBox;
    private CheckBox parityCheckBox;
    private Button sendButton;

    public PortInterface(String comPortName) {
        this.comPort = new ComPort(comPortName).setDataListener(new DataListenerImpl(this));
        this.computerImage = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQDJ2hWgbwBfhQPRGaWSeKZkVGI4MroV2yqJf_AsS8OCaieY7km",
                200.0, 150.0, false, true);
        this.computerBackground = new ImageView(this.computerImage);
        this.currentData = new TextArea();
        this.generateNewByteButton = new Button("Generate new string");
        this.speedComboBox = new ComboBox();
        this.parityCheckBox = new CheckBox("Parity");
        this.sendButton = new Button("Send");
        creatingGui();
    }

    private void creatingGui() {
        this.computerBackground.setLayoutY(51.0);

        this.currentData.setMaxSize(186.0, 110.0);
        this.currentData.setFont(new Font(20));
        this.currentData.setLayoutY(59.0);


        this.generateNewByteButton.setPrefSize(201.0, 25.0);
        this.generateNewByteButton.setLayoutY(209.0);

        this.speedComboBox.getItems().addAll(
                300, 1200, 2400, 4800,
                9600, 19200, 38400, 57600
        );
        this.speedComboBox.setPrefSize(86.0, 25.0);
        this.speedComboBox.setLayoutY(283.0);

        this.parityCheckBox.setPrefSize(73.0, 25.0);
        this.parityCheckBox.setLayoutY(283.0);
        this.parityCheckBox.setSelected(false);


        this.sendButton.setPrefSize(54.0, 25.0);
        this.sendButton.setLayoutY(283.0);

        this.layer= new Pane();
    }

    public PortInterface concreteFixing(double offset) {
        this.sendButton.setLayoutX(219.0 + offset);
        this.speedComboBox.setLayoutX(118.0 + offset);
        this.parityCheckBox.setLayoutX(29.0 + offset);
        this.generateNewByteButton.setLayoutX(45.0+ offset);
        this.currentData.setLayoutX(54.0 + offset);
        this.computerBackground.setLayoutX(46.0 + offset);
        return this;
    }

    public Parent getLayerCustom(){
        this.layer.getChildren().addAll(
                parityCheckBox,
                speedComboBox,
                sendButton,
                generateNewByteButton,
                computerBackground,
                currentData
                );
        return this.layer;
    }
}
