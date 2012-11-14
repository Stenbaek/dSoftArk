package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 08/11/12
 * Time: 09.28
 */

public class Mountain implements Tile {

    private Position position;

    public Mountain(Position p){
        this.position = p;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public String getTypeString() {
        return GameConstants.MOUNTAINS;
    }
}
