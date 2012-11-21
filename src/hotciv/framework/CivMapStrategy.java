package hotciv.framework;

import hotciv.standard.maps.UnitHashMap;
import hotciv.standard.maps.CityHashMap;

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
     * set game
     * @param game
     */
    public void setGame( Game game );

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
    public CityHashMap<Position,City> getCities();

    /**
     * return array containing tiles. Length should be same as
     * GameConstants.WORLDSIZE
     * @return array containing tiles
     */
    public Map<Position,Tile> getTiles();

    public void addUnit( Position p , Unit u );

    public void addCity( Position p , Player player);

    public void addTile( Position p , Tile u );

    public void setCityProduction(Position p, String unitType);

    public Position getPositionOfFirstEmptyTile(Position p, Player player);

    public boolean isTileEmpty(Position p, Player player);

}
