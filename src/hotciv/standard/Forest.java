package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 08-11-12
 * Time: 09:01
 */
public class Forest implements Tile {

    private Position position;

    public Forest(Position p){
        this.position = p;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getTypeString() {
        return GameConstants.FOREST;
    }
}
