package hotciv.standard;

import hotciv.CivForms.*;
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
public class TestDeltaCiv {

    private Game game;

    @Before
    public void setUp(){
        game = new GameImpl(new AlphaCivAge(), new AlphaCivWin(),new AlphaCivMovement(), new DeltaCivMap(), new AlphaCivUnitAction());

    }

    @Test
    public void thereShouldBeACityAt8_12(){
        assertNotNull("There should be a Settler at (8,12)",game.getCityAt(new Position(8,12)));
    }

}