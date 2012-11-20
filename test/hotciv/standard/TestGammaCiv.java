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
        game = new GameImpl(new AlphaCivMap(),new AlphaCivWin(),new AlphaCivAge(), new GammaCivMovement());

    }



}
