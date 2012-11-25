package hotciv.standard;

import hotciv.factories.ZetaFactory;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import org.junit.*;

import static org.junit.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 24-11-12
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
public class TestZetaCiv {
    private Game game;
    /** Fixture for alphaCiv testing. */
    @Before
    public void setUp() {
        game = new GameImpl(new ZetaFactory());
    }

    @Test
    public void after20TurnsYouDontWinByTakingAllTheCities() {
        game.moveUnit(new Position(2,0),new Position(3,0));
        for(int i=0; i<40;i++){
            game.endOfTurn();
            game.endOfTurn();
        }
        game.moveUnit(new Position(3,0),new Position(4,1));
        assertNull("No player should have won the game yet by being inactive",game.getWinner());

    }
    @Test
    public void redPlayerWinsWhenTakingAllCities() {
        game.moveUnit(new Position(2,0), new Position(3,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(3,1), new Position(4,1));

        assertEquals("City at (4,1) should be owned by red after captured by red archer",
                Player.RED, game.getCityAt(new Position(4,1)).getOwner());
        assertEquals("Red wins by conquering blue's city",Player.RED, game.getWinner());
    }
    @Test
    public void bluePlayerWinsWhenTakingAllCities() {
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(2,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(2,1), new Position(1,1));

        assertEquals("City at (1,1) should be owned by blue after captured by blue legion",
                Player.BLUE, game.getCityAt(new Position(4,1)).getOwner());
        assertEquals("Blue wins by conquering red's city",Player.BLUE, game.getWinner());
    }
    @Test
    public void noOneAutomaticWinsAfter20Rounds(){
        for(int i=0; i<40; i++) {
            game.endOfTurn(); // progresses the time 40 turns
        }
        game.moveUnit(new Position(2,0), new Position(3,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(3,1), new Position(4,1));
        assertNull("None of the players should have won the game yet", game.getWinner());
    }
}
