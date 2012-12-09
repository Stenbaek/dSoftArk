package hotciv.standard;

import hotciv.factories.SemiFactory;
import hotciv.framework.*;

import hotciv.teststubs.FixedTestStubCivDieRoll;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 09-12-12
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
public class TestGameTranscript {
    private Game game;

    @Before
    public void setUp() {
        game = new GameTranscript(new GameImpl(new SemiFactory(new FixedTestStubCivDieRoll())));
    }

    @Test
    public void settlerShouldBuildACity() {
        Unit u = game.getUnitAt(new Position(5, 5));
        game.moveUnit(new Position(5, 5), new Position(6, 5));
        assertEquals("there is a settler at position 6,5",
                GameConstants.SETTLER, u.getTypeString());
        game.performUnitActionAt(new Position(6, 5));
        u = game.getUnitAt(new Position(6, 5));
        assertNull("the settler is gone", u);
        assertNotNull("there is a city at 6,5", game.getCityAt(new Position(6,5)));
    }
    @Test
    public void endOfTurnTest() {
        Player p = game.getPlayerInTurn();
        assertEquals("It's Red's turn", Player.RED, p);
        game.endOfTurn();
        p = game.getPlayerInTurn();
        assertEquals("It's Blue's turn now", Player.BLUE, p);

    }


    @Test
    public void unitShouldBeDefeatedInAttack() {
        Unit u = game.getUnitAt(new Position(5, 5));
        assertEquals("There is a (red) settler at 5,5", GameConstants.SETTLER,
                u.getTypeString());
        game.moveUnit(new Position(5, 5), new Position(4, 4));
        u = game.getUnitAt(new Position(5, 5));
        assertNull("reds settler is not at 5,5", u);
        u = game.getUnitAt(new Position(4, 4));
        assertEquals("There is a blue legion at 4,4", GameConstants.LEGION,
                u.getTypeString());
    }
}
