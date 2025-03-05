package com.example.project;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class runner{
    public static void main(String[] args) {
        Player player = new Player();
        player.addCard(new Card("5", "♠"));
        player.addCard(new Card("8", "♦"));
    
        ArrayList<Card> communityCards = new ArrayList<>();
        communityCards.add(new Card("A", "♣"));
        communityCards.add(new Card("8", "♠"));
        communityCards.add(new Card("8", "♣"));
        
        player.playHand(communityCards);
        String handResult = player.playHand(communityCards);
        System.out.println(handResult);
    }
}