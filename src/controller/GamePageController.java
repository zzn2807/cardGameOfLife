package controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

public class GamePageController {

    @FXML
    BorderPane gameRoot;

    //Background Image of game root pane
    public static BackgroundImage cp = new BackgroundImage(new Image("view/Images/cardPattern.jpg"), null, null, null, null);

    //Header VBox
    HBox header = new HBox();
    //Message VBox
    VBox messages = new VBox();

    //HBox containing the player's cards
    public static HBox hand = new HBox();
    //HBox containing the player's cards
    public static HBox stackAndDeck = new HBox();
    //Strip hbox
    public static HBox strip = new HBox();
    //Footer hbox
    public static HBox footer = new HBox();
    //Body vbox
    public static VBox body = new VBox();
    //Bottom vbox
    public static VBox bottom = new VBox();


    public static Text turn = new Text("Player One's turn");
    public static Text message = new Text("Play one or more cards or pick a card from the deck");

    public static double sceneHeight;

    public static double headerFrac = 0.35;
    public static double footerFrac = 0.015;
    public static double stripFrac = footerFrac;
    public static double turnFontFrac = 0.35*headerFrac;
    public static double messageFontFrac = 0.4*turnFontFrac;
    public static double cardHeightFrac = 0.25;
    public static double cardRatio = 0.705;
    public static double cardWidthFrac = cardHeightFrac*cardRatio;
    public static double handCardSpacingFrac = -0.3*cardWidthFrac;
    public static double bodyCardSpacingFrac = 0.2*cardWidthFrac;

    public void startGame(Stage stage, int numberOfPlayers){
        //Height of the scene
        sceneHeight = stage.getScene().getHeight();
        //Set up the headers
        header.setAlignment(Pos.CENTER);
        header.setBackground(new Background(new BackgroundFill(Paint.valueOf("#002640"),null,null)));
        header.setPrefHeight(headerFrac*sceneHeight);

        //Set up footer
        footer.setBackground(new Background(new BackgroundFill(Paint.valueOf("#002640"),null,null)));
        footer.setPrefHeight(footerFrac*sceneHeight);

        //Set up body
        body.getChildren().addAll(strip,stackAndDeck);
        //Set up the messages
        turn.setFont(Font.font(turnFontFrac*sceneHeight));
        turn.getStyleClass().add("header");
        message.setFont(Font.font(messageFontFrac*sceneHeight));
        message.getStyleClass().add("header");

        //Setup bottom
        bottom.getChildren().addAll(hand,footer);
        bottom.setAlignment(Pos.BOTTOM_CENTER);

        //Setup strip
        strip.setPrefHeight(stripFrac*sceneHeight);
        strip.setBackground(new Background(new BackgroundFill(Paint.valueOf("#444444"),null,null)));

        //Set background image
        gameRoot.setBackground(new Background(cp));
        //Set alignment of hand and body
        hand.setAlignment(Pos.BOTTOM_CENTER);
        stackAndDeck.setAlignment(Pos.CENTER);


        //Add messages to message vbox and set alignment
        messages.getChildren().addAll(turn,message);
        messages.setAlignment(Pos.CENTER);

        //Add message vbox to header
        header.getChildren().addAll(messages);

        //Create game
        Game game = new Game(numberOfPlayers);
        //Get current player
        Player currentPlayer = game.getCurrentPlayer();
        //Set top of stack
        ImageView stackIV = parseCard(game.getStackTop());
        stackIV.setFitWidth(cardWidthFrac*sceneHeight);
        stackIV.setFitHeight(cardHeightFrac*sceneHeight);
        stackAndDeck.getChildren().add(stackIV);
        //Get deck image
        ImageView ivcb = new ImageView(new Image("view/Images/cardBack.jpg"));
        ivcb.setFitWidth(cardWidthFrac*sceneHeight);
        ivcb.setFitHeight(cardHeightFrac*sceneHeight);
        ivcb.setOnMouseClicked(event -> {
            Card newCard = game.pickCard();
            ImageView newCardIV = parseCard(newCard);
            newCardIV.setFitWidth(cardWidthFrac*sceneHeight);
            newCardIV.setFitHeight(cardHeightFrac*sceneHeight);
            hand.getChildren().addAll(newCardIV);
            game.getCurrentPlayer().addCard(newCard);
        });
        stackAndDeck.getChildren().add(ivcb);

        //Set spacing for hand and body
        hand.setSpacing(handCardSpacingFrac*sceneHeight);
        stackAndDeck.setSpacing(bodyCardSpacingFrac*sceneHeight);

        for(Card card:currentPlayer.getHand()){
            ImageView cardIV = parseCard(card);
            cardIV.setFitHeight(cardHeightFrac*sceneHeight);
            cardIV.setFitWidth(cardWidthFrac*sceneHeight);
            hand.getChildren().add(cardIV);
        }
        gameRoot.setBottom(bottom);
        gameRoot.setCenter(body);
        gameRoot.setTop(header);

        stage.getScene().heightProperty().addListener((observable, oldValue, newValue) -> {
            scale(newValue.doubleValue());
        });

        gameRoot.setOnMouseClicked(event -> {
            System.out.println("Body clicked");
        });

        hand.setOnDragDetected(event -> {
            Dragboard db = hand.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            ImageView target = (ImageView) event.getTarget();

            content.putString(target.getId());
            content.putImage(target.getImage());


            db.setContent(content);

            event.consume();
        });

        stackIV.setOnDragOver(event -> {
            if (event.getGestureSource()!=stackIV && event.getDragboard().hasString()){
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        stackIV.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasImage()){
                stackIV.setImage(db.getImage());
                success=true;

            }
            event.setDropCompleted(success);

            event.consume();
        });

        hand.setOnDragDone(event -> {
            if(event.getTransferMode() == TransferMode.MOVE){
                hand.getChildren().remove(hand.lookup("#"+event.getDragboard().getString()));
                game.getCurrentPlayer().getHand().remove(stringToCard(event.getDragboard().getString()));
                System.out.println(game.getCurrentPlayer().getHand());
            }
        });
    }

