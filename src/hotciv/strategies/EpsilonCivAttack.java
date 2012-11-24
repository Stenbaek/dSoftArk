package hotciv.strategies;

import hotciv.framework.*;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 22/11/12
 * Time: 23.10
 * To change this template use File | Settings | File Templates.
 */
public class EpsilonCivAttack implements CivAttackStrategy {

    private DieRollStrategy dieRollStrategy;

    public EpsilonCivAttack(DieRollStrategy dieRollStrategy) {
        this.dieRollStrategy = dieRollStrategy;
    }

    @Override
    public boolean outcomeOfBattle(Game game, Position attackersPosition, Position defendersPosition) {
        return (this.dieRollStrategy.roll() < 2);
    }
}
