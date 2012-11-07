package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Tile;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 07/11/12
 * Time: 13.12
 * To change this template use File | Settings | File Templates.
 */
public class Ocean implements Tile{

    private Position position;

    public Ocean(Position p){
        this.position = p;
    }

    @Override
    public Position getPosition() {
        return this.position;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getTypeString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
