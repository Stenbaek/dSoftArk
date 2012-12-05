package hotciv.standard;

import hotciv.factories.SemiFactory;
import hotciv.framework.Game;
import hotciv.teststubs.FixedTestStubCivDieRoll;
import org.junit.Before;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 05-12-12
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class TestSemiCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiFactory(new FixedTestStubCivDieRoll()));
    }


}
