package hotciv.standard;

import java.util.Map;
import java.util.Iterator;
import hotciv.framework.*;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;

/** Skeleton implementation of HotCiv.

 This source code is from the book
 "Flexible, Reliable Software:
 Using Patterns and Agile Development"
 published 2010 by CRC Press.
 Author:
 Henrik B Christensen
 Computer Science Department
 Aarhus University

 This source code is provided WITHOUT ANY WARRANTY either
 expressed or implied. You may study, use, modify, and
 distribute it for non-commercial purposes. For any
 commercial use, see http://www.baerbak.com/
 */

public class GameImpl implements Game {

    private Unit[][] units; // matrix of units in game
    private CityImpl[][] cities; // matrix of cities in game
    private Player playerInTurn = Player.RED;

    private CivAgeStrategy ageingStrategy;
    private CivWinStrategy winningStrategy;
    private CivMovementStrategy moveStrategy;
    private CivMapStrategy mapStrategy;
    private CivUnitStrategy unitAct;

    public GameImpl(CivAgeStrategy ageingStrategy, CivWinStrategy winningStrategy, CivMovementStrategy moveStrategy, CivMapStrategy mapStrategy,CivUnitStrategy unitActStrategy){

        this.ageingStrategy = ageingStrategy;
        this.winningStrategy = winningStrategy;
        this.moveStrategy = moveStrategy;
        this.mapStrategy = mapStrategy;
        this.unitAct = unitActStrategy;

        this.ageingStrategy.setGame(this);
        this.mapStrategy.setGame(this);
        this.unitAct.setGame(this);
    }

    public Tile getTileAt( Position p ) {
        return mapStrategy.getTiles().get(p);
    }

    public Unit getUnitAt( Position p ) {
        return (Unit) mapStrategy.getUnits().get(p);
    }

    public City getCityAt( Position p ) {
        return (City) mapStrategy.getCities().get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public CivMapStrategy getMapStrategy(){
        return mapStrategy;
    }

    public CivUnitStrategy getUnitStrategy() {
        return unitAct;
    }

    public CivMovementStrategy getMovementStrategy() {
        return moveStrategy;
    }

    public Player getWinner() {
        return winningStrategy.getWinner(this);
    }

    public int getAge() {
        return ageingStrategy.getAge();
    }

    public boolean moveUnit( Position from, Position to ) {
        return unitAct.moveUnit(from,to);
    }

    public void endOfTurn() {
        ageingStrategy.endOfTurn();

        // swaps the players each turn
        if (playerInTurn == Player.RED) {
            playerInTurn = Player.BLUE;
        } else {
            playerInTurn = Player.RED;
        }

    }
    private void endOfRoundActions(){
    //TODO - tag stilling til endOfGameActions

        /*
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                CityImpl currentCity = cities[r][c];
                if (currentCity != null) {
                    currentCity.setProduction(null);
                }

                UnitImpl currentUnit = (UnitImpl) units[r][c];
                if (currentUnit != null) {
                    int unitMovePoints = currentUnit.getMoveCount();
                    currentUnit.changeMoveCounter((-unitMovePoints));
                }
            }
        }*/
    }

    private int getUnitCost(String unitType){
        return unitAct.getUnitCost(unitType);
    }

    public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

    public void changeProductionInCityAt( Position p, String unitType ) {
        mapStrategy.setCityProduction(p,unitType);
    }

    public void performUnitActionAt( Position p ) {
        unitAct.performUnitActionAt(p);
    }

}
