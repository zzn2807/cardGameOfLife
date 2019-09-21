package controller;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrontPageController{

    @FXML
    private BorderPane root;

    //Ratio of width of a card to its height
    public static double cardWHRatio = 0.705;

    //Fraction of the scene height that the header is contained within
    public static double headerFrac = 0.4;
    //Fraction of the scene height that the strip is contained within
    public static double stripFrac = 0.03;
    //Fraction of the scene height that the footer is contained within
    public static double footerFrac = stripFrac;
    //Fraction of the scene height that the card menu is contained within
    public static double cardFrac = 0.54;


    //Fraction of the scene that the animated cards are contained within the header
    public static double headerCardFrac = 0.5*headerFrac;
    //Fraction of the scene that the header font size is
    public static double headerFontFrac = 0.2*headerFrac;
    //Fraction of the scene that the pivotY of the textgroup is
    public static double headerPivotYFrac = 0.75*headerFrac;

    //Fraction of the scene that the body font size is
    public static double bodyFontFrac = 0.16*cardFrac;

    //Header StackPane
    public static StackPane header = new StackPane();
    //Strip HBox
    public static HBox strip = new HBox();
    //Footer HBox
    public static HBox footer = new HBox();
    //Menu Stackpane
    public static StackPane menu = new StackPane();

    //Body VBox (Bodu contains strip and menu)
    public static VBox body = new VBox();

    //Header Text Group
    public static Group textGroup = new Group();

    //Image view of the king card
    public ImageView ivk = new ImageView();
    //Image view of the queen card
    public ImageView ivq = new ImageView();
    //Image view of the jack card
    public ImageView ivj = new ImageView();
    //Image view of the back of a card used for menu
    public ImageView ivcb = new ImageView();

    //Image of the king card
    public Image k = new Image("view/Images/king.png");
    //Image of the queen card
    public Image q = new Image("view/Images/queen.png");
    //Image of the jack card
    public Image j = new Image("view/Images/jack.jpg");
    //Image of the background of the scene
    public Image cp = new Image("view/Images/cardPattern.jpg");
    //Image of the back of a card
    public Image cb = new Image("view/Images/cardBack.jpg");

    //Rotate transform for the jack card
    public static Rotate jRotate = new Rotate(0);
    //Rotate transform for the queen card
    public static Rotate qRotate = new Rotate(0);

    //Translate the header cards slightly up
    public static Translate headerCardTranslate = new Translate();

    //Translate play above centre-line
    public static Translate playTranslate = new Translate();

    //Translate exit below centre-line
    public static Translate exitTranslate = new Translate();

    //Menu Label for "play"
    public static Text play = new Text("Play");
    //Menu Label for "rules"
    public static Text rules = new Text("Rules");
    //Menu Label for "exit"
    public static Text exit = new Text("Exit");

    //Game of Life Text
    public static String gol = "Game Of Life";

    //Background image of the scene
    public BackgroundImage backgroundImage = new BackgroundImage(cp,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);



    public void setup(Stage stage){
        double sceneHeight = stage.getScene().getHeight();


        //Set Background Image of App
        root.setBackground(new Background(backgroundImage));

        //Set Background Color of header, strip and footer
        header.setBackground(new Background(new BackgroundFill(Paint.valueOf("#002640"),null,null)));
        strip.setBackground(new Background(new BackgroundFill(Paint.valueOf("#222222"),null,null)));
        footer.setBackground(new Background(new BackgroundFill(Paint.valueOf("#002640"),null,null)));

        //Rotation animation for the jack card. Rotates left, pauses for half a second, rotates to the other side, pauses again, returns to center
        Timeline jRotation = new Timeline();
        jRotation.getKeyFrames().addAll(new KeyFrame(Duration.seconds(1),new KeyValue(jRotate.angleProperty(),-25)),
                new KeyFrame(Duration.seconds(2),new KeyValue(jRotate.angleProperty(),25)),
                new KeyFrame(Duration.seconds(0.5),new KeyValue(jRotate.angleProperty(),-25)),
                new KeyFrame(Duration.seconds(3),new KeyValue(jRotate.angleProperty(),0)),
                new KeyFrame(Duration.seconds(2.5),new KeyValue(jRotate.angleProperty(),25)));

        //Rotation animation for the queen card. Rotates right, pauses for half a second, rotates to the other side, pauses again, returns to center
        Timeline qRotation = new Timeline();
        qRotation.getKeyFrames().addAll(new KeyFrame(Duration.seconds(1),new KeyValue(qRotate.angleProperty(),25)),
                new KeyFrame(Duration.seconds(2),new KeyValue(qRotate.angleProperty(),-25)),
                new KeyFrame(Duration.seconds(0.5),new KeyValue(qRotate.angleProperty(),25)),
                new KeyFrame(Duration.seconds(3),new KeyValue(qRotate.angleProperty(),0)),
                new KeyFrame(Duration.seconds(2.5),new KeyValue(qRotate.angleProperty(),-25))
        );
        //Set the animation to loop continuously
        qRotation.setCycleCount(Animation.INDEFINITE);
        jRotation.setCycleCount(Animation.INDEFINITE);


        //Play animation
        jRotation.play();
        qRotation.play();


        //Add transforms to the animated cards to animate them
        ivq.getTransforms().add(qRotate);
        ivj.getTransforms().add(jRotate);

        //Set up the game of life text
        double rotate = -90;
        for(char c: gol.toCharArray()){
            Text text = new Text(Character.toString(c));
            text.setId("header-text");
            text.setFont(Font.font(headerFontFrac*sceneHeight));
            text.setFill(Color.WHITE);


            double pivotY = headerPivotYFrac*sceneHeight;


            Rotate rotation = new Rotate(rotate,0,pivotY);
            text.getTransforms().add(rotation);

            textGroup.getChildren().add(text);
            rotate+=16;
        }

        //Set height of the various components of the scene according to fractions as well as the width of the menu
        header.setPrefHeight(headerFrac*sceneHeight);
        strip.setPrefHeight(stripFrac*sceneHeight);
        footer.setPrefHeight(footerFrac*sceneHeight);
        ivcb.setFitHeight(cardFrac*sceneHeight);
        ivcb.setFitWidth(cardFrac*sceneHeight*cardWHRatio);

        //Set height of the cards within the header
        ivk.setFitHeight(headerCardFrac*sceneHeight);
        ivq.setFitHeight(headerCardFrac*sceneHeight);
        ivj.setFitHeight(headerCardFrac*sceneHeight);

        //Set width of the cards within the header
        ivk.setFitWidth(cardWHRatio*headerCardFrac*sceneHeight);
        ivq.setFitWidth(cardWHRatio*headerCardFrac*sceneHeight);
        ivj.setFitWidth(cardWHRatio*headerCardFrac*sceneHeight);

        //Set header translate
        headerCardTranslate.setY(-0.2*headerCardFrac*sceneHeight);

        //Set pivots for animated cards
        jRotate.setPivotX(0.5*ivk.getFitWidth());
        jRotate.setPivotY(1.35*ivk.getFitHeight());
        qRotate.setPivotX(0.5*ivk.getFitWidth());
        qRotate.setPivotY(1.35*ivk.getFitHeight());

        ivk.getTransforms().add(headerCardTranslate);
        ivq.getTransforms().add(headerCardTranslate);
        ivj.getTransforms().add(headerCardTranslate);

        //Set the image of the menu
        ivcb.setImage(cb);

        //Set the images of the header cards
        ivk.setImage(k);
        ivq.setImage(q);
        ivj.setImage(j);

        //Add header cards to header
        header.getChildren().addAll(ivq,ivj,ivk,textGroup);

        exitTranslate.setY(ivcb.getFitHeight()/4);
        playTranslate.setY(-ivcb.getFitHeight()/4);
        //Translates for exit and play
        exit.getTransforms().add(exitTranslate);
        play.getTransforms().add(playTranslate);

        //Set Alignment for header items
        StackPane.setAlignment(ivk,Pos.BOTTOM_CENTER);
        StackPane.setAlignment(ivq,Pos.BOTTOM_CENTER);
        StackPane.setAlignment(ivj,Pos.BOTTOM_CENTER);

        menu.getChildren().addAll(ivcb,play,rules,exit);

        //Add strip and menu to the body of the scene
        body.getChildren().addAll(strip,menu);

        //Set Alignment of the body
        body.setAlignment(Pos.TOP_CENTER);

        //Put the various components of the scene onto the scene
        root.setTop(header);
        root.setCenter(body);
        root.setBottom(footer);

        //Set a window scale listener
        stage.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
            scale(newValue.doubleValue());
        });



        //Create drop shadow effect for hovering over menu labels
        DropShadow ds = new DropShadow();
        ds.setRadius(10);
        ds.setColor(Color.valueOf("#8c4544"));


        //Make labels glow when mouse hovers over it
        play.setOnMouseEntered(event -> {
            play.setEffect(ds);
        });

        rules.setOnMouseEntered(event -> {
            rules.setEffect(ds);
        });

        exit.setOnMouseEntered(event -> {
            exit.setEffect(ds);
        });
        //Make labels stop glowing after mouse leaves
        play.setOnMouseExited(event ->{
            play.setEffect(null);
        });

        rules.setOnMouseExited(event -> {
            rules.setEffect(null);
        });

        exit.setOnMouseExited(event -> {
            exit.setEffect(null);
        });


        //Add style classes to menu labels for css styling
        play.getStyleClass().add("body-text");
        rules.getStyleClass().add("body-text");
        exit.getStyleClass().add("body-text");

        //set font size to 27
        play.setFont(Font.font(bodyFontFrac*sceneHeight));
        rules.setFont(Font.font(bodyFontFrac*sceneHeight));
        exit.setFont(Font.font(bodyFontFrac*sceneHeight));


    }

    public void scale(double height){
        //Set height of the various components of the scene according to fractions
        header.setPrefHeight(headerFrac*height);
        strip.setPrefHeight(stripFrac*height);
        footer.setPrefHeight(footerFrac*height);
        ivcb.setFitHeight(cardFrac*height);
        ivcb.setFitWidth(cardFrac*height*cardWHRatio);

        //Set height of the cards within the header
        ivk.setFitHeight(headerCardFrac*height);
        ivq.setFitHeight(headerCardFrac*height);
        ivj.setFitHeight(headerCardFrac*height);

        //Set width of the cards within the header
        ivk.setFitWidth(cardWHRatio*headerCardFrac*height);
        ivq.setFitWidth(cardWHRatio*headerCardFrac*height);
        ivj.setFitWidth(cardWHRatio*headerCardFrac*height);

        //Set pivots for animated cards
        jRotate.setPivotX(0.5*ivk.getFitWidth());
        jRotate.setPivotY(1.35*ivk.getFitHeight());
        qRotate.setPivotX(0.5*ivk.getFitWidth());
        qRotate.setPivotY(1.35*ivk.getFitHeight());

        headerCardTranslate.setY(-0.2*headerCardFrac*height);

        textGroup.getChildren().clear();
        //Set up the game of life text
        double rotate = -90;
        for(char c: gol.toCharArray()){
            Text text = new Text(Character.toString(c));
            text.setId("header-text");
            text.setFont(Font.font(headerFontFrac*height));
            text.setFill(Color.WHITE);


            double pivotY = headerPivotYFrac*height;


            Rotate rotation = new Rotate(rotate,0,pivotY);
            text.getTransforms().add(rotation);

            textGroup.getChildren().add(text);
            rotate+=16;
        }

        exitTranslate.setY(ivcb.getFitHeight()/4);
        playTranslate.setY(-ivcb.getFitHeight()/4);

        play.setFont(Font.font(bodyFontFrac*height));
        rules.setFont(Font.font(bodyFontFrac*height));
        exit.setFont(Font.font(bodyFontFrac*height));

        play.getStyleClass().add("body-text");
        rules.getStyleClass().add("body-text");
        exit.getStyleClass().add("body-text");

    }

}