    public ImageView parseCard(Card card){

        ImageView cardIV = new ImageView();
        cardIV.setId(card.toString());
        if(card.getValue()!=20) {
            Image cardImage = new Image("view/PlayingCards/" + card.getShape() + "S/" + card.toString() + ".png");
            cardIV.setImage(cardImage);
        }
        else{
            Image cardImage = new Image("view/PlayingCards/JOKERS/joker.png");
            cardIV.setImage(cardImage);
        }
        return cardIV;
    }

    public void scale(double sceneHeight){
        GamePageController.sceneHeight = sceneHeight;
        header.setPrefHeight(headerFrac*sceneHeight);
        strip.setPrefHeight(stripFrac*sceneHeight);
        footer.setPrefHeight(footerFrac*sceneHeight);

        turn.setFont(Font.font(turnFontFrac*sceneHeight));
        turn.getStyleClass().add("header");
        message.setFont(Font.font(messageFontFrac*sceneHeight));
        message.getStyleClass().add("header");

        for(Node node : hand.getChildren()){
            ImageView iv = (ImageView)node;
            iv.setFitHeight(cardHeightFrac*sceneHeight);
            iv.setFitWidth(cardWidthFrac*sceneHeight);
        }

        for(Node node: stackAndDeck.getChildren()){
            ImageView iv = (ImageView)node;
            iv.setFitHeight(cardHeightFrac*sceneHeight);
            iv.setFitWidth(cardWidthFrac*sceneHeight);
        }

    }

    public Card stringToCard(String cardString){
        String[] shapeAndType = cardString.split("_");
        Card card = new Card(Shape.valueOf(shapeAndType[0].toUpperCase()),Type.valueOf(shapeAndType[1].toUpperCase()));
        return card;
    }


}
