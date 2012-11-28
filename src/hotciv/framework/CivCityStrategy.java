package hotciv.framework;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 28/11/12
 * Time: 12.37
 * To change this template use File | Settings | File Templates.
 */
public interface CivCityStrategy {

    public void updateCityRoundProduction( City c, Position p, Game game );

    public void updateCityPopulation( City c );

}
