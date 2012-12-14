package hotciv.standard;

import hotciv.factories.SemiFactory;
import hotciv.framework.*;
import hotciv.teststubs.FixedTestStubCivDieRoll;
import org.junit.Before;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14-12-12
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
public class TestGameObserver {
    private Game game;

    @Before
    public void Setup(){
        game = new GameImpl(new SemiFactory(new FixedTestStubCivDieRoll()));
        //game.addObserver(observer);
    }

}
