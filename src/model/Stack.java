package model;

import java.util.ArrayList;

/* Class to represent the stack of played cards*/
public class Stack {
    private ArrayList<Card> cards;

    public Stack(Card card){
        cards = new ArrayList<>();
        cards.add(card);
    }

    public void emptyStack(Deck deck){
        for(int i = 0; i<cards.size()-1;i++){
            deck.returnCard(cards.get(i));
        }
        Card top = cards.get(cards.size()-1);
        cards = new ArrayList<>();
        cards.add(top);
    }

    public int getStackSize(){
        return cards.size();
    }

    public Card getTop(){
        return cards.get(cards.size()-1);
    }
}
