package hotciv.framework;

import hotciv.framework.Game;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 16-11-12
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
public interface CivAgeStrategy {

    /**
     * Increase age and return the new age
     * @return new age
     */
    public int ageProgress(Integer currentAge);

}
