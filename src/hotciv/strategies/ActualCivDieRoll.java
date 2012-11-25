package hotciv.strategies;

import hotciv.framework.DieRollStrategy;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 24/11/12
 * Time: 14.48
 */
public class ActualCivDieRoll implements DieRollStrategy{

    @Override
    public int roll() {
        return (int) (Math.random()*6)+1;
    }

}
