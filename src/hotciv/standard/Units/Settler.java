package hotciv.standard.units;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14-11-12
 */
public class Settler extends AbstractUnit {

    public Settler(Player owner){
        super(owner,GameConstants.SETTLER);
    }

    @Override
    public int getDefensiveStrength() {
        return 3;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }

}
