package model;

import java.util.ArrayList;

public class Player {

    private ArrayList<Card> cards;

    public Player(Deck deck, int noOfCards){
        cards= new ArrayList<>();

        for(int i = 0; i < noOfCards; i++){
            cards.add(deck.pickCard());
        }
    }


}
