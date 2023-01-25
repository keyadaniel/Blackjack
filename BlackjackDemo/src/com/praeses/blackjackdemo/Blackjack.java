package com.praeses.blackjackdemo;

import java.util.Scanner;

public class Blackjack {

	public static void main(String[] args) {

		int twentyone = 21;
		int dealerStand = 17;
		int hit = 1;
		int stand = 2;

		// Welcome Message
		System.out.println("Welcome to Blackjack!");

		// Create our playing deck
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();

		// Create a deck for the player and dealer
		Deck playerHand = new Deck();
		Deck dealerHand = new Deck();


		// Get Player's Bank
		Scanner userInput = new Scanner(System.in);

		double playerMoney = 0.0;
		boolean playerIsSettingBank = true;

		while (playerIsSettingBank) {
			System.out.println(
					"How much money would you like to enter the game with? Bets for each hand typically range from $2 - $500 dollars");
			try {
				playerMoney = Double.parseDouble(userInput.nextLine());
				playerIsSettingBank = false;
			} catch (NullPointerException e) {
				System.out.println("Please enter a response: " + e);
				userInput.nextLine();
			} catch (NumberFormatException e) {
				System.out.println("Your response does not contain a number, please try again: " + e);
				userInput.nextLine();
			}

		}

		// Game Loop
		while (playerMoney > 0) {
			
			// Take the player's bet
			System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
			double playerBet = userInput.nextDouble();
			
			if (playerBet > playerMoney) {
				System.out.println("You cannot bet more money than you have");
				break;
			}

			boolean endRound = false;

			// Deal player 2 cards 
			playerHand.draw(playingDeck);
			playerHand.draw(playingDeck);

			// Deal dealer 2 cards 
			dealerHand.draw(playingDeck);
			dealerHand.draw(playingDeck);

			// end round for instant Blackjack
			if (playerHand.cardsValue() == twentyone && dealerHand.cardsValue() != twentyone) {
				System.out.println(playerHand.toString());
				System.out.println(playerHand.cardsValue());
				System.out.println("BLACKJACK");
				playerMoney += (playerBet * 1.5);
				endRound = false;
				break;
			}

			// hit
			while (true) {
				System.out.println("Your hand: ");
				System.out.println(playerHand.toString());
				System.out.println("Your have: " + playerHand.cardsValue());

				// Display Dealer Hand
				System.out.println("Dealer hand: " + dealerHand.getCard(0).toString() + " and [Hidden]");

				// What does the player want to do?
				System.out.println("Would you like to (1)Hit or (2)Stand? ");
				int response = userInput.nextInt();

				// Hit
				if (response == hit) {
					playerHand.draw(playingDeck);
					System.out.println("You drew a: " + playerHand.getCard(playerHand.deckSize() - 1).toString());
					// Bust if > 21
					if (playerHand.cardsValue() > twentyone) {
						System.out.println("Bust! " + playerHand.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}

				if (response == stand) {
					break;
				}
			}

			// Reveal Dealer Card
			System.out.println("Dealer Cards: " + dealerHand.toString());
			// See if dealer has more points than player
			if ((dealerHand.cardsValue() > playerHand.cardsValue()) && endRound == false) {
				System.out.println("Dealer wins!");
				playerMoney -= playerBet;
				endRound = true;
			}
			// Dealer Draws at 16, stand at 17
			while ((dealerHand.cardsValue() < dealerStand) && endRound == false) {
				dealerHand.draw(playingDeck);
				System.out.println("Dealer drew: " + dealerHand.getCard(dealerHand.deckSize() - 1).toString());
			}
			// Display total value for dealer
			System.out.println("Dealer has " + dealerHand.cardsValue());

			// Determine if Dealer busted
			if ((dealerHand.cardsValue() > twentyone) && endRound == false) {
				System.out.println("Dealer Bust, You Win!");
				playerMoney += playerBet;
				endRound = true;
			}

			// Determine if push
			if ((playerHand.cardsValue() == dealerHand.cardsValue()) && endRound == false) {
				System.out.println("Push");
				endRound = true;
			}

			// Dealer lose
			if ((dealerHand.cardsValue() < playerHand.cardsValue()) && endRound == false) {
				System.out.println("You Win!");
				playerMoney += playerBet;
				endRound = true;
			}

			// if dealer wins after drawing
			if ((dealerHand.cardsValue() > playerHand.cardsValue()) && endRound == false) {
				System.out.println("Dealer Wins!");
				playerMoney -= playerBet;
				endRound = true;
			}

			playerHand.moveAllToDeck(playingDeck);// top of deck
			dealerHand.moveAllToDeck(playingDeck);
			System.out.println("End of hand.");
		}

		System.out.println("Game Over");

	}

}