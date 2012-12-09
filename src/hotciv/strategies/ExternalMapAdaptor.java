package hotciv.strategies;

import hotciv.framework.*;
import hotciv.standard.maps.CityHashMap;
import hotciv.standard.maps.UnitHashMap;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 09-12-12
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */
public class ExternalMapAdaptor implements CivWorldStrategy{

    private UnitHashMap<Position,Unit> units;
    private CityHashMap<Position,City> cities;
    private HashMap<Position,Tile> tiles;
    private ThirdPartyFractalGenerator fractalGenerator;

    public ExternalMapAdaptor(ThirdPartyFractalGenerator fractalGenerator) {
        this.fractalGenerator = fractalGenerator;
        this.tiles = new HashMap();
        this.units = new UnitHashMap<Position,Unit>();
        this.cities = new CityHashMap<Position,City>();
    }

    @Override
    public void populateWorld(UnitHashMap units, CityHashMap cities) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<Position, Tile> getWorld() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
