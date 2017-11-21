package ui;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import ui.chatInterface.ChatInterface;

@AllArgsConstructor
public class ModuleController {

    private ChatInterface chatUI;

    public void initializeUI(Stage stage) {
        stage.setScene(new Scene(createWindow()));
        stage.setOnCloseRequest(event -> chatUI.getChat().disconnect());
        stage.show();
    }

    private Parent createWindow() {
        Pane root = new Pane();
        root.setPrefSize(550, 350);
        root.setStyle("color: #FFFFFF");
        root.getChildren().add(chatUI.getLayerCustom());
        return root;
    }

    void initializeChatUI() {
        initializeGenerateNewStringButton();
        initializeClearButton();
        initializeAddToBufferButton();
        initializeDestinationComboBox();
        if (chatUI.getChat().isManager()) initializeStartTokenButton();
    }

    private void initializeClearButton() {
        chatUI.getCleanButton().setOnAction(event ->
                chatUI.getDataToAdd().setText(""));
    }

    private void initializeGenerateNewStringButton() {
        chatUI.getGenerateNewByteButton().setOnAction((event) ->
                chatUI.getDataToAdd().setText(chatUI.getChat().generateData()));
    }

    private void initializeAddToBufferButton() {
        chatUI.getAddToBufferButton().setOnAction(event -> {
            chatUI.getChat().addToBuffer(chatUI.getDataToAdd().getText(), chatUI.getDestination());
            chatUI.getDataToAdd().clear();
        });
    }

    private void initializeDestinationComboBox() {
        chatUI.getDestinationComboBox().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> chatUI.setDestination(newValue));
    }

    private void initializeStartTokenButton() {
        chatUI.getStartTokenBackground().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            chatUI.getChat().sendBytes(chatUI.getChat().getPackageManager().getTOKEN());
            chatUI.getStartTokenBackground().setVisible(false);
        });

    }
}
