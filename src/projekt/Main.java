package projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("notepad.fxml"));
        primaryStage.setTitle("Notepad");
        primaryStage.setScene(new Scene(root));
        Image icon = new Image("file:icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }
}