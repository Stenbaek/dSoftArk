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
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public class BetaCivAge implements CivAgeStrategy {

    public int ageProgress(Integer age) {
        if (age < -100) {
            return age + 100;
        } else if (age == -100) {
            return -1;
        } else if (age == -1) {
            return 1;
        } else if (age == 1) {
           return 50;
        } else if (age >= 50 && age < 1750) {
            return age + 50;
        } else if (age >=  1750 && age < 1900) {
            return age + 25;
        } else if (age >= 1900 && age < 1970) {
            return age + 5;
        } else if (age >= 1970)
            return age + 1;
        return age;
    }

    @Override
    public Integer getEndOfGameAge() {
        return null;  //Beta han no specific endOfGameAge
    }

}
