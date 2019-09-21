package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GamePageController implements Initializable {

    @FXML
    private BorderPane gameRoot;
    BackgroundImage cp = new BackgroundImage(new Image("view/Images/cardPattern.jpg"),null,null,null,null);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameRoot.setBackground(new Background(cp));
    }
}
