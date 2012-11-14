package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14-11-12
 */
public class Settler implements Unit {
    private Player owner;
    private int moveCount = 1;

    public Settler(Player owner){
        this.owner = owner;
    }

    @Override
    public String getTypeString() {
        return GameConstants.SETTLER;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return 3;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }
    public void changeMoveCounter(int moveChange){
        moveCount += moveChange;
    }
}
