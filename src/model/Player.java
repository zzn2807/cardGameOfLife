package model;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private Boolean isTurn;

    public Player(Deck deck, int noOfCards){
        hand= new ArrayList<>();
        isTurn = false;
        for(int i = 0; i < noOfCards; i++){
            hand.add(deck.pickCard());
        }
    }

    public void makeMove(Game game){
        game.setMoveMade();
    }

    public boolean hasWon(Card stackCard){
        return ((hand.size()==0)&&(stackCard.getValue()!=1)
                                &&(stackCard.getValue()!=2)
                                &&(stackCard.getValue()!=5)
                                &&(stackCard.getValue()!=11)
                                &&(stackCard.getValue()!=20));
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
}
