package hotciv.strategies;

import hotciv.framework.City;
import hotciv.framework.CivCityStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 28/11/12
 * Time: 12.39
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivCity implements CivCityStrategy{

    @Override
    public void updateCityRoundProduction(City c, Position p, Game g) {
        ((CityImpl) c).addProductionTreasury(6);
        ((CityImpl) c).addFood(0);
    }

    @Override
    public void updateCityPopulation(City c) {
        //Population doesn't change in AlphaCiv
    }


}
