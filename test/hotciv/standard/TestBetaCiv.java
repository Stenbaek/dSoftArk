package hotciv.standard;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hotciv.framework.*;

import hotciv.CivForms.BetaCivAge;


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
        game = new GameImpl(new BetaCivAge());
    }
    @Test
    public void startAgeShouldBe4000BC(){
        assertEquals("Starting age should be 4000BC",-4000,game.getAge());
    }
}
