package com.game.war;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Game Started!!");
    	War war = new War();
    	war.play(4, 13, 4);
    	System.out.println("Game Ended!!");
    }
}
