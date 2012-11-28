package hotciv.factories;

import hotciv.framework.*;
import hotciv.strategies.*;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 24-11-12
 * Time: 12:51
 * To change this template use File | Settings | File Templates.
 */
public class ZetaFactory implements AbstractFactory {
    @Override
    public CivWinStrategy getWinningStrategy() {
        return new ZetaCivWin();
    }

    @Override
    public CivWorldStrategy getWorldStrategy() {
        return new AlphaCivWorld();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CivActionStrategy getActionStrategy() {
        return new AlphaCivAction();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CivAgeStrategy getAgeStrategy() {
        return new AlphaCivAge();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CivUnitStrategy getUnitStrategy() {
        return new AlphaCivUnit();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CivAttackStrategy getAttackStrategy() {
        return new AlphaCivAttack();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CivCityStrategy getCityStrategy() {
        return new AlphaCivCity();
    }
}
