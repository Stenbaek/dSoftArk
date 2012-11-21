package hotciv.standard;

import hotciv.factories.GammaFactory;
import hotciv.strategies.*;
import hotciv.framework.*;

import hotciv.standard.units.Archer;
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
        game = new GameImpl(new GammaFactory());

    }

    @Test
    public void settlerMakesCityWhenActionIsPerformed(){
        Position p = new Position(4,3);
        assertNotNull("There should be a Settler at (4,3)",game.getUnitAt(p));
        //Makes Settler perform his action (build city)
        game.performUnitActionAt(p);
        assertNull("There should be no settler at (4,3) now",game.getUnitAt(p));
        assertNotNull("There should be a city at (4,3) now",game.getCityAt(p));
    }

    @Test
    public void archerIsFortifiedAndCannotMoveAfterActionIsPerformed(){
        Position p = new Position(2,0);
        assertTrue("There should be an Archer at (2,0)", game.getUnitAt(p).getClass().equals(Archer.class));
        //Makes Settler perform his action (build city)
        game.performUnitActionAt(p);
        assertFalse("Unit should not be able to move, since it is fortified",game.moveUnit(p,new Position(2,1)));
        assertEquals("Archer's defensive Strength should be doubled (6)",6,game.getUnitAt(p).getDefensiveStrength());
        game.performUnitActionAt(p);
        assertTrue("Unit should not be able to move, since it is fortified",game.moveUnit(p,new Position(2,1)));
        assertEquals("Archer's defensive Strength should be normal (3)",3,game.getUnitAt(new Position(2,1)).getDefensiveStrength());
    }

    @Test
    public void archersShouldHaveDefenceOf3BeforeAction() {
        Unit u = game.getUnitAt(new Position(2,0));
        assertEquals("Archer defence should be 3", 3, u.getDefensiveStrength());
    }

    @Test
    public void archersShouldHaveDefenceOf6AfterAction() {
        game.performUnitActionAt(new Position(2,0));
        Unit u = game.getUnitAt(new Position(2,0));
        assertEquals("Archer defence should be 6", 6, u.getDefensiveStrength());
    }

    @Test
    public void archersShouldBeHaveDefenceOf3After2Actions() {
        game.performUnitActionAt(new Position(2,0));
        game.performUnitActionAt(new Position(2,0));
        Unit u = game.getUnitAt(new Position(2,0));
        assertEquals("Archer defence should be 3", 3, u.getDefensiveStrength());
    }

    @Test
    public void archersShouldNotBeAbleToMoveAfterAction() {
        game.performUnitActionAt(new Position(2,0));
        boolean b = game.moveUnit(new Position(2,0), new Position(2,1));
        assertFalse("Action should cause unit to not be able to move", b);
    }

    @Test
    public void redSettlerShouldNotBeAbleToPerformActionOnBluePlayersTurn() {
        game.endOfTurn();
        Position p = new Position(4,3);
        game.performUnitActionAt(p);
        assertNull("Red city should not be built on blue players turn", game.getCityAt(p));
    }

}
