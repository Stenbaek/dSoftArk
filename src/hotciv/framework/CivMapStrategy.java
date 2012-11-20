package hotciv.framework;

import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;

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
    public UnitImpl[][] getUnits();

    /**
     * return array containing cities. Length should be same as
     * GameConstants.WORLDSIZE
     * @return array containing cities
     */
    public CityImpl[][] getCities();

    /**
     * return array containing tiles. Length should be same as
     * GameConstants.WORLDSIZE
     * @return array containing tiles
     */
    public Tile[][] getWorld();
}
