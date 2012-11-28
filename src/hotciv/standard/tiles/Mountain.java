package hotciv.standard.tiles;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;

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

    @Override
    public String getTileFocus() {
        return GameConstants.productionFocus;
    }

    @Override
    public int getProductionValue() {
        return 1;
    }

}
