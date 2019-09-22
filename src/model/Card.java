package model;

public class Card {

    private Shape shape;
    private Type type;
    private int value;

    public Card(Shape shape, Type type){
        this.shape  = shape;
        this.type   = type;

        switch (type){
            case ACE:
                value=1;
                break;

            case TWO:
                value=2;
                break;

            case THREE:
                value=3;
                break;

            case FOUR:
                value=4;
                break;

            case FIVE:
                value=5;
                break;

            case SIX:
                value=6;
                break;

            case SEVEN:
                value=7;
                break;

            case EIGHT:
                value=8;
                break;

            case NINE:
                value=9;
                break;

            case TEN:
                value=10;
                break;

            case JESTER:
                value=11;
                break;

            case QUEEN:
                value=12;
                break;

            case KING:
                value=13;
                break;

            default:
                value=20;
                break;

        }
    }

    public int getValue() {
        return value;
    }

    public Shape getShape() {
        return shape;
    }

    @Override
    public String toString() {
        return type+" of "+shape;
    }
}
