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

    public void makeMove(Card stackTop){

    }


}
