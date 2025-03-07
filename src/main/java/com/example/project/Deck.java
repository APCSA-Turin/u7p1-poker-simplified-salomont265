package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public  void initializeDeck(){ //hint.. use the utility class
        for(int i=0;i<4;i++){
            //makes a copy of each suit for each value
            String suit = Utility.getSuits()[i];
            for(int j=0;j<13;j++){
                String rank = Utility.getRanks()[j];
                Card card = new Card(rank,suit);
                cards.add(card);
            }
        }
    }

    public  void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
    
        for(int i=0;i<cards.size();i++){
            //shuffle by randomly swapping but increases min to make sure if swapped it wont be swapped again
            int swap = (int)(Math.random()* (51-i+1)) +i;
            Card temp = cards.get(i);
            cards.set(i,cards.get(swap));
            cards.set(swap,temp);
        }
    }

    public  Card drawCard(){
        //only draws card if cards left to draw
        if(cards.size()>=1){
            return cards.remove(0);
        }else{
       return new Card("","");
        }
    }

    public  boolean isEmpty(){
        return cards.isEmpty();
    }

   


}