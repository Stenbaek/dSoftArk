package hotciv.strategies;

import hotciv.framework.CivWorldStrategy;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.maps.CityHashMap;
import hotciv.standard.maps.UnitHashMap;
import hotciv.standard.tiles.*;
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

    private HashMap<Position,Tile> tiles;
    private ThirdPartyFractalGenerator fractalGenerator;

    public ExternalMapAdaptor(ThirdPartyFractalGenerator fractalGenerator) {
        this.fractalGenerator = fractalGenerator;
        this.tiles = new HashMap();
    }

    public void setWorld(){
        for(int r=0; r < GameConstants.WORLDSIZE; r++){
            for(int c=0; c < GameConstants.WORLDSIZE; c++){
                char tile = fractalGenerator.getLandscapeAt(r,c);
                if(tile == '.'){
                    tiles.put(new Position(r, c), new Ocean(new Position(r, c)));
                } else if(tile == 'M'){
                    tiles.put(new Position(r, c), new Mountain(new Position(r, c)));
                } else if(tile == 'o'){
                    tiles.put(new Position(r, c), new Plain(new Position(r, c)));
                } else if(tile == 'f'){
                    tiles.put(new Position(r, c), new Forest(new Position(r, c)));
                } else if(tile == 'h'){
                    tiles.put(new Position(r, c), new Hill(new Position(r, c)));
                }
            }
        }
    }

    @Override
    public void populateWorld(UnitHashMap units, CityHashMap cities) {
        new DeltaCivWorld().populateWorld(units, cities);
    }

    @Override
    public Map<Position, Tile> getWorld() {
        return tiles;
    }
}
