package view;

import controller.FrontPageController;
import controller.MainController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOfLife extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../controller/Main.fxml"));
        Parent root = loader.load();
        MainController mc = (MainController) loader.getController();
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
        Scene scene = new Scene(root,500,500);
        //Title of game
        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(scene);
        mc.StartApplication(primaryStage);
        primaryStage.show();
    }


}
