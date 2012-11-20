package hotciv.framework;

import hotciv.standard.UnitImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 09:37
 * To change this template use File | Settings | File Templates.
 */
public interface CivMovementStrategy {
    /**
     * check if the unit's movement should be restored,
     * and if so restore it
     * @param u the unit who's movement to restore
     */
    public void restoreMovement(UnitImpl u);
}