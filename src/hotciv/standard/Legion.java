package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 14-11-12
 */
public class Legion implements Unit {
    private Player owner;
    private int moveCount = 1;

    public Legion(Player owner){
        this.owner = owner;
    }

    @Override
    public String getTypeString() {
        return GameConstants.LEGION;
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
        return 2;
    }

    @Override
    public int getAttackingStrength() {
        return 4;
    }
    public void changeMoveCounter(int moveChange){
        moveCount += moveChange;
    }
}
