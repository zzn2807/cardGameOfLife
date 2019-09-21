package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    public BorderPane root;

    public void StartApplication(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontPage.fxml"));
        Parent root = loader.load();
        FrontPageController fpc = (FrontPageController)loader.getController();
        Scene scene = new Scene(root,500,500);
        scene.getStylesheets().add("view/Stylesheets/FrontPageStyle.css");
        stage.setScene(scene);
        fpc.setup(stage);

    }
}
