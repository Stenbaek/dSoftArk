package hotciv.strategies;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.maps.UnitHashMap;
import hotciv.standard.units.AbstractUnit;
import hotciv.standard.units.Archer;

import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivUnit implements CivUnitStrategy {

    public void restoreAllMovement(UnitHashMap<Position,Unit> unitsMap) { // always restores units movement

        Iterator it = unitsMap.iterator(); //Creates an iterator of the cityMap
        // //Iterates over every city in the game
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();

            // Adding 6 production to each cities treasury each round
            AbstractUnit unit = (AbstractUnit) pairs.getValue();
            unit.changeMoveCounter(1);
        }

    }

    @Override
    public int getUnitCost(String unitType) {
        if(unitType.equals(GameConstants.ARCHER)) return 10;
        if(unitType.equals(GameConstants.LEGION)) return 15;
        if(unitType.equals(GameConstants.SETTLER)) return 30;
        return 0;
    }

}
