package model;

import java.util.ArrayList;

public class Move {

    public static Boolean validMove(Card playerCard, Card stackCard, Shape shape){
        Boolean valid = false;
        if(stackCard.getValue()==2 || stackCard.getValue()==5){
            if(playerCard.getValue()==stackCard.getValue()){
                valid = true;
            }
            else if (playerCard.getValue()==7){
                valid = true;
            }
        }
        else if(stackCard.getValue()==1){
            valid = true;
        }
        else if(stackCard.getValue()==20 && shape != null){
            if(stackCard.getShape()==playerCard.getShape()){
                valid = true;
            }
        }
        else{
            if(playerCard.getShape()==stackCard.getShape()||playerCard.getValue()==stackCard.getValue()){
                valid = true;
            }
        }

        return valid;
    }

    public static Boolean validMove(ArrayList<Card> playerCards,Card stackCard, Shape shape){
        Boolean valid = false;

        int cardInt = playerCards.get(0).getValue();

        for(Card card: playerCards){
            if(card.getValue()!=cardInt){
                return false;
            }
        }

        valid = validMove(playerCards.get(0),stackCard,shape);

        return valid;
    }
}
