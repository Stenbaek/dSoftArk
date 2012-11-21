package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 00.24
 */
public abstract class AbstractTile implements Tile {

    private Position position;
    private String typeString;

    public AbstractTile(Position position, String typeString){
        this.position = position;
        this.typeString = typeString;
    }

    public Position getPosition() {
        return position;
    }

    public String getTypeString() {
        return typeString;
    }

}
