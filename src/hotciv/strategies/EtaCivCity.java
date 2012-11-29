package hotciv.strategies;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.Utility;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 28/11/12
 * Time: 12.46
 */
public class EtaCivCity implements CivCityStrategy {

    @Override
    public void updateCityRoundProduction(City c, Position p, Game game) {
        //The basic production in the city is "1 production + 1 food", so we set the variables hereby
        int resultProduction = 1;
        int resultFood = 1;

        //Fetching the size and the work force focus in order to determine how the production should be calculated
        int citySize = c.getSize();
        String cityFocus = c.getWorkforceFocus();

        //Fetching the pre-sorted iterator
        //Here the tiles are sorted descending in order of productionValue,
        //with the tiles of the same focus placed first in the list.
        Iterator<Tile> neighbors = Utility.getFoodAndProductionSortedIterator(p, game, cityFocus);

        //We iterate through the sorted list of tiles
        //And only add the production value of the same number of tiles
        //corresponding to the (city population - 1)
        for(int i = 1; i < citySize; i++){
            if(neighbors.hasNext()){
                Tile t = neighbors.next();
                if(t.getTileFocus() == GameConstants.foodFocus){
                    resultFood += t.getProductionValue();
                }else if(t.getTileFocus() == GameConstants.productionFocus){
                    resultProduction += t.getProductionValue();
                }
            }
        }

        //Update both the food and the production
        ((CityImpl) c).addProductionTreasury(resultProduction);
        ((CityImpl) c).addFood(resultFood);
    }

    @Override
    public void updateCityPopulation(City c) {
        //If the food in the city exceeds the outcome of
        //the formula (5 + citySize * 3)
        if( ((CityImpl) c).getCityFood() > (5 + c.getSize() * 3) && c.getSize() < 9 ){
            // ... the population is incremented  ...
            ((CityImpl) c).incrementPopulation();
            // ... and the food is reset
            ((CityImpl) c).resetFood();
        }
    }
}
