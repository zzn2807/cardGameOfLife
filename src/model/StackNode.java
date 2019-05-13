package model;

public class StackNode {
    StackNode previous;
    Card card;

    public StackNode(Card card){
        this.card=card;
        this.previous=null;
    }

    public void setPrevious(StackNode previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        String stackNodeString = "";
        stackNodeString=card.toString()+", ";
        StackNode stackNode = previous;
        while (stackNode!=null){
            stackNodeString+=stackNode.card.toString()+", ";
            stackNode=stackNode.previous;
        }
        stackNodeString=stackNodeString.substring(0,stackNodeString.length()-2);
        return stackNodeString;
    }
}
