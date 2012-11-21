package hotciv.CivForms;

import java.util.Map;
import java.util.HashMap;

import hotciv.framework.*;
import hotciv.standard.*;
import hotciv.standard.CityImpl;


/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivMap implements CivMapStrategy {

    private UnitHashMap<Position,Unit> units;
    private HashMap<Position,City> cities;

    public AlphaCivMap(){
        units = new UnitHashMap();
        units.put(new Position(2,0),new Archer(Player.RED));
        units.put(new Position(3,2),new Legion(Player.BLUE));
        units.put(new Position(4,3),new Settler(Player.RED));

        cities = new HashMap();
        cities.put(new Position(1,1),new CityImpl(Player.RED));
        cities.put(new Position(4,1),new CityImpl(Player.BLUE));
    }

    @Override
    public UnitHashMap<Position,Unit> getUnits() {
        return units;
    }

    @Override
    public Map<Position,City> getCities() {
        return cities;
    }

    @Override
    public Map<Position,Tile> getTiles() {
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

    @Override
    public void addUnit(Position p, Unit u) {
        units.put(p,u);
    }

    @Override
    public void addCity(Position p, City c) {
        cities.put(p,c);
    }

    @Override
    public void addTile(Position p, Tile t) {
        //Empty for now
    }
}
