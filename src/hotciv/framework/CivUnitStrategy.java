package hotciv.framework;

import java.util.Map;
import hotciv.standard.GameImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 09:58
 * To change this template use File | Settings | File Templates.
 */
public interface CivUnitStrategy {

    /**
     * set game
     * @param game
     */
    public void setGame( GameImpl game );

    /**
     * perform unit's action at position
     * Precondition: unit at position
     * @param p position of unit
     */
    public void performUnitActionAt( Position p );

    /**
     * fetches all units in the game
     * @return Map<Position,Unit> of all units in the game
     */
    public Map<Position,Unit> getAllUnits();

    public void addUnit( Position p , Unit u );


}
