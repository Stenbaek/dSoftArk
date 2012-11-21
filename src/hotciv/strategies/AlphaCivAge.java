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

    private Game game;
    private int age;

    public AlphaCivAge(){
        age=-4000;
    }

    public int getAge() {
        return age;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public void ageProgress() {
        age = age + 100; //To change body of implemented methods use File | Settings | File Templates.
    }


    public Integer getEndOfGameAge() {
        return -3000;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void endOfTurn() {
        Player playerInTurn = game.getPlayerInTurn();
        CivMapStrategy mapStrategy = game.getMapStrategy();
        CivUnitStrategy unitStrategy = game.getUnitStrategy();
        CivActionStrategy moveStrategy = game.getMovementStrategy();
        if(playerInTurn == Player.BLUE) { // A round ends after blue players turn as he/she is the last in round

            Integer currentAge = getAge(); // fetches the current Age
            if (getEndOfGameAge() == null
                    || getEndOfGameAge().compareTo(currentAge) != 0) {
                ageProgress(); // advances time

                Iterator it = mapStrategy.getCities().iterator(); //Creates an iterator of the cityMap

                //Iterates over every city in the game
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry)it.next();

                    // Adding 6 production to each cities treasury each round
                    CityImpl city = (CityImpl) pairs.getValue();
                    city.addProductionTreasury(6);

                    if (city.getProduction() != null) {

                        // If the city can afford what it is producing, the unit is placed on the map
                        String cityProductionType = city.getProduction();
                        Integer priceOfProduction = unitStrategy.getUnitCost(cityProductionType);

                        // only allow production if the city can afford it
                        if(city.getProductionTreasury() >= priceOfProduction) {

                            // getting the first free spot found for unit placement
                            Position newUnitPos = mapStrategy.getPositionOfFirstEmptyTile((Position) pairs.getKey(),city.getOwner());
                            Unit theNewUnit = null;
                            // Creates the new unit
                            if(cityProductionType == GameConstants.ARCHER){
                                theNewUnit = new Archer(city.getOwner());
                            }else if(cityProductionType == GameConstants.SETTLER){
                                theNewUnit = new Settler(city.getOwner());
                            }else if(cityProductionType == GameConstants.LEGION){
                                theNewUnit = new Legion(city.getOwner());
                            }

                            // places the unit on map
                            mapStrategy.addUnit(newUnitPos,theNewUnit);
                            city.addProductionTreasury((-priceOfProduction));
                        }
                    }
                }

                moveStrategy.restoreAllMovement(mapStrategy.getUnits()); //restores movement-count for all units

            } else {
                moveStrategy.restoreAllMovement(mapStrategy.getUnits()); //restores movement-count for all units
                //endOfRoundActions();
            }
        }

    }
}
