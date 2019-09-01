package view;

import controller.Controller;
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
        //Fraction of fullscreen the starting stage is
        double fraction = 0.75;
        //Minimum fraction of fullscreen the starting stage can be
        double minFrac = 0.73;

        //Root pane of scene
        Parent root = FXMLLoader.load(getClass().getResource("golFXML.fxml"));
        //Title of game
        primaryStage.setTitle("Game of Life");
        //Set min stage size
        primaryStage.setMinWidth(1380*minFrac);
        primaryStage.setMinHeight(705*minFrac);
        //set scene
        Scene scene = new Scene(root, 1380*fraction, 705*fraction);
        scene.getStylesheets().add("view/style.css");
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {



            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                Controller.scale(newValue.doubleValue());



            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
