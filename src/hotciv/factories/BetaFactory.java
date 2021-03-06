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
public class BetaFactory implements AbstractFactory{

    @Override
    public CivWinStrategy getWinningStrategy() {
        return new BetaCivWin();
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
        return new BetaCivAge();
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