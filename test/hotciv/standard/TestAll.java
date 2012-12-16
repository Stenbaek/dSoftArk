/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 25-11-12
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
package hotciv.standard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith ( Suite.class )
@Suite.SuiteClasses(
        {
                TestAlphaCiv.class,
                TestBetaCiv.class,
                TestDeltaCiv.class,
                TestGammaCiv.class,
                TestEpsilonCiv.class,
                TestZetaCiv.class,
                TestSemiCiv.class,
                TestGameTranscript.class,
                TestGameObserver.class
        })

public class TestAll {
    // dummy
}
