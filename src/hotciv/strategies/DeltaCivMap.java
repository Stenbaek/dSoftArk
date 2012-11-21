package hotciv.strategies;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.maps.CityHashMap;
import hotciv.standard.maps.UnitHashMap;
import hotciv.standard.tiles.*;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Legion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 08.31
 * To change this template use File | Settings | File Templates.
 */
public class DeltaCivMap implements CivMapStrategy {

    private UnitHashMap<Position,Unit> units = new UnitHashMap();
    private CityHashMap<Position,City> cities = new CityHashMap();
    private HashMap<Position,Tile> tiles = new HashMap();
    private Game game;

    public DeltaCivMap(){
        //Adding Cities
        addCity(new Position(8,12),Player.RED);
        addCity(new Position(4,5),Player.BLUE);

        //Adding units
        addUnit(new Position(4, 4), new Legion(Player.BLUE));
		addUnit(new Position(8, 3), new Archer(Player.BLUE));

        //Populating the map
        setWorld();
    }

    public void setWorld(){
        String[] worldLayout = worldDesign();
		for(int r=0; r < GameConstants.WORLDSIZE; r++){
			for(int c=0; c < GameConstants.WORLDSIZE; c++){
				if(worldLayout[r].charAt(c) == '~'){
					addTile(new Position(r, c), new Ocean(new Position(r, c)));
				} else if(worldLayout[r].charAt(c) == 'M'){
					addTile(new Position(r, c), new Mountain(new Position(r, c)));
				} else if(worldLayout[r].charAt(c) == 'o'){
					addTile(new Position(r, c), new Plain(new Position(r, c)));
				} else if(worldLayout[r].charAt(c) == 'f'){
					addTile(new Position(r, c), new Forest(new Position(r, c)));
				} else if(worldLayout[r].charAt(c) == 'h'){
					addTile(new Position(r, c), new Hill(new Position(r, c)));
				}
			}
		}
    }

    public String[] worldDesign(){
		String[] worldLayout = new String[]{
				"~~~ooMooooo~~~~~",
				"~~ohhoooofffoo~~",
				"~oooooMooo~~~oo~",
				"~ooMMMoooo~~oooo",
				"~~~ofooohhoooo~~",
				"~ofoofooooohhoo~",
				"~~~ooo~~~~~~~~~~",
				"~ooooo~ooohooM~~",
				"~ooooo~oohooof~~",
				"offfoooo~offoooo",
				"oooooooo~~~ooooo",
				"~ooMMMoooo~~~~~~",
				"~~ooooooffoooo~~",
				"~~~~ooooooooo~~~",
				"~~ooohhoo~~~~~~~",
				"~~~~~ooooooooo~~",
		};
		return worldLayout;
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
        if (tiles.containsKey(p))
            tiles.remove(p); //If a tile already exists at Position p,  we remove it before we replace it with a new tile
        tiles.put(p,t);
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