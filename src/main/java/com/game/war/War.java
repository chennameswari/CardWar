package com.game.war;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.game.war.deck.Card;

public class War {
	
	
	public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers) {
		if(numberOfSuits > 4 || numberOfRanks > 13){
			throw new RuntimeException("Illegal Input Exception. Suits should be less 3 and ranks should be less than 13");
		}
		if(numberOfPlayers <= 1 || numberOfPlayers > 52){
			throw new RuntimeException("Illegal Input Exception. Number of players should alteast 2 and less than 52");
		}
		
		DeckImpl masterDeck = new DeckImpl();
		masterDeck.create(numberOfSuits, numberOfRanks);
		masterDeck.displayCard();
		masterDeck.shuffle();
		masterDeck.displayCard();
		//Creating players
		List<Player> players = new ArrayList<Player>();
		for (int i = 0; i < numberOfPlayers; i++) {
			LinkedList<Card> temp = new LinkedList<Card>();
			players.add(new Player(i, temp));
		}
		
		//Distributing Cards
		int i = 0;
		while( i < numberOfPlayers){
			Card c = masterDeck.deal();
			if(c == null){
				break;
			}
			
				 players.get(i).add(c);
			
			i++;
			if( i == numberOfPlayers){
				i = 0;
			}
		}
		displayCards(players);
		
		//Start the game
		List<Player> contendors = getContendors(players);
		int round = 1;
		int maxRound = 500000;
		while(contendors.size() > 1){
			System.out.println("Round : " + round);
			this.findWinner(contendors, generatePot(contendors));
			contendors = getContendors(contendors);
			round++;
			
			if(round  > 500000){
				System.out.println("Cannot find solution in "+maxRound + "Rounds");
				break;
			}
		}
		System.out.println("The winner of the game is player "+ contendors.get(0).getId());
		System.out.println("************** Winner card **************");
		contendors.get(0).displayCards();
	}
	
	/*
	 * Find the valid contendors from the list of players.
	 * A valid contendor is a player with more than one card.
	 */
	private List<Player> getContendors(List<Player> players){
		List<Player> cont = new ArrayList<Player>();
		for (Player player : players) {
			if(!player.isEmpty()){
				cont.add(player);
			}
		}
		return cont;
	}
	
	/*
	 * This method takes the list of contendors and the pot.
	 * Checks the winner of the pot. If there is more than on e winner.
	 * It recursively increases the pot until a single winner is emerged.
	 */
	
	private List<Player> findWinner(List<Player> players, List<Card> pot){
		List<Player> winners = getRoundWinners(players);
		while(winners.size() > 1){
			List<Card> newPot = generatePot(players);
			pot.addAll(newPot);
			return findWinner(winners, pot);
		}
		if(winners.size() == 1){
			System.out.println("Winner of the round " + winners.get(0).getId());
			winners.get(0).addPot(pot);
		}
		return winners;
	}
	
	/*
	 * This method find the winner of each round by comparing cards.
	 * and returns the list of players.
	 * If there are more than one player having the highest card. There will be more then one winner
	 */
	private List<Player> getRoundWinners(List<Player> players){
		List<Player> winners = new ArrayList<Player>();
		Map<Card, ArrayList<Player>> map = new HashMap<Card, ArrayList<Player>>();
		Card maxCard = null;
		for (Player player : players) {
			if(player.getCurrentCard().compareTo(maxCard) > 0){
				maxCard  = player.getCurrentCard();
				if(map.containsKey(maxCard)){
					ArrayList<Player> temp = map.get(maxCard);
					temp.add(player);
					map.put(maxCard, temp);
				}else{
					ArrayList<Player> temp = new ArrayList<Player>();
					temp.add(player);
					map.put(maxCard, temp);
				}
			}else if (maxCard != null && player.getCurrentCard().compareTo(maxCard) == 0){
				maxCard  = player.getCurrentCard();
				if(map.containsKey(maxCard)){
					ArrayList<Player> temp = map.get(maxCard);
					temp.add(player);
					map.put(maxCard, temp);
				}else{
					ArrayList<Player> temp = new ArrayList<Player>();
					temp.add(player);
					map.put(maxCard, temp);
				}
			}
		}
		
		if(map.containsKey(maxCard)){
			winners = map.get(maxCard);
		}
		return winners;

	}
	
	/*
	 * This take a card from the all the contendors and puts it in a pot 
	 */
	private List<Card> generatePot(List<Player> players){
		List<Card> pot = new ArrayList<Card>();
		for (Player player : players) {
			pot.add(player.getNextCard());
		}
		return pot;
	}
	
	private void displayCards(List<Player> players) {
		for (Player player : players) {
			System.out.println("**************"+player.getId()+"*****************");
			player.displayCards();
			System.out.println("*******************************");
		}
	}
	
}
