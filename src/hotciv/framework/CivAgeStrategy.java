package hotciv.framework;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 16-11-12
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
public interface CivAgeStrategy {

    /**
     * Return the current age
     * @return current age
     */
    public int getAge();

    /**
     * Increase age
     */
    public void ageProgress();

    /**
     * Return the year in which the game ends
     * @return ending year
     */
    public Integer getEndOfGameAge();

}
