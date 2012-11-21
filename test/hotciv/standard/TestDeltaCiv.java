package hotciv.standard;

import hotciv.factories.DeltaFactory;
import hotciv.standard.tiles.Mountain;
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
        assertNotNull("There should be a Settler at (0,5)",game.getTileAt(new Position(0, 5)));
        assertEquals("City should be at (0,5)", Mountain.class, game.getTileAt(new Position(0, 5)).getClass());
    }

}