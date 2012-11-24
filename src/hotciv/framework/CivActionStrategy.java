package hotciv.framework;

import hotciv.standard.GameImpl;
import hotciv.standard.maps.UnitHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 09:37
 * To change this template use File | Settings | File Templates.
 */
public interface CivActionStrategy {

    /**
     * perform unit's action at position
     * Precondition: unit at position
     * @param p position of unit
     */
    public void performUnitAction( Unit unit, Position p, Game game );

}
