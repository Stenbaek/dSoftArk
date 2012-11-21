package hotciv.standard;

import java.util.HashMap;
import hotciv.framework.Position;
import hotciv.framework.Unit;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 02.32
 * To change this template use File | Settings | File Templates.
 */
public class UnitHashMap<Position,Unit> extends HashMap {

    public UnitHashMap(){
        super();
    }

    public void moveUnitToNewPosition( Position oldPosition, Position newPosition ){

        Unit unit = (Unit) this.get(oldPosition);
        this.remove(oldPosition);
        this.put(newPosition,unit);

    }

}
