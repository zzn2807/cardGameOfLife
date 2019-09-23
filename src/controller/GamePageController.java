package controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Card;
import model.Game;
import model.Player;

public class GamePageController {

    @FXML
    BorderPane gameRoot;

    //Background Image of game root pane
    public static BackgroundImage cp = new BackgroundImage(new Image("view/Images/cardPattern.jpg"), null, null, null, null);

    //Header VBox
    VBox header = new VBox();
    //HBox containing the player's cards
    public static HBox hand = new HBox();
    //HBox containing the player's cards
    public static HBox stack = new HBox();
    //HBox containing the player's cards
    public static HBox deck = new HBox();

    public static Text turn = new Text("Player One's turn");
    public static Text message = new Text();

    public static double cardHeightFrac = 0.3;
    public static double cardRatio = 0.705;
    public static double cardWidthFrac = cardHeightFrac*cardRatio;

    public void startGame(Stage stage, int numberOfPlayers){
        //Height of the scene
        double sceneHeight = stage.getScene().getHeight();
        //Make the header fill
        header.setFillWidth(true);
        header.setAlignment(Pos.CENTER);
        header.setBackground(new Background(new BackgroundFill(Paint.valueOf("#002640"),null,null)));
        header.setSpacing(30);

        turn.setFont(Font.font(60));
        turn.getStyleClass().add("header");
        message.setFont(Font.font(60));
        message.getStyleClass().add("header");

        message.getStyleClass().add("header");
        //Set background image
        gameRoot.setBackground(new Background(cp));
        //Set alignment of hand, stack, and deck
        hand.setAlignment(Pos.BOTTOM_CENTER);
        stack.setAlignment(Pos.CENTER);
        deck.setAlignment(Pos.CENTER_LEFT);
        //Create game
        Game game = new Game(numberOfPlayers);
        //Get current player
        Player currentPlayer = game.getCurrentPlayer();
        //Set top of stack
        ImageView stackIV = parseCard(game.getStackTop());
        stackIV.setFitWidth(cardWidthFrac*sceneHeight);
        stackIV.setFitHeight(cardHeightFrac*sceneHeight);
        stack.getChildren().add(stackIV);
        //Get deck image
        ImageView ivcb = new ImageView(new Image("view/Images/cardBack.jpg"));
        ivcb.setFitWidth(cardWidthFrac*sceneHeight);
        ivcb.setFitHeight(cardHeightFrac*sceneHeight);
        deck.getChildren().add(ivcb);

        for(Card card:currentPlayer.getHand()){
            ImageView cardIV = parseCard(card);
            cardIV.setFitHeight(cardHeightFrac*sceneHeight);
            cardIV.setFitWidth(cardWidthFrac*sceneHeight);
            hand.getChildren().add(cardIV);
        }
        header.getChildren().addAll(turn,message);
        gameRoot.setBottom(hand);
        gameRoot.setCenter(stack);
        gameRoot.setRight(deck);
        gameRoot.setTop(header);

    }

    public ImageView parseCard(Card card){

        ImageView cardIV = new ImageView();
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
}
