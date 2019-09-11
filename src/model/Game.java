package model;

import java.util.ArrayList;

public class Game {
    private ArrayList<Player> players;
    private Deck deck;
    private Stack stack;
    private int currentPlayer;
    private Boolean isOver;

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
}
