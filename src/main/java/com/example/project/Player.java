package com.example.project;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    private int[] rankFreq ;
    private int[] rankSuit;
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();

    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        
    }

    public String playHand(ArrayList<Card> communityCards){      
        return "Nothing";
    }

    public void sortAllCards(){} 

    public int[] findRankingFrequency(){
        for(int i=0;i<allCards.size();i++){
            int rank= allCards.get(i).getRank();
            rankFreq[rank]++;
        }
        return new ArrayList<>(); 
    }

    public ArrayList<Integer> findSuitFrequency(){

        return new ArrayList<>(); 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
