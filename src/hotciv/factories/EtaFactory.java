package hotciv.factories;

import hotciv.framework.*;
import hotciv.strategies.*;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 14.03
 * To change this template use File | Settings | File Templates.
 */
public class EtaFactory implements AbstractFactory{

    public DieRollStrategy epsilonDieRollStrategy;

    public EtaFactory(DieRollStrategy dieRollStrategy){
        this.epsilonDieRollStrategy = dieRollStrategy;
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
        return new EpsilonCivAttack(epsilonDieRollStrategy);
    }

    @Override
    public CivCityStrategy getCityStrategy() {
        return new EtaCivCity();
    }
}
