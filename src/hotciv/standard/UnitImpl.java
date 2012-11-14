package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 07-11-12
 * Time: 21:11
 */
public class UnitImpl implements Unit{
    private String typeString; //type of unit
    private Player owner; //the player who owns the unit
    private int moveCount = 1; //movement - hardcoded

    public UnitImpl(String typeString, Player owner){
        this.typeString=typeString;
        this.owner=owner;
    }

    @Override
    public String getTypeString() {
        return typeString;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }

    public void changeMoveCounter(int moveChange){
        moveCount += moveChange;
    }
}
