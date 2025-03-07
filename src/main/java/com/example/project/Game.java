package com.example.project;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class


public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
       //gets rankings
        int p1Result = Utility.getHandRanking(p1Hand);
        
        int p2Result = Utility.getHandRanking(p2Hand);
        //checks if already clear to see who wins
        if(p1Result>p2Result){
            return "Player 1 wins!";
        } else if(p2Result>p1Result)
            return "Player 2 wins!";
        else{
            //compare full hand using freq list

            int[] freq1 = p1.findRankingFrequency();
            int[] freq2 = p2.findRankingFrequency();
            for(int i =freq1.length-1;i>=0;i--){
                if(freq1[i] > freq2[i]){
                    //by checking from last to first, the first difference wll be the greatest card, showing who has high card
                    return "Player 1 wins!";
                }
                else if(freq2[i] > freq1[i]){
                    return "Player 2 wins!";
                }
            }
            return "Tie!";

        }
    }

    public static void play(){ //simulate card playing
        Scanner scan = new Scanner(System.in);
        //print out welcome
        System.out.println("------------------------------------");
        System.out.println("  WELCOME TO YOUR NEWEST OBSESSION");
        System.out.println("            SIMPLE POKER");
        System.out.println("------------------------------------");

        //start the game loop 
        boolean finished = false;
        int p1S = 0;
        int p2S = 0;
        while(!finished){
            //print out options
            System.out.println("OPTION 1: Start new round!");
            System.out.println("OPTION 2: Check score");
            System.out.println("OPTION 3: Quit :(");

            int op = scan.nextInt();

            if(op == 1){
                //start loop for simulating a round

                //setup for deck andloop
                Deck deck = new Deck();
                Player p1 = new Player();
                Player p2 = new Player();
                ArrayList<Card> comCards = new ArrayList<>();
                p1.addCard(deck.drawCard());
                p2.addCard(deck.drawCard());

                //start loops for 3 rounds

                //see if game ends early
                boolean roundFin = false;
                //count number of rounds
                int index =3;

                while((!roundFin) && (index >0)){

                    if(index == 3){
                        //if first time, only see one card, similar to how regular poker works i.e the buy in
                        System.out.println("Your hand is the following:");
                        System.out.println(p1.getHand());
                        System.out.println("Would you like to continue? 0 for no, 1 for yes");
                        int choice = scan.nextInt();
                        if(choice == 0){
                            roundFin = true;
                        }
                    }
                    if(index == 2){
                        //make community cardss
                        for(int i=0;i<3;i++){
                            comCards.add(deck.drawCard());
                        }
                        //show the cards
                        System.out.println("These are the community cards: ");
                        for(int i=0;i<3;i++){
                            System.out.println(comCards.get(i));
                        }
                        //allow final choice before doing the winner/loser calcuations
                        System.out.println("Would you like to continue? 0 for no, 1 for yes");
                        int choice = scan.nextInt();
                        if(choice == 0){
                            roundFin = true;
                        }

                    }
                    if(index == 1){
                        //do the winner calcuations and show full hand

                        //add second cards
                        p1.addCard(deck.drawCard());
                        p2.addCard(deck.drawCard());

                        //show cards
                        System.out.println("Your hand is the following:");
                        System.out.println(p1.getHand());

                        

                        //get hand rankings
                        String resultP1 = p1.playHand(comCards);

                        String resultP2 = p2.playHand(comCards);

                        //do final calc
                        String win = determineWinner(p1, p2, resultP1, resultP2, comCards);

                        //assign score
                        if( win.equals("Player 1 wins!")){
                            p1S++;
                            System.out.println(win);
                            
                        }else if(win.equals("Player 2 wins!")){
                            p2S++;
                            System.out.println(win);
                        }else{
                            System.out.println("Tie!");
                        }
                        roundFin = true;

                    }
                    index--;

                    
                }

            }else if(op == 2){
                //prints out win record
                System.out.println("Wins to losses: " + p1S + " : " + p2S);
            }else if(op == 3){
                //ends early
                finished = true;
            }else{
                System.out.println("Please pick again!");
            }



        }   

        
    }
        
        

}