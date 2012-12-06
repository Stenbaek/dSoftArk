package hotciv.factories;

import hotciv.framework.*;
import hotciv.strategies.*;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 29/11/12
 * Time: 16.16
 * To change this template use File | Settings | File Templates.
 */
public class SemiFactory implements AbstractFactory {

    public DieRollStrategy dieRollStrategy;

    public SemiFactory(DieRollStrategy dieRollStrategy){
        this.dieRollStrategy = dieRollStrategy;
    }

    @Override
    public CivWinStrategy getWinningStrategy() {
        return new EpsilonCivWin();
    }

    @Override
    public CivWorldStrategy getWorldStrategy() {
        return new DeltaCivWorld();
    }

    @Override
    public CivActionStrategy getActionStrategy() {
        return new GammaCivAction();
    }

    @Override
    public CivAgeStrategy getAgeStrategy() {
        return new BetaCivAge();
    }

    @Override
    public CivAttackStrategy getAttackStrategy() {
        return new EpsilonCivAttack(dieRollStrategy);
    }

    @Override
    public CivCityStrategy getCityStrategy() {
        return new EtaCivCity();
    }
}
