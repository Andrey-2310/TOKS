package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import ui.chatInterface.ChatInterface;
import ui.chatInterface.SettingsInterface;

import java.io.IOException;

@Getter
public class Main extends Application {

    private ModuleController moduleController;
    private StartFormController startFormController;

    public static void main(String[] args) {
        launch(args);
    }

//    @Override
//    public void start(Stage primaryStage) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("startForm.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
//        primaryStage.show();
//    }
    @Override
    public void start(Stage primaryStage) {
        startFormController = new StartFormController(primaryStage, new SettingsInterface() );
        startFormController.initialize();
    }
//
//    private void initializeStage(Stage primaryStage) {
//        Scene scene = new Scene(createWindow());
//        primaryStage.setTitle("Token Ring");
//        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(false);
//        primaryStage.setResizable(false);
//        primaryStage.setOnCloseRequest(event -> chatUI.getChat().disconnect());
//        primaryStage.show();
//    }
//
//    private Parent createWindow() {
//        Pane root = new Pane();
//        root.setPrefSize(550, 350);
//        root.setStyle("color: #FFFFFF");
//        root.getChildren().add(chatUI.getLayerCustom());
//        return root;
//    }
}
