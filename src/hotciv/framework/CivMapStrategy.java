package hotciv.framework;

import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import hotciv.standard.UnitHashMap;
import hotciv.framework.Unit;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 09:58
 * To change this template use File | Settings | File Templates.
 */
public interface CivMapStrategy {

    /**
     * return array containing units. Length should be same as
     * GameConstants.WORLDSIZE
     * @return array containing units
     */
    public UnitHashMap<Position,Unit> getUnits();

    /**
     * return array containing cities. Length should be same as
     * GameConstants.WORLDSIZE
     * @return array containing cities
     */
    public Map<Position,City> getCities();

    /**
     * return array containing tiles. Length should be same as
     * GameConstants.WORLDSIZE
     * @return array containing tiles
     */
    public Map<Position,Tile> getTiles();

    public void addUnit( Position p , Unit u );

    public void addCity( Position p , City u );

    public void addTile( Position p , Tile u );
}
