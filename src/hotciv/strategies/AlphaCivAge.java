package hotciv.strategies;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;

import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 16-11-12
 * Time: 12:24
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivAge implements CivAgeStrategy{

    public int ageProgress(Integer currentAge) {
        return currentAge + 100;
    }

}
