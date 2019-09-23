package model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private Stack stack;
    private int currentPlayer;
    private Boolean isOver;
    private Boolean moveMade;

    public Game(int playerNo){
        isOver = false;
        currentPlayer = 0;
        players = new ArrayList<>();
        deck = new Deck();
        for(int i = 0; i<playerNo; i++){
            Player player = new Player(deck,(52-52%playerNo)/playerNo);
            players.add(player);
        }
        stack = new Stack(deck.pickStackCard());
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void startGame(){
        while(!isOver){
            while (!moveMade){

            }
            moveMade = false;
            currentPlayer = (currentPlayer++)%players.size();
            if(stack.getStackSize()>=10){
                stack.emptyStack(deck);
            }
        }
    }

    public void setMoveMade(){
        moveMade = true;
    }
}
