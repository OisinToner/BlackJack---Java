
package blackjack;

import java.util.Scanner;
import java.util.List;
/**
 * Blackjack - Will hold main method and all logic
 * @author Oisin Toner
 */
public class Blackjack {
    
    public static void main(String[] args) {                                    
        Scanner keyb = new Scanner(System.in);
        boolean is_valid = false;
        
        while(is_valid == false) // <------------------------------------------- Keeps code running in loop until user enters valid input
        {
            System.out.print("Would you like to play or quit? (P to Play or Q to Quit): "); // <- Initial user choice
            String mChoice = keyb.next();

            if(mChoice.equalsIgnoreCase("Q")) // <------------------------------ User enters "Q" and program will end
            {
                is_valid = true;
                System.out.println("Thank you...\t\tProgram Ending.");
                System.exit(0);
            }//if
            else if(mChoice.equalsIgnoreCase("P")) // <------------------------- User enters "P" and program continues to game logic
            {
                is_valid = true;
                System.out.println("Game Loading...");
                try
                {
                    Thread.sleep(2000); // <------------------------------------ Creates deliberate delay for user (2000 milliseconds AKA 2 seconds)
                }//try
                catch (InterruptedException ex) // <---------------------------- Exception handling
                {
                    System.out.println("Error in load wait");
                }//catch
                System.out.println("Game Loaded");

                AppData appData = new AppData();
                List<String> playerCards = appData.dealCards(); // <------------ Calling function to generate cards for player
                List<String> dealerCards = appData.dealCards(); // <------------ Calling function to generate cards for dealer
                System.out.println("\n\nYou were dealt a " + playerCards.get(0) + " and a " + playerCards.get(1));
                
                int totalP = appData.calcCardTotal(playerCards); // <----------- Calling function to calculate total for player
                System.out.println("Your total is " + totalP);
                int totalD = appData.calcCardTotal(dealerCards); // <----------- Calling function to calculate total for dealer
                System.out.println("The dealers total is " + totalD);
                
                boolean draw = true;
                while(draw == true && totalP <= 21 && totalD <= 21) // <-------- Only runs if player and dealer are both still under 21
                {
                    System.out.print("\nWould you like to draw a new card? (Y/N): ");
                    String drawChoice1 = keyb.next();
                    if(totalP >= 22)
                    {
                        draw = false; // <-------------------------------------- Stops running if player is over 21
                    }//if
                    else if(totalD >= 22)
                    {
                        draw = false; // <-------------------------------------- Stops running if dealer is over 21
                    }//else if
                    else if(drawChoice1.equalsIgnoreCase("Y") && totalP < 21)
                    {
                        boolean check1 = true;
                        boolean check2 = true;
                        while(check1 && check2 == true)
                        {
                        String newPCard = appData.genNewCard(); // <------------ Generates new card
                        playerCards.add(newPCard); // <------------------------- Adding new card to players list
                        totalP = appData.calcCardTotal(playerCards); // <------- Calling function to calculate total for player
                        System.out.println("Your new total is " + totalP);
                        if(totalD < totalP)
                        {
                            System.out.println("\nThe dealer is drawing a card");
                            String newDCard = appData.genNewCard(); // <-------- Generates new card
                            dealerCards.add(newDCard); // <--------------------- Adding new card to dealers list
                            totalD = appData.calcCardTotal(dealerCards);
                        }//if
                        System.out.println("The dealers current total is "+ totalD);
                        check1 = appData.checkBust(totalP); // <---------------- function to check if player is bust
                        check2 = appData.checkDealerBust(totalD); // <---------- Calling function to calculate total for dealer
                        }//while
                    }//if
                    else if(drawChoice1.equalsIgnoreCase("N") && totalP < 21)
                    {
                        draw = false; // <-------------------------------------- Drops out of loop
                    }//else if
                    else
                    {
                        System.out.println("Invalid Input");
                    }//else
                }//while
                
                while(totalD < totalP) // <------------------------------------- While the dealers total is lower, it will keep drawing cards till above or bust
                {
                    System.out.println("The dealer is drawing a card");
                    String newDCard = appData.genNewCard(); // <---------------- Generates new card
                    dealerCards.add(newDCard); // <----------------------------- Adds new card to the dealers list
                    totalD = appData.calcCardTotal(dealerCards); // <----------- Calculating new total for dealer
                }//while
                
                totalP = appData.calcCardTotal(playerCards); // <--------------- Recalculates players total
                totalD = appData.calcCardTotal(dealerCards); // <--------------- Recalculates dealers total
                
                if(totalP > 21 && totalD > 21) // <----------------------------- If player & dealer are bust
                {
                    System.out.println("Your total is " + totalP);
                    System.out.println("The dealers total is "+ totalD);
                    System.out.println("You are both over 21. You both lose.");
                }//if
                else if(totalP > 21) // <--------------------------------------- If only player is over 21
                {
                    System.out.println("Your total is " + totalP);
                    System.out.println("The dealers total is "+ totalD);
                    System.out.println("You are bust. Dealer Wins");
                }//else if
                else if(totalD > 21) // <--------------------------------------- If only dealer is over 21
                {
                    System.out.println("Your total is " + totalP);
                    System.out.println("The dealers total is "+ totalD);
                    System.out.println("The dealer is bust. You Win");
                }//else if
                else if(totalP > totalD) // <----------------------------------- If player wins
                {
                    System.out.println("Your total is " + totalP);
                    System.out.println("The dealers total is "+ totalD);
                    System.out.println("You Win. Well Done");
                }//else if
                else if(totalD == totalP) // <---------------------------------- If Dealer and Player tie
                {
                    System.out.println("Your total is " + totalP);
                    System.out.println("The dealers total is "+ totalD);
                    System.out.println("You and the dealer drew");
                }//else if
                else // <------------------------------------------------------- Defaults to the dealer wins
                {
                    System.out.println("Your total is " + totalP);
                    System.out.println("The dealers total is "+ totalD);
                    System.out.println("The Dealer Wins. Better luck next time");
                }//else
                
            }//else if
            else
            {
                System.out.println("Invalid Input");
            }//else
        }//while
        
        System.out.println("\nThank you for playing. goodbye");
        
    }//main
}//class