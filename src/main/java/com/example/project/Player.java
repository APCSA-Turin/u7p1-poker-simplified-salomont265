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

        //get high card
        int highCard = 0;
        int i = rankFreq.length-1;
      while(i >=0){
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
                pairs++;
            }
            if(num == 3){
                trips = true;
            }
            if(num == 4){
                quads = true;
            }
        }
        //see if flush
        boolean flush = false;
        for(int num:rankSuit){
            if(num == 5){
                flush = true;
            }
        }
        //see if straight

        //srot
        sortAllCards();

        boolean straight = true;
        for(int j = 0;j<allCards.size()-1;j++){

            int rankJ = Utility.getRankValue(allCards.get(j).getRank());
            int rankJ1 = Utility.getRankValue(allCards.get(j +1).getRank());
         
            if( rankJ != rankJ1 - 1){
                straight = false;
            }
        }
        //check for royal straight
        boolean royalStraight = false;
        if(straight){
            //if first is 10, and last is ace, then stragiht since already sorted
            if((Utility.getRankValue(allCards.get(0).getRank()) == 10) &&(Utility.getRankValue(allCards.get(4).getRank())) == 14){
                royalStraight = true;
            }
        }

        /*  case "Royal Flush": return 11;
        case "Straight Flush": return 10;
        case "Four of a Kind": return 9;
        case "Full House": return 8;
        case "Flush": return 7;
        case "Straight": return 6;
        case "Three of a Kind": return 5;
        case "Two Pair": return 4;
        case "A Pair": return 3;
        case "High Card": return 2;
        case "Nothing": return 1;
        */
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
        else if( allCards.size() != 0) {
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
        int[] rankFreq = new int[13];
        for(int i=0;i<allCards.size();i++){
            int rank= Utility.getRankValue(allCards.get(i).getRank()) - 2;
            rankFreq[rank]++;
        }
        return rankFreq;
    }

    public int[] findSuitFrequency(){
        int[] rankSuit = new int[4];

        for(int i=0;i<allCards.size();i++){
            String suit = allCards.get(i).getSuit();
           
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
