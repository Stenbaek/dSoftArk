package hotciv.standard;

import hotciv.framework.Tile;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 28/11/12
 * Time: 17.01
 * To change this template use File | Settings | File Templates.
 */
public class ProductionComparator implements Comparator<Tile> {
    @Override
    public int compare(Tile tile1, Tile tile2) {
        if(tile1.getProductionValue() > tile2.getProductionValue()){
            return -1;
        }else if(tile1.getProductionValue() < tile2.getProductionValue()){
            return 1;
        }else{
            return 0;
        }
    }
}
