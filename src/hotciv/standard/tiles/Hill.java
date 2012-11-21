package hotciv.standard.tiles;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 08-11-12
 * Time: 09:01
 */
public class Hill extends AbstractTile{

    public Hill(Position position){
        super(position,GameConstants.HILLS);
    }

    @Override
    public boolean isHabitable() {
        return true;
    }
}