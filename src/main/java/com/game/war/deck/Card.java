package com.game.war.deck;

public class Card implements Comparable<Card>{
	private int rank;
	private int suite;

	public Card(int rank, int suite) {
		super();
		this.rank = rank;
		this.suite = suite;
	}

	public int compareTo(Card o) {
		if(o == null){
			return 1;
		}
		if(this.equals(o)){
			return 0;
		}else{
			if(this.rank == o.rank){
				return this.hashCode()-o.hashCode();
			}else{
				return this.rank = o.rank;
			}
		}
	}
	
	@Override
	public int hashCode() {
		return suite*rank;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Card){
			return (this.hashCode() == obj.hashCode());
		}else{
			return false;
		}
		
	}
	
	@Override
	public String toString() {
		String result = "";
		if( rank <= 10){
			result = rank+"";
		}else{
			if(rank == 11){
				result = "J";
			}else if(rank == 12){
				result = "Q";
			}else if(rank == 13){
				result = "K";
			}else if(rank == 14){
				result = "A";
			}
		}
		
		switch (suite) {
		case 1:
			result = result + " - Spades";
			break;
		case 2:
			result = result + " - Hearts";
			break;
		case 3:
			result = result + " - Clubs";
			break;
		case 4:
			result = result + " - Diamonds";
			break;
		default:
			break;
		}
		return result;
	}
}
