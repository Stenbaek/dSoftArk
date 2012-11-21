package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 00.13
 */
public abstract class AbstractUnit implements Unit{
    private Player owner;
    private int moveCount = 1;
    private String typeString;
    
    public AbstractUnit(Player owner,String typeString){
        this.owner = owner;
        this.typeString = typeString;
    }
    
    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public String getTypeString() {
        return this.typeString;
    }
    
    @Override
    public int getMoveCount() {
        return moveCount;
    }
    
    public void changeMoveCounter(int moveChange){
        moveCount += moveChange;
    }
}
