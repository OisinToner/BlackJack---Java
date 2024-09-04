
package blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * AppData - Will hold all the functions for dealing & calculating in the game
 * @author Oisin Toner
 */

public class AppData {
    
    Scanner keyb = new Scanner(System.in);
    
    public List<String> dealCards()
    {
        Random rand = new Random();
        List<String> cards = new ArrayList<>();
        
        
        int card1 = rand.nextInt(13) + 1;
        int card2 = rand.nextInt(13) + 1;
        
        cards.add(getCardName(card1));
        cards.add(getCardName(card2));
            
        return cards;    
    }//dealCards
    
    private String getCardName(int cardValue) {
        switch(cardValue)
        {
            case 1:
                return "Ace";
            case 11:
                return "Jack";
            case 12:
                return "Queen";
            case 13:
                return "King";
            default:
                return String.valueOf(cardValue); // <-------------------------- Converts number value to string for cards 2 to 10
        }//switch
    }//getCardName
    
    public int calcCardTotal(List<String> cards) {
        int total = 0;
        
        for (String card : cards) {
            switch (card) {
                
                case "Jack":
                case "Queen":
                case "King":
                    total += 10;
                    break;
                case "Ace":
                    if((total + 11) <= 21)
                    {
                        boolean repeat = false;
                        while (!repeat)
                        {
                            System.out.println("You got an Ace. Do you want it to be a 1 or 11?");

                            // Ensure you prompt and check for valid input
                            if (keyb.hasNextInt())
                            {
                                int aceChoice = keyb.nextInt();

                                if (aceChoice == 1)
                                {
                                    total += 1;
                                    repeat = true;
                                }//if
                                else if (aceChoice == 11)
                                {
                                    total += 11;
                                    repeat = true;
                                }//else if
                                else
                                {
                                    System.out.println("Invalid Input. Please enter 1 or 11.");
                                }//else
                            }//if
                            else
                            {
                                System.out.println("Invalid Input. Please enter a number.");
                                keyb.next();
                            }//else
                        }//while
                    }//if
                    else
                    {
                        System.out.println("Ace defaulted to 1. 11 would've gone over 21");
                        total += 1;
                    }//else
                    break;
                default:
                    try
                    {
                        total += Integer.parseInt(card); // <------------------- Converting number card values (2-10) from String to int
                    }//try
                    catch (NumberFormatException e)
                    {
                        System.out.println("Unexpected card value: " + card);
                    }//catch
                    break;
            }//switch
        }//for
        
        return total;
    }//calcCardTotal
    
    public String genNewCard()
    {
        Random rand = new Random();
        int newCard = rand.nextInt(13) + 1;
        
        String cardName = getCardName(newCard);
        System.out.println("\nThe new card is: " + cardName);
        
        return cardName;
        //return newCard;
    }//genNewCard
    
    public boolean checkBust(int totalP)
    {
        boolean decision = false;
        
        if(totalP > 21)
        {
            System.out.println("You're over 21");
        }//if
        
        return decision;
    }//checkBust
    
    public boolean checkDealerBust(int totalD)
    {
        boolean decision = false;
        
        if(totalD > 21)
        {
            System.out.println("Dealer over 21, She's bust");
        }//if
        
        return decision;
    }//checkBust
    
}//class
