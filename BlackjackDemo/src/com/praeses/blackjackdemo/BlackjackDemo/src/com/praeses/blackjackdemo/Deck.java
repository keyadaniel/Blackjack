package com.praeses.blackjackdemo;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;

	// constructor
	public Deck() {
		this.cards = new ArrayList<Card>();
	}

	public void createFullDeck() {
		// Generate cards
		for (Suit cardSuit : Suit.values()) {
			for (Value cardValue : Value.values()) {
				// Add a new card to the deck
				this.cards.add(new Card(cardSuit, cardValue));
			}
		}
	}

	public void shuffle() {
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		// Use Random to generate random numbers
		Random random = new Random();
		int randomCardIndex = 0;
		int originalSize = this.cards.size();
		for (int i = 0; i < originalSize; i++) {
			// Generate random Index //rand.nextInt((max-min) + 1) + min;
			randomCardIndex = random.nextInt((this.cards.size() - 1 - 0) + 1) + 0;
			tmpDeck.add(this.cards.get(randomCardIndex));
			// Remove from original deck
			this.cards.remove(randomCardIndex);
		}

		this.cards = tmpDeck;

	}

	public String toString() {
		String cardListOutput = "";
		for (Card aCard : this.cards) {
			cardListOutput += "\n" + aCard.toString();
		}
		return cardListOutput;
	}

	// Getters & Setters
	public void removeCard(int i) {
		this.cards.remove(i);
	}

	public Card getCard(int i) {
		return this.cards.get(i);
	}

	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}

	// Draws from the deck
	public void draw(Deck comingFrom) {
		this.cards.add(comingFrom.getCard(0));
		comingFrom.removeCard(0);
	}

	// Amount of cards in deck
	public int deckSize() {
		return this.cards.size();
	}

	public void moveAllToDeck(Deck moveTo) {
		int thisDeckSize = this.cards.size();

		// put cards in move to deck
		for (int i = 0; i < thisDeckSize; i++) {
			moveTo.addCard(this.getCard(i));
		}

		for (int i = 0; i < thisDeckSize; i++) {
			this.removeCard(0);
		}
	}

	// Return total value of cards in deck
	public int cardsValue() {
		int totalValue = 0;
		int aces = 0;
		int findLowAce = 10;
		int lowAce = 1;
		int highAce = 11;

		for (Card aCard : this.cards) {
			totalValue += aCard.getValue().getCardValue();
		}

		for (int i = 0; i < aces; i++) {
			if (totalValue > findLowAce) {
				totalValue += lowAce;
			} else {
				totalValue += highAce;
			}
		}

		return totalValue;

	}

}
