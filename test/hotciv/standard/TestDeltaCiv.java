package hotciv.standard;

import hotciv.factories.DeltaFactory;
import hotciv.standard.tiles.Forest;
import hotciv.standard.tiles.Mountain;
import hotciv.standard.tiles.Plain;
import hotciv.strategies.*;
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
public class TestDeltaCiv {

    private Game game;

    @Before
    public void setUp(){
        game = new GameImpl(new DeltaFactory());

    }

    @Test
    public void thereShouldBeACityAt8_12(){
        assertNotNull("There should be a Settler at (8,12)",game.getCityAt(new Position(8, 12)));
        assertEquals("City should be at (8,12)", CityImpl.class, game.getCityAt(new Position(8,12)).getClass());
    }

    @Test
    public void thereShouldBeAMountainAt0_5(){
        assertNotNull("There should be a Mountain at (0,5)",game.getTileAt(new Position(0, 5)));
        assertEquals("Mountain should be at (0,5)", Mountain.class, game.getTileAt(new Position(0, 5)).getClass());
    }

    @Test
    public void thereShouldBeAForestAt5_5(){
        assertNotNull("There should be a Forest at (5,5)",game.getTileAt(new Position(5, 5)));
        assertEquals("Forest should be at (5,5)", Forest.class, game.getTileAt(new Position(5, 5)).getClass());
    }

    @Test
    public void thereShouldBeAPlainAt5_6(){
        assertNotNull("There should be a Plain at (5,6)",game.getTileAt(new Position(5, 6)));
        assertEquals("Plain should be at (5,6)", Plain.class, game.getTileAt(new Position(5, 6)).getClass());
    }

    @Test
    public void thereShouldBeATileAtEveryCoordinate(){
        for(int r = 0; r < GameConstants.WORLDSIZE; r++){
            for(int c = 0; c < GameConstants.WORLDSIZE; c++){
                assertNotNull("There should be a Tile at (" + r + "," + c + ") " + game.getTileAt(new Position(r,c)).getClass(), game.getTileAt(new Position(r,c)));
            }
        }
    }

}