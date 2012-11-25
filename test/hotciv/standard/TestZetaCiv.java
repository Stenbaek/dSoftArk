package hotciv.standard;

import hotciv.factories.ZetaFactory;
import hotciv.framework.City;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;
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
    public void after20TurnsYouDoNotWinByTakingAllTheCities() {
        game.moveUnit(new Position(2,0),new Position(3,0));
        for(int i=0; i<20;i++){
            game.endOfTurn();
            game.endOfTurn();
        }
        game.moveUnit(new Position(3,0),new Position(4,1));
        assertNull("No player should have won the game yet by being inactive",game.getWinner());

    }

    @Test
    public void after20TurnsYouWinByTaking3SuccessiveAttacks() {
        for(int i=0; i<26;i++){
            game.endOfTurn();
            game.endOfTurn();
        }
        assertTrue("Age should be more than -2000",(game.getAge() > -2000));
        assertNull("And no one should have won yet by being inactibe", game.getWinner());
        for(int i = 0; i < 3; i++){
            game.addUnit(new Position(6,6),new Settler(Player.BLUE));
            game.addUnit(new Position(5,6),new Legion(Player.RED));

            game.moveUnit(new Position(5,6),new Position(6,6));
            game.removeUnit(new Position(6,6));
        }
        assertNotNull("There should be a winenr",game.getWinner());

    }

    @Test
    public void after17TurnsYouWinByTakingAllTheCities() {
        game.moveUnit(new Position(2,0),new Position(3,0));
        for(int i=0; i<17;i++){
            game.endOfTurn();
            game.endOfTurn();
        }
        game.moveUnit(new Position(3,0),new Position(4,1));
        assertNotNull("No player should have won the game yet by being inactive",game.getWinner());

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
