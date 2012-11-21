package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14-11-12
 */
public class Archer extends AbstractUnit {

    public Archer(Player owner){
        super(owner,GameConstants.ARCHER);
    }

    @Override
    public int getDefensiveStrength() {
        return 3;
    }

    @Override
    public int getAttackingStrength() {
        return 2;
    }

}
