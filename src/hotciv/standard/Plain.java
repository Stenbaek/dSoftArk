package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 08-11-12
 * Time: 09:01
 * To change this template use File | Settings | File Templates.
 */
public class Plain implements Tile {

    private Position position;

    public Plain(Position p){
        this.position = p;
    }

    @Override
    public Position getPosition() {
        return position;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTypeString() {
        return GameConstants.PLAINS;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
