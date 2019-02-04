package client.clientRed;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainRed extends Application {
    @Override
    public void start(Stage primaryStage){
        try {
            Parent root = new ClientRed();
            root.setStyle("-fx-background-color: #769CE1;");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
