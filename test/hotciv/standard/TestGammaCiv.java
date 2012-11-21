package hotciv.standard;

import hotciv.CivForms.*;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 21:09
 * To change this template use File | Settings | File Templates.
 */
public class TestGammaCiv {

    private Game game;

    @Before
    public void setUp(){
        game = new GameImpl(new AlphaCivAge(), new AlphaCivWin(),new AlphaCivMovement(), new AlphaCivMap(), new AlphaCivUnitAction());

    }
    /* test til gamma civ
    @Test
    public void archersShouldHaveDefenceOf3BeforeAction() {
        Unit u = game.getUnitAt(new Position(2,0));
        assertEquals("Acher defence should be 3", 3, u.getDefensiveStrength());
    }
    @Test
	public void archersShouldHaveDefenceOf6AfterAction() {
		game.performUnitActionAt(new Position(2,0));
		Unit u = game.getUnitAt(new Position(2,0));
		assertEquals("Acher defence should be 6", 6, u.getDefensiveStrength());
	}

	@Test
	public void archersShouldBeHaveDefenceOf3After2Actions() {
		game.performUnitActionAt(new Position(2,0));
		game.performUnitActionAt(new Position(2,0));
		Unit u = game.getUnitAt(new Position(2,0));
		assertEquals("Acher defence should be 3", 3, u.getDefensiveStrength());
	}

	@Test
	public void archersShouldNotBeAbleToMoveAfterAction() {
		game.performUnitActionAt(new Position(2,0));
		boolean b = game.moveUnit(new Position(2,0), new Position(2,1));
		assertFalse("Action should cause unit to not be able to move", b);
	}

	@Test
	public void fortifiedArchersMovementShouldNotBeRestored() {
		game.performUnitActionAt(new Position(2,0));
		game.endOfTurn(); game.endOfTurn();
		boolean b = game.moveUnit(new Position(2,0), new Position(2,1));
		assertFalse("Fortified unit's movement should not be restored", b);
	}

	@Test
	public void redSettlerShouldBeAbleToBuildCity() {
		game.performUnitActionAt(new Position(4,3));
		City c = game.getCityAt(new Position(4,3));
		assertNotNull("Should be city at (4,3)", c);
		assertNull("Settler should be removed", game.getUnitAt(new Position(4,3)));
		assertEquals("City's owner should be red", Player.RED, c.getOwner());
		CityImpl cStart = (CityImpl)game.getCityAt(new Position(1, 1));//Uses alpha civ map so redudant
		assertEquals("City population should be one", 1, cStart.getSize());

	}

	@Test
	public void redSettlerShouldNotBeAbleToPerformActionOnBluePlayersTurn() {
		game.endOfTurn();
		Position p = new Position(4,3);
		game.performUnitActionAt(p);
		assertNull("Red city should not be built on blue players turn", game.getCityAt(p));
	}
    */



}
