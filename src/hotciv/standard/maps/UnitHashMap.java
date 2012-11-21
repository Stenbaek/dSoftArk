package hotciv.standard.maps;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 02.32
 * To change this template use File | Settings | File Templates.
 */
public class UnitHashMap<Position,Unit> extends HashMap implements Iterable{

    public UnitHashMap(){
        super();
    }

    public void moveUnitToNewPosition( Position oldPosition, Position newPosition ){

        Unit unit = (Unit) this.get(oldPosition);
        this.remove(oldPosition);
        this.put(newPosition,unit);

    }

    @Override
    public Iterator iterator() {
        return this.entrySet().iterator();
    }
}
