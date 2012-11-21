package hotciv.standard.units;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14-11-12
 */
public class Archer extends AbstractUnit{

    private boolean fortified = false;
    private int defensiveStrength = 3;

    public Archer(Player owner){
        super(owner,GameConstants.ARCHER);
    }

    @Override
    public int getDefensiveStrength() {
        return (!fortified) ? defensiveStrength : defensiveStrength*2;
    }

    @Override
    public int getAttackingStrength() {
        return 2;
    }

    public void toggleFortified(){
        this.fortified = (fortified) ? false : true;
    }

    public boolean isFortified(){
        return fortified;
    }

}
