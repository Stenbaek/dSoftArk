package hotciv.framework;

import java.util.Map;
import hotciv.standard.GameImpl;
import hotciv.standard.maps.UnitHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 09:58
 * To change this template use File | Settings | File Templates.
 */
public interface CivUnitStrategy {

    /**
     * set game
     * @param game
     */
    public void setGame( Game game );

        /**
     * check if the unit's movement should be restored,
     * and if so restore it
     * @param unitsMap the unit who's movement to restore
     */
    public void restoreAllMovement(UnitHashMap<Position,Unit> unitsMap);

    int getUnitCost(String unitType);

}
