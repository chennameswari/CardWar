package com.game.war;

import java.util.LinkedList;
import java.util.List;

import com.game.war.deck.Card;

public class Player {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private int id;
	private LinkedList<Card> deck;
	private Card currentCard = null;
	
	public Player(int id, LinkedList<Card> deck) {
		super();
		this.id = id;
		this.deck = deck;
	}

	public Card getNextCard(){
		if(deck != null){
			currentCard = deck.poll();
			
		}else{
			currentCard = null;
		}
		return currentCard;
	}
	
	public Card getCurrentCard(){
		return currentCard;
	}
	
	public void addLast(Card card){
		if(deck != null){
			deck.addLast(card);
		}
	}
	
	public void add(Card card){
		if(deck != null){
			deck.add(card);
		}
	}
	
	public boolean isEmpty(){
		if(deck != null){
			return deck.isEmpty();
		}else{
			return true;
		}
	}
	
	public void addPot(List<Card> pot){
		for (Card card : pot) {
			addLast(card);
		}
	}
	
	public void displayCards() {
		for (Card card : deck) {
			System.out.println(card);
		}
	}
	
	
}
