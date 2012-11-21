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
public class Plain extends AbstractTile {

    public Plain(Position position){
        super(position,GameConstants.PLAINS);
    }

    @Override
    public boolean isHabitable() {
        return true;
    }

}
