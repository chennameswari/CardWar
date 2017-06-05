package com.game.war;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import com.game.war.deck.Card;
import com.game.war.deck.Deck;

public class DeckImpl implements Deck {
	
	LinkedList<Card> deck = new LinkedList<Card>();

	public void create(int numberOfSuits, int numberOfRanks) {
		for (int i = 0; i < numberOfSuits; i++) {
			for (int j = 0; j < numberOfRanks; j++) {
				deck.add(new Card(j+2, i+1));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(deck, new Random());
	}

	public Card deal() {
		if(deck.isEmpty()){
			return null;
		}
		return deck.poll();
	}
	
	public void displayCard(){
		for (Card card : deck) {
			System.out.println(card);
		}
	}

}
