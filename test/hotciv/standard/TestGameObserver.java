package hotciv.standard;

import hotciv.factories.SemiFactory;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.classes.StubObserverImpl;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;
import hotciv.teststubs.FixedTestStubCivDieRoll;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14-12-12
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
public class TestGameObserver {
    private Game game;
    private StubObserverImpl observer;

    @Before
    public void Setup(){
        game = new GameImpl(new SemiFactory(new FixedTestStubCivDieRoll()));
        observer = new StubObserverImpl();
        game.addObserver(observer);
    }
    @Test
    public void observerShouldBeNotifiedOfPlayerWhenTurnEnds() {
        game.endOfTurn();
        Player p = game.getPlayerInTurn();
        assertEquals("Player in turn should be blue", Player.BLUE, p);
        assertEquals("Observer should be notified of new player when turn ends", Player.BLUE, observer.popPlayer());
    }
    @Test
    public void observerShouldBeNotifiedOfAgeWhenRoundEnds() {
        game.endOfTurn(); game.endOfTurn();
        assertEquals("Game age should be 3900 BC", -3900, game.getAge());
        assertEquals("Observer should be notified of new age when round ends", -3900, observer.popAge());
    }
    @Test
    public void observerShouldBeNotifiedWhenCityIsConstructed() {
        Position pos = new Position(5,5);
        game.performUnitActionAt(pos);
        assertEquals("Observer should be notified of new city", pos, observer.popLastChangedPosition());
    }
    @Test
    public void observerShouldBeNotifiedWhenUnitIsMoved() {
        game.moveUnit(new Position(5,5), new Position(6,5));
        assertEquals("Observer should be notified of moved unit not being at old position", new Position(5,5), observer.popLastChangedPosition());
        assertEquals("Observer should be notified of moved unit's new position", new Position(6,5), observer.popLastChangedPosition());
    }
    @Test
    public void observerShouldBeNotifiedWhenUnitLosesAttack() {
        game.addUnit(new Position(3, 2), new Legion(Player.BLUE));
        game.addUnit(new Position(4, 3), new Settler( Player.RED));
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(4,3));
        assertEquals("Observer should be notified of attack loss", new Position(3,2), observer.popLastChangedPosition());
    }

    @Test
    public void oberserverShouldBeNotifiedWhenUnitWinsAttack() {
        game.endOfTurn();
        game.moveUnit(new Position(4,4), new Position(5,5));
        assertEquals("Observer should be notified of winning unit being moved", new Position(4,4), observer.popLastChangedPosition());
        assertEquals("Observer should be notified of losing unit being removed", new Position(5,5), observer.popLastChangedPosition());
    }

}
