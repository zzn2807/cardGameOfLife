package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOfLife extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("golFXML.fxml"));
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(new Scene(root,400,400));
        primaryStage.show();
    }


}
