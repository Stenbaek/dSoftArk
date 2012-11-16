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
public class Plain implements Tile {

    private Position position;

    public Plain(Position p){
        this.position = p;
    }

    public Position getPosition() {
        return position;
    }

    public String getTypeString() {
        return GameConstants.PLAINS;
    }
}
