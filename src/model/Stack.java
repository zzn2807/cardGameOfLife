package model;
/* Class to represent the stack of played cards*/
public class Stack {
    StackNode previous;
    Card top;

    public Stack (Card card){
        previous= null;
        top = card;
    }

    public void setPrevious(StackNode previous) {
        this.previous = previous;
    }

    public void placeCard(Card card){
        StackNode newPrevious = new StackNode(top);
        newPrevious.setPrevious(previous);
        previous=newPrevious;
        top = card;
    }

    @Override
    public String toString() {
        return top.toString()+ ", " + previous.toString();
    }
}
