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
public interface CivWorldStrategy {

    /**
     * populate World
     * @param units
     * @param cities
     */
    public void populateWorld( UnitHashMap units, CityHashMap cities );

    /**
     * return Map containing tiles.
     * @return Map containing tiles
     */
    public Map<Position,Tile> getWorld();

}
