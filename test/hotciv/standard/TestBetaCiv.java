package hotciv.standard;

import static org.junit.Assert.*;

import hotciv.CivForms.*;
import org.junit.Before;
import org.junit.Test;

import hotciv.framework.*;


/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 16-11-12
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
public class TestBetaCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new BetaCivAge(),new BetaCivWin(),new AlphaCivMovement(),new AlphaCivMap(),new AlphaCivUnitAction());
    }
    @Test
    public void startAgeShouldBe4000BC(){
        assertEquals("Starting age should be 4000BC",-4000,game.getAge());
    }
   @Test
    public void ageShouldAdvanceTimeBy100AtEndOfFirstRound() {
    game.endOfTurn();
    game.endOfTurn();
    assertEquals("Ending a round the time should be incremented by 100",
            -3900, game.getAge());
}
    @Test
    public void ageShouldAdvanceFrom100BCTo1BC() {
        for(int i=0; i<78; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 100BC", -100, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1BC", -1, game.getAge());
    }
    @Test
    public void ageShouldAdvanceFrom1BCTo1AD(){
        for(int i=0; i<80; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1BC", -1, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1AD", 1, game.getAge());
    }
    @Test
    public void ageShouldAdvanceFrom1ADTo50AD(){
        for(int i=0; i<82; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1AD", 1, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 50AD", 50, game.getAge());
    }
    @Test
    public void ageShouldAdvanceFrom1700ADTo1750AD(){
        for(int i=0; i<150; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1700AD", 1700, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1750AD", 1750, game.getAge());

    }
    @Test
    public void ageShouldAdvanceFrom1875ADTo1900AD(){
        for(int i=0; i<162; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1875AD", 1875, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1900AD", 1900, game.getAge());

    }
    @Test
    public void ageShouldAdvanceFrom1900ADTo1905AD() {
        for(int i=0; i<164; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1900AD", 1900, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1905AD", 1905, game.getAge());
    }
    @Test
    public void ageShouldAdvanceFrom1YearFrom1970AD(){
        for(int i=0; i<192; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1970AD", 1970, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1971AD", 1971, game.getAge());

    }
    @Test
    public void intiallyNoPlayerShouldHaveWon() {
        assertNull("No player should have won at the start of the game", game.getWinner());
    }
}
