package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 07-11-12
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
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
        return typeString;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Player getOwner() {
        return owner;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getMoveCount() {
        return moveCount;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getDefensiveStrength() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getAttackingStrength() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void changeMoveCounter(int moveChange){
        moveCount += moveChange;
    }
}
