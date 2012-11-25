package hotciv.teststubs;

import hotciv.framework.DieRollStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 24/11/12
 * Time: 14.48
 */
public class FixedTestStubCivDieRoll implements DieRollStrategy{

    @Override
    public int roll() {
        return 1;
    }

}
