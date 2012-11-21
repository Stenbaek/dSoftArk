package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14-11-12
 */
public class Legion  extends AbstractUnit {

    public Legion(Player owner){
        super(owner,GameConstants.LEGION);
    }

    @Override
    public int getDefensiveStrength() {
        return 2;
    }

    @Override
    public int getAttackingStrength() {
        return 4;
    }

}
