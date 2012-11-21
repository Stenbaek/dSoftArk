package hotciv.strategies;

import hotciv.framework.CivActionStrategy;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.units.AbstractUnit;
import hotciv.standard.maps.UnitHashMap;

import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 09:41
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivAction implements CivActionStrategy {

    public void restoreAllMovement(UnitHashMap<Position,Unit> unitsMap) { // always restores units movement

        Iterator it = unitsMap.iterator(); //Creates an iterator of the cityMap

                //Iterates over every city in the game
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry)it.next();

                    // Adding 6 production to each cities treasury each round
                    AbstractUnit unit = (AbstractUnit) pairs.getValue();
                    unit.changeMoveCounter(1);

                }

    }
}
