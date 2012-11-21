package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.Player;
import hotciv.framework.Unit;

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
    private CivActionStrategy actionStrategy;
    private CivMapStrategy mapStrategy;
    private CivUnitStrategy unitAct;

    public GameImpl(AbstractFactory factory){

        this.ageingStrategy = factory.getAgeStrategy();
        this.winningStrategy = factory.getWinningStrategy();
        this.actionStrategy = factory.getActionStrategy();
        this.mapStrategy = factory.getWorldStrategy();
        this.unitAct = factory.getUnitStrategy();

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

    public CivActionStrategy getMovementStrategy() {
        return actionStrategy;
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
