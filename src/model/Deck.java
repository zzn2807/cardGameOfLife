package model;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<>();
        for (Shape shape: Shape.values()){
            if(shape==Shape.JOKER1||shape==Shape.JOKER2){
                continue;
            }
            else {
                for (Type type : Type.values()) {
                    if (type == Type.JOKER) {
                        continue;
                    } else {
                        deck.add(new Card(shape, type));
                    }
                }
            }
        }
        deck.add(new Card(Shape.JOKER1,Type.JOKER));
        deck.add(new Card(Shape.JOKER2,Type.JOKER));
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
