package controller;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private BorderPane root;

    static double sceneFrac = 0.75;
    static double sceneWidth = 1380*sceneFrac;
    static double sceneHeight = 705*sceneFrac;
    static double cardRatio = 0.705;
    static double headerRatio = 0.45;
    static double width = 190;
    static double rotate = -90;
    static double headerCardWidth = 75;
    BackgroundImage cp = new BackgroundImage(new Image("view/Images/cardPattern.jpg"),null,null,null,null);
    //Back of card
    static Image cb = new Image("view/Images/cardBack.jpg");
    static Image j = new Image("view/Images/joker.jpg");
    static Image q = new Image("view/Images/queen.png");
    static Image k = new Image("view/Images/king.png");

    //Card Image View
    static ImageView ivcb = new ImageView(cb);
    static ImageView ivj = new ImageView(j);
    static ImageView ivq = new ImageView(q);
    static ImageView ivk = new ImageView(k);
    //Card group
    static Group g = new Group();
    //Top Header
    static HBox hbox = new HBox();
    static HBox strip = new HBox();
    static HBox footer = new HBox();
    static StackPane stack = new StackPane();
    static StackPane headerStack = new StackPane();
    static String gol = "GAME OF LIFE";
    static Group textGroup = new Group();
    static Group cardGroup = new Group();
    static Label play = new Label("Play");
    static Label cont = new Label("Rules");
    static Label exit = new Label ("Exit");
    //Rotate transforms for the left and right animated cards in the header
    static Rotate jRotate = new Rotate(0);
    static Rotate qRotate = new Rotate(0);
    static FontLoader fl = Toolkit.getToolkit().getFontLoader();
    //Translates for play, cont and exit
    static Translate shiftPlay = new Translate(0,0);
    static Translate shiftExit = new Translate(0,0);



    public static void scale(double height){
        //Adds "GAME OF LIFE" to header text group around a semicircle
        textGroup.getChildren().clear();
        rotate = -90;
        for(char c: gol.toCharArray()){
            Text text = new Text(Character.toString(c));
            text.setId("text");
            text.setFont(Font.font(60*(height/sceneHeight)));
            text.setFill(Color.WHITE);


            double pivotY = 150+(height-sceneHeight)*0.4;


            Rotate rotation = new Rotate(rotate,10,pivotY);
            text.getTransforms().add(rotation);

            textGroup.getChildren().add(text);
            rotate+=16;
        }
        //Scale header
        hbox.setPrefHeight(height*headerRatio);
        //Scale Animated Cards
        ivk.setFitWidth(headerCardWidth+(height-sceneHeight)*0.25);
        ivk.setFitHeight(ivk.getFitWidth()/cardRatio);
        ivq.setFitWidth(headerCardWidth+(height-sceneHeight)*0.25);
        ivq.setFitHeight(ivq.getFitWidth()/cardRatio);
        ivj.setFitWidth(headerCardWidth+(height-sceneHeight)*0.25);
        ivj.setFitHeight(ivj.getFitWidth()/cardRatio);
        //Scale strip
        strip.setPrefHeight((stack.getHeight()-ivcb.getFitHeight())/2);
        //Adjust animation
        jRotate.setPivotY((1.9*headerCardWidth/cardRatio)+(height-sceneHeight)*0.59);
        qRotate.setPivotY((1.9*headerCardWidth/cardRatio)+(height-sceneHeight)*0.59);
        jRotate.setPivotX((ivk.getFitWidth()-headerCardWidth)*0.50);
        qRotate.setPivotX((ivk.getFitWidth()-headerCardWidth)*0.50);

        //Scale card back menu
        ivcb.setFitWidth(width*(height/sceneHeight));
        ivcb.setFitHeight((width/cardRatio)*(height/sceneHeight));

        //Adjust menu option positions
        shiftPlay.setY(-ivcb.getFitHeight()*0.25);
        shiftExit.setY(ivcb.getFitHeight()*0.25);

        //Scale menu options
        play.setScaleX(height/sceneHeight);
        play.setScaleY(height/sceneHeight);
        cont.setScaleX(height/sceneHeight);
        cont.setScaleY(height/sceneHeight);
        exit.setScaleX(height/sceneHeight);
        exit.setScaleY(height/sceneHeight);

        //Scale strip and footer
        strip.setMaxHeight(height/50);
        footer.setMaxHeight(height/50);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {



        //Add background to pane
        root.setBackground(new Background(cp));
        //Add color to header
        hbox.setBackground(new Background(new BackgroundFill(Paint.valueOf("#002640"),null,null)));
        //Set height of header
        hbox.setPrefHeight(sceneHeight*headerRatio);
        //


        //Back of card image view used for menu
        ivcb.setFitWidth(width);
        ivcb.setFitHeight(width/cardRatio);



        //set sizes of the three animated cards in the header
        ivk.setFitWidth(headerCardWidth);
        ivj.setFitWidth(headerCardWidth);
        ivq.setFitWidth(headerCardWidth);

        ivk.setFitHeight(headerCardWidth/cardRatio);
        ivj.setFitHeight(headerCardWidth/cardRatio);
        ivq.setFitHeight(headerCardWidth/cardRatio);

        //Center the three animated cards
        ivj.setX(-ivj.getFitWidth()/2);
        ivk.setX(-ivk.getFitWidth()/2);
        ivq.setX(-ivq.getFitWidth()/2);

        ivj.setY(36);
        ivq.setY(36);
        ivk.setY(36);



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

        //Add header cards to header text group
        cardGroup.getChildren().addAll(ivj,ivq,ivk);
        //Makes sure the cards stack on top of each other
        cardGroup.setBlendMode(BlendMode.SRC_ATOP);

        //align the hbox to centre and add text group to hbox
        hbox.setAlignment(Pos.CENTER);
        headerStack.getChildren().addAll(cardGroup,textGroup);
        headerStack.setAlignment(Pos.BOTTOM_CENTER);
        hbox.getChildren().add(headerStack);


        //Add the card back menu background to body group


        //Create drop shadow effect for hovering over menu labels
        DropShadow ds = new DropShadow();
        ds.setRadius(10);
        ds.setColor(Color.valueOf("#8c4544"));


        //Make labels glow when mouse hovers over it
        play.setOnMouseEntered(event -> {
            play.setEffect(ds);
        });

        cont.setOnMouseEntered(event -> {
            cont.setEffect(ds);
        });

        exit.setOnMouseEntered(event -> {
            exit.setEffect(ds);
        });
        //Make labels stop glowing after mouse leaves
        play.setOnMouseExited(event ->{
            play.setEffect(null);
        });

        cont.setOnMouseExited(event -> {
            cont.setEffect(null);
        });

        exit.setOnMouseExited(event -> {
            exit.setEffect(null);
        });

        //Set menu text colour to white
        play.setTextFill(Color.WHITE);
        cont.setTextFill(Color.WHITE);
        exit.setTextFill(Color.WHITE);

        //Add style classes to menu labels for css styling
        exit.getStyleClass().add("lbl");
        play.getStyleClass().add("lbl");
        cont.getStyleClass().add("lbl");

        //set font size to 27
        play.setFont(Font.font(40));
        cont.setFont(Font.font(40));
        exit.setFont(Font.font(40));




        //Set exit label to end application
        exit.setOnMouseClicked(event ->{
            Platform.exit();
        });
        //Set rules label to clear root
        cont.setOnMouseClicked(event -> {
            root.getChildren().clear();
        });





        play.getTransforms().add(shiftPlay);
        exit.getTransforms().add(shiftExit);

        StackPane.setAlignment(strip,Pos.TOP_CENTER);
        StackPane.setAlignment(footer,Pos.BOTTOM_CENTER);
        strip.setBackground(new Background(new BackgroundFill(Color.valueOf("#222222"),null,null)));
        footer.setBackground(new Background(new BackgroundFill(Color.valueOf("#002640"),null,null)));


        stack.getChildren().addAll(strip,ivcb,play,cont,exit,footer);



        root.setTop(hbox);
        root.setCenter(stack);



    }
}
