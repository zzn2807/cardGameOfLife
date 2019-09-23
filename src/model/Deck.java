package model;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<>();
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

    public Card pickStackCard(){
        Card picked = deck.get(new Random().nextInt(deck.size()));
        boolean valid = (picked.getValue()!=1 && picked.getValue()!=2 && picked.getValue()!=5 && picked.getValue()!=11 && picked.getValue()!=20);
        while(!valid){
            this.returnCard(picked);
            picked = this.pickCard();
            valid = (picked.getValue()!=1 && picked.getValue()!=2 && picked.getValue()!=5 && picked.getValue()!=11 && picked.getValue()!=20);
        }
        return picked;
    }
}
