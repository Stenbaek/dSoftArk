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

public class Mountain extends AbstractTile{

    public Mountain(Position position){
        super(position,GameConstants.MOUNTAINS);
    }

    @Override
    public boolean isHabitable() {
        return false;
    }

}
