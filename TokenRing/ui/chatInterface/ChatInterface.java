package ui.chatInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;
import transfering.ComChat;
import ui.dataListener.CurrentDataListener;
import ui.dataListener.TempDataListener;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
public class ChatInterface {
    private ComChat chat;
    private String destination;
    private Pane layer;

    private Image computerImage;
    private ImageView computerBackground;

    private Image tokenImage;
    private ImageView tokenBackground;

    private Image startTokenImage;
    private ImageView startTokenBackground;

    private TextArea currentData;
    private Button generateNewByteButton;
    private Button cleanButton;
    private TextArea lastCameData;
    private TextArea dataToAdd;
    private Button addToBufferButton;
    private ComboBox<String> destinationComboBox;

    public ChatInterface(String inputPortName, String outputPortName) {
        this.chat = new ComChat(inputPortName, outputPortName).addDataListener(new ArrayList<>(Arrays.asList(
                new TempDataListener(this), new CurrentDataListener(this))));

        this.computerImage = new Image("computer.png", 225.0, 175.0, false, true);
        this.computerBackground = new ImageView(this.computerImage);

        this.tokenImage = new Image("lamp1.png", 50.0, 50.0, false, true);
        this.tokenBackground = new ImageView(this.tokenImage);

        if (chat.isManager()) {
        this.startTokenImage = new Image("redButton.png", 50.0, 50.0, false, true);
        this.startTokenBackground = new ImageView(this.startTokenImage);
        }

        this.currentData = new TextArea();
        this.generateNewByteButton = new Button("Generate new string");
        this.cleanButton = new Button("Clear");
        this.lastCameData = new TextArea();
        this.dataToAdd = new TextArea();
        this.addToBufferButton = new Button("Add to buffer");

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "COM1",
                        "COM2",
                        "COM3",
                        "COM4");

        this.destinationComboBox = new ComboBox<>(options);
        createGui();
    }

    private void createGui() {
        this.computerBackground.setLayoutY(51.0);
        this.computerBackground.setLayoutX(45.0);

        this.tokenBackground.setLayoutY(245);
        this.tokenBackground.setLayoutX(470);
        this.tokenBackground.setVisible(false);

        if (chat.isManager()) {
        this.startTokenBackground.setLayoutY(245);
        this.startTokenBackground.setLayoutX(470);
        }

        this.currentData.setMaxSize(211.0, 130.0);
        this.currentData.setFont(new Font(20));
        this.currentData.setLayoutY(59.0);
        this.currentData.setLayoutX(53.0);

        this.lastCameData.setMaxSize(211.0, 130.0);
        this.lastCameData.setFont(new Font(20));
        this.lastCameData.setLayoutY(59.0);
        this.lastCameData.setLayoutX(303.0);

        this.dataToAdd.setMaxSize(320.0, 30.0);
        this.dataToAdd.setFont(new Font(12));
        this.dataToAdd.setLayoutY(250.0);
        this.dataToAdd.setLayoutX(43.0);

        this.generateNewByteButton.setPrefSize(148.0, 25.0);
        this.generateNewByteButton.setLayoutY(300.0);
        this.generateNewByteButton.setLayoutX(42.0);

        this.cleanButton.setPrefSize(60.0, 25.0);
        this.cleanButton.setLayoutY(300.0);
        this.cleanButton.setLayoutX(190.0);

        this.addToBufferButton.setPrefSize(113.0, 25.0);
        this.addToBufferButton.setLayoutY(300.0);
        this.addToBufferButton.setLayoutX(250.0);

        this.destinationComboBox.setLayoutX(380.0);
        this.destinationComboBox.setLayoutY(255.0);

        this.layer = new Pane();
    }

    public Parent getLayerCustom() {
        this.layer.getChildren().addAll(
                generateNewByteButton,
                computerBackground,
                tokenBackground,
                currentData,
                cleanButton,
                lastCameData,
                dataToAdd,
                addToBufferButton,
                destinationComboBox
        );
        if (chat.isManager()) this.layer.getChildren().add(startTokenBackground);
        return this.layer;
    }
}
