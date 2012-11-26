package hotciv.strategies;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.maps.CityHashMap;
import hotciv.standard.maps.UnitHashMap;
import hotciv.standard.tiles.*;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 08.31
 * To change this template use File | Settings | File Templates.
 */
public class DeltaCivWorld implements CivWorldStrategy {

    private HashMap<Position,Tile> tiles;

    public DeltaCivWorld(){
        this.tiles = new HashMap();
        setWorld();
    }

    @Override
    public void populateWorld(UnitHashMap units, CityHashMap cities) {
        units.put(new Position(4, 4), new Legion(Player.BLUE));
		units.put(new Position(8, 3), new Archer(Player.RED));
        units.put(new Position(5, 5), new Settler(Player.RED));


        cities.put(new Position(8,12),new CityImpl(Player.RED));
        cities.put(new Position(4,5),new CityImpl(Player.BLUE));
    }

    public void setWorld(){
        String[] worldLayout = worldDesign();
		for(int r=0; r < GameConstants.WORLDSIZE; r++){
			for(int c=0; c < GameConstants.WORLDSIZE; c++){
				if(worldLayout[r].charAt(c) == '~'){
					tiles.put(new Position(r, c), new Ocean(new Position(r, c)));
				} else if(worldLayout[r].charAt(c) == 'M'){
					tiles.put(new Position(r, c), new Mountain(new Position(r, c)));
				} else if(worldLayout[r].charAt(c) == 'o'){
					tiles.put(new Position(r, c), new Plain(new Position(r, c)));
				} else if(worldLayout[r].charAt(c) == 'f'){
					tiles.put(new Position(r, c), new Forest(new Position(r, c)));
				} else if(worldLayout[r].charAt(c) == 'h'){
					tiles.put(new Position(r, c), new Hill(new Position(r, c)));
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
    public Map<Position,Tile> getWorld() {
        return tiles;
    }

}