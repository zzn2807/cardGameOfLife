package controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Card;
import model.Game;
import model.Player;

public class GamePageController {

    @FXML
    BorderPane gameRoot;

    //Background Image of game root pane
    public static BackgroundImage cp = new BackgroundImage(new Image("view/Images/cardPattern.jpg"), null, null, null, null);

    //HBox containing the player's cards
    public static HBox hand = new HBox();
    //HBox containing the player's cards
    public static HBox stack = new HBox();
    //HBox containing the player's cards
    public static HBox deck = new HBox();

    public static double cardHeightFrac = 0.2;
    public static double cardRatio = 0.705;
    public static double cardWidthFrac = cardHeightFrac*cardRatio;

    public void startGame(Stage stage, int numberOfPlayers){
        //Set background image
        gameRoot.setBackground(new Background(cp));
        //Set alignment of hand
        hand.setAlignment(Pos.BOTTOM_CENTER);
        //Create game
        Game game = new Game(numberOfPlayers);
        //Get current player
        Player currentPlayer = game.getCurrentPlayer();

        for(Card card:currentPlayer.getHand()){
            ImageView cardIV = parseCard(card);
            hand.getChildren().add(cardIV);
        }
        gameRoot.setBottom(hand);

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
