package hotciv.standard;

import hotciv.factories.AlphaFactory;
import hotciv.factories.EpsilonFactory;
import hotciv.standard.units.Legion;
import hotciv.strategies.*;
import hotciv.framework.*;

import hotciv.standard.tiles.*;
import hotciv.standard.units.Archer;
import org.junit.*;

import static org.junit.Assert.*;

/** Skeleton class for AlphaCiv test cases

 This source code is from the book
 "Flexible, Reliable Software:
 Using Patterns and Agile Development"
 published 2010 by CRC Press.
 Author:
 Henrik B Christensen
 Computer Science Department
 Aarhus University

 This source code is provided WITHOUT ANY WARRANTY either
 expressed or implied. You may study, use, modify, and
 distribute it for non-commercial purposes. For any
 commercial use, see http://www.baerbak.com/
 */
public class TestEpsilonCiv {
    private Game game;
    /** Fixture for alphaCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonFactory(new EpsilonCivDieRoll()));
    }

   /* @Test
    public void redShouldWinAfter3SuccessfulAttacks(){
        game.addUnit(new Position(3,2), new Legion(Player.RED));
        game.addUnit(new Position(4,3), new Settler(Player.BLUE));
        game.moveUnit(new Position(3,2), new Position(4,3));
        assertEquals("Red should have won 1 attack", 1,
        game.endOfTurn(); game.endOfTurn();
        game.addUnit(new Position(3,2), new Settler(Player.BLUE));
        game.moveUnit(new Position(4,3), new Position(3,2));
        assertEquals("Red should have won 2 attack", 2, getAttacksWon()(Player.RED));
        game.endOfTurn(); game.endOfTurn();
        game.addUnit(new Position(4,3), new Settler(Player.BLUE));
        game.moveUnit(new Position(3,2), new Position(4,3));
        assertEquals("Red should have won 3 attacks", 3, game.getAttacksWon(Player.RED));
        assertEquals("Red should win after 3 legions won against 3 settlers",
                Player.RED, game.getWinner());

    }  */
    @Test
    public void redSettlerShouldnotBeAbleToKillBlueLegion(){
        game.moveUnit(new Position(4,3), new Position(3,2));
        assertEquals("There should still be a legion at (3,2)", Legion.class,
                game.getUnitAt(new Position(3,2)).getTypeString());

    }
}
