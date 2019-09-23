package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayerNumberSelectorController {

    //root pane of the game page
    @FXML
    private BorderPane gameRoot;

    //Background image for the game screen
    public static BackgroundImage cp = new BackgroundImage(new Image("view/Images/cardPattern.jpg"), null, null, null, null);

    //VBox for selector for number of Players
    public static VBox numSelector = new VBox();

    //Header HBox
    public static HBox header = new HBox();
    //Strip HBox
    public static HBox strip = new HBox();
    //Footer HBox
    public static HBox footer = new HBox();

    //Text giving the player instructions
    public static Text instruction = new Text("How many players?");

    //Text representing number of players
    public static Text twoP   = new Text("Two Players");
    public static Text threeP = new Text("Three Players");
    public static Text fourP  = new Text("Four Players");
    public static Text fiveP  = new Text("Five Players");

    //Fraction of the scene height that represents the size of the header
    public static double headerFrac = 0.4;
    //Fraction of the scene height that represents the size of the header font
    public static double headerFontFrac = 0.27*headerFrac;
    //Fraction of the scene height that represents the size of the body font
    public static double bodyFontFrac = headerFontFrac*0.6;
    //Fraction of the scene height that represents the spacing of the body
    public static double bodySpacingFrac = 0.05;

    public static double stripFrac = 0.03;
    public static double footerFrac = stripFrac;



    public void setup(Stage stage){
        //Height of the scene
        double sceneHeight = stage.getScene().getHeight();

        //Set the background of the root pane
        gameRoot.setBackground(new Background(cp));

        //Set background of the header, strip and footer
        header.setBackground(new Background(new BackgroundFill(Paint.valueOf("#002640"),null,null)));
        strip.setBackground(new Background(new BackgroundFill(Paint.valueOf("#333333"),null,null)));
        footer.setBackground(new Background(new BackgroundFill(Paint.valueOf("#002640"),null,null)));

        //Set pref height of header
        header.setPrefHeight(headerFrac*sceneHeight);
        strip.setPrefHeight(stripFrac*sceneHeight);
        footer.setPrefHeight(footerFrac*sceneHeight);

        //Set alignment of the header and body
        header.setAlignment(Pos.CENTER);
        numSelector.setAlignment(Pos.CENTER);

        //Set the spacing of the body
        numSelector.setSpacing(bodySpacingFrac*sceneHeight);

        //Set classes to text for CSS
        instruction.getStyleClass().add("header");
        twoP.getStyleClass().add("body");
        threeP.getStyleClass().add("body");
        fourP.getStyleClass().add("body");
        fiveP.getStyleClass().add("body");

        //Set font size for texts
        instruction.setFont(Font.font(headerFontFrac*sceneHeight));
        twoP.setFont(Font.font(bodyFontFrac*sceneHeight));
        threeP.setFont(Font.font(bodyFontFrac*sceneHeight));
        fourP.setFont(Font.font(bodyFontFrac*sceneHeight));
        fiveP.setFont(Font.font(bodyFontFrac*sceneHeight));

        //Add text to header
        header.getChildren().add(instruction);
        //Add the text to numSelector
        numSelector.getChildren().addAll(strip,twoP,threeP,fourP,fiveP,footer);

        //Add header to game root
        gameRoot.setTop(header);
        //Add numSelector to game root
        gameRoot.setCenter(numSelector);

        stage.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
            scale(newValue.doubleValue());
        });

        //Create drop shadow effect for hovering over menu texts
        DropShadow ds = new DropShadow();
        ds.setRadius(10);
        ds.setColor(Color.valueOf("#333333"));

        //Set drop shadow effect for player numbers on mouse hover
        twoP.setOnMouseEntered(event -> {
            twoP.setEffect(ds);
        });
        threeP.setOnMouseEntered(event -> {
            threeP.setEffect(ds);
        });
        fourP.setOnMouseEntered(event -> {
            fourP.setEffect(ds);
        });
        fiveP.setOnMouseEntered(event -> {
            fiveP.setEffect(ds);
        });

        //Remove drop shadow effect for player numbers on mouse exit
        twoP.setOnMouseExited(event -> {
            twoP.setEffect(null);
        });
        threeP.setOnMouseExited(event -> {
            threeP.setEffect(null);
        });
        fourP.setOnMouseExited(event -> {
            fourP.setEffect(null);
        });
        fiveP.setOnMouseExited(event -> {
            fiveP.setEffect(null);
        });

        //Set mouse click for player numbers
        twoP.setOnMouseClicked(event -> {
            try {
                startGame(stage,2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        threeP.setOnMouseClicked(event -> {
            try {
                startGame(stage,3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fourP.setOnMouseClicked(event -> {
            try {
                startGame(stage,4);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fiveP.setOnMouseClicked(event -> {
            try {
                startGame(stage,5);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void scale(double sceneHeight){
        header.setPrefHeight(headerFrac*sceneHeight);
        strip.setPrefHeight(stripFrac*sceneHeight);
        footer.setPrefHeight(footerFrac*sceneHeight);

        instruction.setFont(Font.font(headerFontFrac*sceneHeight));
        twoP.setFont(Font.font(bodyFontFrac*sceneHeight));
        threeP.setFont(Font.font(bodyFontFrac*sceneHeight));
        fourP.setFont(Font.font(bodyFontFrac*sceneHeight));
        fiveP.setFont(Font.font(bodyFontFrac*sceneHeight));

        //Set classes to text for CSS
        instruction.getStyleClass().add("header");
        twoP.getStyleClass().add("body");
        threeP.getStyleClass().add("body");
        fourP.getStyleClass().add("body");
        fiveP.getStyleClass().add("body");

        numSelector.setSpacing(bodySpacingFrac*sceneHeight);

    }

    public void startGame(Stage stage, int numOfPlayers) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GamePage.fxml"));
        Parent root = loader.load();
        GamePageController gpc = (GamePageController) loader.getController();
        Scene scene = new Scene(root,stage.getScene().getWidth(),stage.getScene().getHeight());
        scene.getStylesheets().add("view/Stylesheets/PlayerNumberSelectorStyle.css");
        stage.setScene(scene);
        gpc.startGame(stage,numOfPlayers);
    }
}