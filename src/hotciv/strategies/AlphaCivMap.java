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
public class AlphaCivMap implements CivMapStrategy {

    private UnitHashMap<Position,Unit> units;
    private CityHashMap<Position,City> cities;
    private Game game;

    public AlphaCivMap(){
        units = new UnitHashMap();
        units.put(new Position(2,0),new Archer(Player.RED));
        units.put(new Position(3,2),new Legion(Player.BLUE));
        units.put(new Position(4,3),new Settler(Player.RED));

        cities = new CityHashMap();
        cities.put(new Position(1,1),new CityImpl(Player.RED));
        cities.put(new Position(4,1),new CityImpl(Player.BLUE));
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public UnitHashMap<Position,Unit> getUnits() {
        return units;
    }

    @Override
    public CityHashMap<Position,City> getCities() {
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
    public void addCity(Position p, Player player) {
        cities.put(p,new CityImpl(player));
    }

    @Override
    public void addTile(Position p, Tile t) {
        //Empty for now
    }

    @Override
    public void setCityProduction(Position p, String unitType) {
        game.getCityAt(p).setProduction(unitType);
    }

    @Override
    public Position getPositionOfFirstEmptyTile(Position p, Player player) {
        int[] pos = {p.getRow(), p.getColumn()};
        int sign = 1; // 1=positive -1=negative
        int axis = 0; // 0=y 1=x
        int counter = 0;
        if (isTileEmpty(p, player)) {
            return p;
        } else {
            while(true) { // while true? what ?
                for(axis=0; axis<2; axis++) {
                    for(int i=0; i<counter; i++) {
                        pos[axis] = pos[axis] + sign;
                        if (isTileEmpty(new Position(pos[0], pos[1]), player)) {
                            return new Position(pos[0], pos[1]);
                        }
                    }
                    if(axis==0)
                        sign = sign*(-1);
                }
                counter++;
            }
        }
    }

    @Override
    public boolean isTileEmpty(Position p, Player player) {
        if (p.getColumn() < 0 || p.getRow() < 0 || p.getColumn() > (GameConstants.WORLDSIZE-1)
                || p.getRow() > (GameConstants.WORLDSIZE-1)) {
            // Returns false if we try to place a unit outside of the map
            return false;
        } else if (game.getUnitAt(p) != null) {
            return false; // tile is taken

        } else if ( (game.getTileAt(p).getTypeString() == GameConstants.OCEANS)
                || (game.getTileAt(p).getTypeString() == GameConstants.MOUNTAINS) ) {
            return false; // tile is either oceans or mountains hence no unit can stay there

        } else if (game.getCityAt(p) != null && game.getCityAt(p).getOwner() != player) {
            return false; // there is a city on this tile and it is not the same owner as the unit
        }
        return true;
    }
}
