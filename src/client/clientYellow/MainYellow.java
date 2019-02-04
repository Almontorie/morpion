package client.clientYellow;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainYellow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = new ClientYellow();
            root.setStyle("-fx-background-color: #a9955c");
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){}
    }
}
