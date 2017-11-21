package ui.chatInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;
import ui.ModuleController;

import java.util.Objects;

@Getter
public class SettingsInterface {
    @Setter
    private String inputPortValue;
    @Setter
    private String outputPortValue;

    private Pane layer;
    private Button readyButton;
    private ComboBox<String> inputComboBox;
    private ComboBox<String> outputComboBox;
    private Label inputLabel;
    private Label outputLabel;
    private Label chooseLabel;

    private ChatInterface chatUI;

    public SettingsInterface() {
        this.layer = new Pane();
        readyButton = new Button("Ready");
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "COM1",
                        "COM2",
                        "COM3",
                        "COM4");
        inputComboBox = new ComboBox<>(options);
        outputComboBox = new ComboBox<>(options);
        inputLabel = new Label("Input port");
        outputLabel = new Label("Output port");
        chooseLabel = new Label("Choose input | output connection");
        createGui();
    }

    private void createGui() {
        readyButton.setPrefSize(150.0, 25.0);
        readyButton.setLayoutX(90.0);
        readyButton.setLayoutY(294.0);
        readyButton.setFont(new Font(15.0));

        inputComboBox.setLayoutX(131.0);
        inputComboBox.setLayoutY(158.0);
        inputComboBox.setPrefWidth(150.0);

        outputComboBox.setLayoutX(131.0);
        outputComboBox.setLayoutY(225.0);
        outputComboBox.setPrefWidth(150.0);

        inputLabel.setLayoutX(31.0);
        inputLabel.setLayoutY(153.0);
        inputLabel.setPrefSize(81.0, 35.0);
        inputLabel.setFont(new Font(15.0));

        outputLabel.setLayoutX(29.0);
        outputLabel.setLayoutY(225.0);
        outputLabel.setPrefSize(81.0, 25.0);
        outputLabel.setFont(new Font(15.0));

        chooseLabel.setLayoutX(27.0);
        chooseLabel.setLayoutY(32.0);
        chooseLabel.setPrefSize(278.0, 117.0);
        chooseLabel.setFont(new Font(18.0));

    }

    public Pane getLayerCustom() {
        this.layer.getChildren().addAll(
                readyButton,
                inputComboBox,
                outputComboBox,
                inputLabel,
                outputLabel,
                chooseLabel
        );
        return this.layer;
    }

}
