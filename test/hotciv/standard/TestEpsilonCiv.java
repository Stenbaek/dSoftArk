package hotciv.standard;

import hotciv.factories.EpsilonFactory;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;
import hotciv.framework.*;

import hotciv.teststubs.FixedTestStubCivDieRoll;
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

    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonFactory(new FixedTestStubCivDieRoll()));
    }

    @Test
    public void redShouldWinAfter3SuccessfulAttacks(){
        for(int i = 0; i < 3; i++){
            game.addUnit(new Position(5,6),new Legion(Player.RED));
            game.addUnit(new Position(6,6),new Settler(Player.BLUE));

            game.moveUnit(new Position(5,6),new Position(6,6));
            game.removeUnit(new Position(6,6));
        }
        assertTrue("RED should be the winner after 3 successful attacks",(game.getWinner().equals(Player.RED)));
    }

    @Test
    public void blueShouldWinAfter3SuccessfulAttacks(){
        game.endOfTurn();
        for(int i = 0; i < 3; i++){
            game.addUnit(new Position(5,6),new Legion(Player.BLUE));
            game.addUnit(new Position(6,6),new Settler(Player.RED));

            game.moveUnit(new Position(5,6),new Position(6,6));
            game.removeUnit(new Position(6,6));
        }
        assertTrue("RED should be the winner after 3 successful attacks",(game.getWinner().equals(Player.BLUE)));
    }

    //Attacker gets deleted from board/world after attacker loses
    @Test
    public void attackerGetsDeletedFromWorldAfterLoss(){
        game.addUnit(new Position(5,6),new Legion(Player.BLUE));
        game.addUnit(new Position(6,6),new Settler(Player.RED));

        assertEquals("There should be a Settler at (6,6)", game.getUnitAt(new Position(6,6)).getClass(),Settler.class);
        assertEquals("There should be a Legion at (5,6)", game.getUnitAt(new Position(5,6)).getClass(),Legion.class);
        assertFalse("Settler should not be able to move to (5,6)",game.moveUnit(new Position(6,6),new Position(5,6)));

        assertEquals("Legion should be the only unit left after battle, at (5,6)",game.getUnitAt(new Position(5,6)).getClass(),Legion.class);
        assertNull("Settler should be deleted at (6,6)",game.getUnitAt(new Position(6,6)));
    }

}
