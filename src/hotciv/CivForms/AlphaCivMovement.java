package hotciv.CivForms;

import hotciv.framework.CivMovementStrategy;
import hotciv.standard.UnitImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 09:41
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivMovement implements CivMovementStrategy{

    public void restoreMovement(UnitImpl u) { // always restores units movement
        u.changeMoveCounter(1);
    }
}
