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
public class GammaFactory implements AbstractFactory{

    @Override
    public CivWinStrategy getWinningStrategy() {
        return new AlphaCivWin();
    }

    @Override
    public CivWorldStrategy getWorldStrategy() {
        return new AlphaCivWorld();
    }

    @Override
    public CivActionStrategy getActionStrategy() {
        return new GammaCivAction();
    }

    @Override
    public CivAgeStrategy getAgeStrategy() {
        return new AlphaCivAge();
    }

    @Override
    public CivUnitStrategy getUnitStrategy() {
        return new AlphaCivUnit();
    }

    @Override
    public CivAttackStrategy getAttackStrategy() {
        return new AlphaCivAttack();
    }

    @Override
    public CivCityStrategy getCityStrategy() {
        return new AlphaCivCity();
    }

}
