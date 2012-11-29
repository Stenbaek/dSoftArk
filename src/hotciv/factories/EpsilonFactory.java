package hotciv.factories;

import hotciv.framework.*;
import hotciv.strategies.*;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 22/11/12
 * Time: 22.59
 * To change this template use File | Settings | File Templates.
 */
public class EpsilonFactory implements AbstractFactory {

    public DieRollStrategy epsilonDieRollStrategy;

    public EpsilonFactory(DieRollStrategy dieRollStrategy){
        this.epsilonDieRollStrategy = dieRollStrategy;
    }

    @Override
    public CivWinStrategy getWinningStrategy() {
        return new EpsilonCivWin();
    }

    @Override
    public CivWorldStrategy getWorldStrategy() {
        return new AlphaCivWorld();
    }

    @Override
    public CivActionStrategy getActionStrategy() {
        return new AlphaCivAction();
    }

    @Override
    public CivAgeStrategy getAgeStrategy() {
        return new AlphaCivAge();
    }

    @Override
    public CivAttackStrategy getAttackStrategy() {
        return new EpsilonCivAttack(epsilonDieRollStrategy);
    }

    @Override
    public CivCityStrategy getCityStrategy() {
        return new AlphaCivCity();
    }
}
