package hotciv.standard.tiles;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 08-11-12
 * Time: 09:01
 */
public class Ocean extends AbstractTile{

    public Ocean(Position position){
        super(position,GameConstants.OCEANS);
    }

    @Override
    public boolean isHabitable() {
        return false;
    }

}
