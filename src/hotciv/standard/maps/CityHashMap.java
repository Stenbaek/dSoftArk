package hotciv.standard.maps;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 05.44
 * To change this template use File | Settings | File Templates.
 */
public class CityHashMap<Position,City> extends HashMap implements Iterable{

    public CityHashMap(){
        super();
    }

    @Override
    public Iterator iterator() {
        return this.entrySet().iterator();
    }

}
