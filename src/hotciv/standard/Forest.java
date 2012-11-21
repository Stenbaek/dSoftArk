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
public class Forest extends AbstractTile {

    public Forest(Position position){
        super(position,GameConstants.FOREST);
    }

    @Override
    public boolean isHabitable() {
        return true;
    }

}
