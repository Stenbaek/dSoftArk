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

    public TileImpl(Position p){
        this.position = p;
    }

    @Override
    public Position getPosition() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTypeString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
