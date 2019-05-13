package model;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;

    public Deck(){
        for (Shape shape: Shape.values()){
            for(Type type: Type.values()){
                deck.add(new Card(shape,type));
            }
        }
    }

    public Card pickCard(){
        Card picked = deck.get(new Random().nextInt(deck.size()));
        deck.remove(picked);
        return picked;
    }


    public void returnCard(Card card){
        deck.add(card);
    }
}
