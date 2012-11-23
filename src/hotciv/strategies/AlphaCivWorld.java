package hotciv.strategies;

import java.util.Map;
import java.util.HashMap;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.tiles.*;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;
import hotciv.standard.maps.CityHashMap;
import hotciv.standard.maps.UnitHashMap;


/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivWorld implements CivWorldStrategy {

    private Game game;

    @Override
    public void populateWorld(UnitHashMap units, CityHashMap cities) {
        units.put(new Position(2,0),new Archer(Player.RED));
        units.put(new Position(3,2),new Legion(Player.BLUE));
        units.put(new Position(4,3),new Settler(Player.RED));

        cities.put(new Position(1,1),new CityImpl(Player.RED));
        cities.put(new Position(4,1),new CityImpl(Player.BLUE));
    }

    @Override
    public Map<Position,Tile> getWorld() {
        HashMap<Position,Tile> tiles = new HashMap();

        for (int r = 0; r < GameConstants.WORLDSIZE; r++) { // all tiles are plains...
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                tiles.put(new Position(r, c),new Plain(new Position(r, c)));
            }
        }

        tiles.put(new Position(1,0),new Ocean(new Position(1,0)));
        tiles.put(new Position(0,1),new Hill(new Position(0, 1)));
        tiles.put(new Position(2,2),new Mountain(new Position(2, 2)));

        return tiles;
    }
}
