package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 08-11-12
 * Time: 09:01
 * To change this template use File | Settings | File Templates.
 */
public class TileImpl implements Tile {

    private Position position;
    private String type;

    public TileImpl(String type, Position p){
        this.position = p;
        this.type =type;
    }

    @Override
    public Position getPosition() {
        return position;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTypeString() {
        return this.type;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
