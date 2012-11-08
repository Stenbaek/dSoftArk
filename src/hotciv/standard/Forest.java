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
public class Forest implements Tile {

    private Position position;

    public Forest(Position p){
        this.position = p;
    }

    @Override
    public Position getPosition() {
        return position;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTypeString() {
        return GameConstants.FOREST;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
