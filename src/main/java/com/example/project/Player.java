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
        //just add the card to the hadn array
        hand.add(c);
        
    }

    public String playHand(ArrayList<Card> communityCards){  
        //set all cards ot have the hand
        allCards = new ArrayList<>(hand);   
        //add community cards
        for( Card card:communityCards){
            allCards.add(card);
        } 
        //get rankings
        rankFreq = findRankingFrequency();
        rankSuit = findSuitFrequency();

        //get high card value
        int highCard = 0;
        int i = rankFreq.length-1;
      while(i >=0){
        //high card is first card in freq list
            if(rankFreq[i] !=0){
                highCard = i;
                break;
            }
            i--;
           
        }
        //find pairs,trips and four
        int pairs = 0;
        boolean trips = false;
        boolean quads = false;
        for(int num:rankFreq){
            if (num == 2){
                //if any have 2, is a pair, isnt boolean since can be more than one pair
                pairs++;
            }
            if(num == 3){
                //if any have 3 its a trip, also only 1 trip possible per player
                trips = true;
            }
            if(num == 4){
                //if any 4 its a quad, only one quad possible
                quads = true;
            }
        } 
        //see if flush by checking if 5 freq of a suit
        boolean flush = false;
        for(int num:rankSuit){
            if(num == 5){
                flush = true;
            }
        }

        //srot
        sortAllCards();
        //check for straight
        boolean straight = true;
        for(int j = 0;j<allCards.size()-1;j++){
            //gest rank of this and next one
            int rankJ = Utility.getRankValue(allCards.get(j).getRank());
            int rankJ1 = Utility.getRankValue(allCards.get(j +1).getRank());
            //checks for straight by seeing if in order, if not becomes false
            if( rankJ != rankJ1 - 1){
                straight = false;
            }
        }
        //check for royal straight by checking if its royal, i.e if fist and last are 10 and ace since already sorted
        boolean royalStraight = false;
        if(straight){
            //if first is 10, and last is ace, then stragiht since already sorted
            if((Utility.getRankValue(allCards.get(0).getRank()) == 10) &&(Utility.getRankValue(allCards.get(4).getRank())) == 14){
                royalStraight = true;
            }
        }

        //check for high card by seeing if highest card is players
        int rankHand1 = Utility.getRankValue(hand.get(0).getRank());
        int rankHand2 = Utility.getRankValue(hand.get(1).getRank());
        boolean high = false;
        for(int j = rankFreq.length-1; j>=0;j--){
            if((rankFreq[j] != 0) &&((  j +2== rankHand1) || (j+2 == rankHand2))){
                high = true;
                break;
            }else{
                break;
            }
        }
        //actual checking
        if(royalStraight && flush){
            return "Royal Flush";
        }
        else if(straight && flush){
            return  "Straight Flush";
        }
        else if(quads){
            return "Four of a Kind";
        }
        else if( pairs == 1 && trips){
            return "Full House";
        }
        else if(flush){
            return "Flush";
        }
        else if(straight){
            return "Straight";
        }
        else if(trips){
            return "Three of a Kind";
        }
        else if(pairs == 2){
            return "Two Pair";
        }
        else if( pairs==1){
            return "A Pair";
        }
        else if( high) {
            return "High Card";
        }else{
            return "Nothing";
        }

        
    
    }

    public void sortAllCards(){
         //do selection sort since small size
         for(int i =0;i<allCards.size();i++){
            int smallestRank = 15;
            int index = i;
            for(int j=i;j<allCards.size();j++){
                //get rank
                String rank = allCards.get(j).getRank();
                //turn to int
                int rankVal = Utility.getRankValue(rank);
                //check if smaller
                if(rankVal < smallestRank){
                    smallestRank = rankVal;
                    index = j;

                }
            }
            //swaping
            Card temp =allCards.set(i, allCards.get(index));
            allCards.set(index, temp);

         }
    } 

    public int[] findRankingFrequency(){
        //create int array since easeir for freq list
        int[] rankFreq = new int[13];
        for(int i=0;i<allCards.size();i++){
            //if it matches index minus 2 add it as an apperance of that index
            int rank= Utility.getRankValue(allCards.get(i).getRank()) - 2;
            rankFreq[rank]++;
        }
        return rankFreq;
    }

    public int[] findSuitFrequency(){
        //create int array since easeir for freq list
        int[] rankSuit = new int[4];

        for(int i=0;i<allCards.size();i++){
            String suit = allCards.get(i).getSuit();
        //checks what suit, adds based on order in utiliy list
            if(suit.equals("♠")){
                rankSuit[0]++;
            }
            if(suit.equals("♥")){
                rankSuit[1]++;
            }
            if(suit.equals("♣")){
                rankSuit[2]++;
            }
            if(suit.equals("♦")){
                rankSuit[3]++;
            }
        }
        return rankSuit;
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }




}
