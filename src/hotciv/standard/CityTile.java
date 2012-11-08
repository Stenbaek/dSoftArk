package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.City;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 08/11/12
 * Time: 09.28
 * To change this template use File | Settings | File Templates.
 */

public class CityTile implements Tile {

    private Position position;
    private City cityAtTile;

    public CityTile(Position p, City c){
        this.position = p;
    }

    @Override
    public Position getPosition() {
        return position;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTypeString() {
        return GameConstants.CITIES;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public City getCityAtTile(){
        return cityAtTile;
    }
}
