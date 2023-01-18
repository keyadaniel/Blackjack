package com.praeses.blackjackdemo;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {
		
		//Welcome Message
		System.out.println("Welcome to Blackjack!");
		
		//Create our playing deck
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		
		//Create a deck for the player and dealer
		Deck playerHand = new Deck();
		Deck dealerHand = new Deck();
		
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("How much money would you like to enter the game with? Bets for each hand can you range from $2 - $500 dollars");
		double playerMoney = userInput.nextDouble();
		
		
		
		//Game Loop
		while(playerMoney > 0) {
			//Play
			//Take the player's bet
			System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
			double playerBet = userInput.nextDouble();
			if (playerBet > playerMoney) {
				System.out.println("You cannot bet more money than you have");
				break;
			}
			
			boolean endRound = false;
			
			//Dealing
			//Player gets two cards
			playerHand.draw(playingDeck);
			playerHand.draw(playingDeck);
			
			//Dealer gets two cards
			dealerHand.draw(playingDeck);
			dealerHand.draw(playingDeck);
			
			// end round for instant Blackjack
			if(playerHand.cardsValue() == 21) {
				playerMoney += playerBet * 2;
				endRound = true;
				break;
			}
			
			//hits
			while(true) {
				System.out.println("Your hand: ");
				System.out.println(playerHand.toString());
				System.out.println("Your have: " + playerHand.cardsValue());
				
				
			
				
				//Display Dealer Hand
				System.out.println("Dealer hand: " + dealerHand.getCard(0).toString() + " and [Hidden]");
				
				
				
				
				//What does the player want to do?
				System.out.println("Would you like to (1)Hit or (2)Stand? ");
				int response = userInput.nextInt();
				
				
				//They Hit 
				if(response == 1) {
					playerHand.draw(playingDeck);
					System.out.println("You drew a: " + playerHand.getCard(playerHand.deckSize()-1).toString());
					//Bust if > 21
					if(playerHand.cardsValue() > 21) {
						System.out.println("Bust! " + playerHand.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}
				
				if(response ==2) {
					break;
				}
			}
			
			//Reveal Dealer Card
			System.out.println("Dealer Cards: " + dealerHand.toString());
			//See if dealer has more points than player
			if((dealerHand.cardsValue() > playerHand.cardsValue())&& endRound == false) {
				System.out.println("Dealer wins!");
				playerMoney -= playerBet;
				endRound = true;
			}
			//Dealer Draws at 16, stand at 17
			while((dealerHand.cardsValue() < 17) && endRound == false){
				dealerHand.draw(playingDeck);
				System.out.println("Dealer drew: " + dealerHand.getCard(dealerHand.deckSize()-1).toString());
			}
			//Display total value for dealer 
			System.out.println("Dealer has " + dealerHand.cardsValue());
			
			//Determine if Dealer busted 
			if((dealerHand.cardsValue() > 21)&& endRound == false) {
				System.out.println("Dealer Bust, You Win!");
				playerMoney += playerBet;
				endRound = true;
			}
			
			//Determine if push
			if((playerHand.cardsValue() == dealerHand.cardsValue())&& endRound == false) {
				System.out.println("Push");
				endRound = true;
			}
			
			//Dealer lose 
			if((dealerHand.cardsValue() < playerHand.cardsValue())&& endRound == false) {
				System.out.println("You Win!");
				playerMoney += playerBet;
				endRound = true;
			}
			
			//if dealer wins after drawing
			if ((dealerHand.cardsValue() > playerHand.cardsValue())&& endRound == false) {
				System.out.println("Dealer Wins!");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			playerHand.moveAllToDeck(playingDeck);//top of deck
			dealerHand.moveAllToDeck(playingDeck);
			System.out.println("End of hand.");
		}
		
		System.out.println("Game Over");
		

	}

}
