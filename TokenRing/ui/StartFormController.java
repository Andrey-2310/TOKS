package ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import ui.chatInterface.ChatInterface;
import ui.chatInterface.SettingsInterface;

import java.util.Objects;

@AllArgsConstructor
public class StartFormController {

    private Stage stage;
    private SettingsInterface settingsUI;

    public void initialize() {
        initializeUI();
        settingsUI.getReadyButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (!Objects.isNull(settingsUI.getOutputPortValue()) &&
                    !Objects.isNull(settingsUI.getInputComboBox())) {
                startChatting();
            }
        });
        settingsUI.getInputComboBox().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> settingsUI.setInputPortValue(newValue));
        settingsUI.getOutputComboBox().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> settingsUI.setOutputPortValue(newValue));
    }

    private void initializeUI() {
        Scene scene = new Scene(createWindow());
        stage.setTitle("COM Ports chat program");
        stage.setScene(scene);
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.show();
    }

    private Parent createWindow() {
        Pane root = new Pane();
        root.setPrefSize(330, 350);
        root.setStyle("color: #FFFFFF");
        root.getChildren().add(settingsUI.getLayerCustom());
        return root;
    }

    private void startChatting() {
        ModuleController moduleController = new ModuleController(
                new ChatInterface(settingsUI.getInputPortValue(), settingsUI.getOutputPortValue()));
        moduleController.initializeChatUI();
        moduleController.initializeUI(stage);
    }
}
