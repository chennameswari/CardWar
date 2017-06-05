package com.game.war;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Testing the application with multiple players
     */
    public void testApp()
    {
    	War war = new War();
    	war.play(4, 13, 4);
    	
    	War war1 = new War();
    	war1.play(4, 13, 10);
        assertTrue( true );
    }
    
}
