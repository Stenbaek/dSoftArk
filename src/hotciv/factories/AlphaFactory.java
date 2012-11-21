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
public class AlphaFactory implements AbstractFactory{

    @Override
    public CivWinStrategy getWinningStrategy() {
        return new AlphaCivWin();
    }

    @Override
    public CivMapStrategy getWorldStrategy() {
        return new AlphaCivMap();
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
    public CivUnitStrategy getUnitStrategy() {
        return new AlphaCivUnit();
    }
}
