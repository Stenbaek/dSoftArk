package hotciv.standard;

import hotciv.factories.EtaFactory;
import hotciv.framework.Game;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 28/11/12
 * Time: 16.18
 * To change this template use File | Settings | File Templates.
 */
public class TestEtaCiv {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new EtaFactory());
    }

    @Test
    public void firstTest(){
        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        c.setWorkForceFocus(GameConstants.foodFocus);

        for(int i = 0; i < 10; i++){
            game.endOfTurn();
            game.endOfTurn();
        }

        assertFalse("City food: " + c.getCityFood(),true);
    }

}
