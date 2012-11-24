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
}
