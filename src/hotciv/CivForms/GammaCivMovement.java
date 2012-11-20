package hotciv.CivForms;

import hotciv.framework.CivMovementStrategy;
import hotciv.standard.Archer;
import hotciv.standard.UnitImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 09:42
 * To change this template use File | Settings | File Templates.
 */
public class GammaCivMovement implements CivMovementStrategy{

    public void restoreMovement(UnitImpl u) {
        if (Archer.class.equals(u.getTypeString())
                && (u.getDefensiveStrength() == 6)) {
            // do nothing
        } else {
            u.changeMoveCounter(1); // restore movement
        }
    }
}
