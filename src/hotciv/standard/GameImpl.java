package hotciv.standard;

import java.util.Map;
import java.util.Iterator;
import hotciv.framework.*;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.standard.UnitHashMap;

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

    private int age;

    private Map<Position,Unit> unitMap;
    private Map<Position,City> cityMap;
    private Map<Position,Tile> tileMap;

    private Unit[][] units; // matrix of units in game
    private CityImpl[][] cities; // matrix of cities in game
    private Player playerInTurn = Player.RED;

    private CivAgeStrategy ageingStrategy;
    private CivWinStrategy winningStrategy;
    private CivMovementStrategy moveStrategy;
    private CivMapStrategy mapStrategy;
    private CivUnitStrategy unitAct;

    public GameImpl(CivAgeStrategy ageingStrategy, CivWinStrategy winningStrategy, CivMovementStrategy moveStrategy, CivMapStrategy mapStrategy,CivUnitStrategy unitActStrategy){
        this.age = -4000; // initial start age
        this.ageingStrategy = ageingStrategy;
        this.winningStrategy = winningStrategy;
        this.moveStrategy = moveStrategy;
        this.mapStrategy = mapStrategy;
        this.unitAct = unitActStrategy;
        this.unitAct.setGame(this);
        this.createWorld();
    }

    private void createWorld(){
        unitMap = mapStrategy.getUnits();
        cityMap = mapStrategy.getCities();
        tileMap = mapStrategy.getTiles();
    }

    public Tile getTileAt( Position p ) {
        return mapStrategy.getTiles().get(p);
    }

    public Unit getUnitAt( Position p ) {
        return (Unit) mapStrategy.getUnits().get(p);
    }

    public City getCityAt( Position p ) {
        return mapStrategy.getCities().get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return winningStrategy.getWinner(this);
    }

    public int getAge() {
        return ageingStrategy.getAge();
    }

    public boolean moveUnit( Position from, Position to ) {

        // Getting the unit
        Unit theUnitInMove = getUnitAt(from);

        // Checking the unit is owned by the player in turn
        if(theUnitInMove.getOwner() == playerInTurn){

            //We check that the move is legal - within the boundaries of the world.
            if(from.getColumn() < 0 || from.getRow() < 0 ||
                to.getColumn() < 0 || to.getRow() < 0) return false; //Outside the array
            if(from.getColumn() >= GameConstants.WORLDSIZE ||
                from.getRow() >= GameConstants.WORLDSIZE ||
                to.getColumn() >= GameConstants.WORLDSIZE ||
                to.getRow() >= GameConstants.WORLDSIZE) return false; //Outside the world

            if(!(to.getColumn() >= from.getColumn()-1 &&
                to.getColumn() <= from.getColumn()+1 &&
                to.getRow() >= from.getRow()-1 &&
                to.getRow() <= from.getRow()+1)) return false; //Unit can only move within the 8 adjacent tiles

            if (theUnitInMove.getMoveCount() < 1) {
                return false; // returns false if the unit has < 1 move points left
            }

            Unit unitPossiblyUnderAttack = getUnitAt(to); // finds the unit possibly coming under attack

            if (unitPossiblyUnderAttack != null
                    && unitPossiblyUnderAttack.getOwner() == playerInTurn) {
                return false; // if there is a unit and the unit is owned by the player in turn
            }else if(unitPossiblyUnderAttack != null
                    && unitPossiblyUnderAttack.getOwner() != playerInTurn){
                if(mapStrategy.getUnits().remove(to).getClass().equals(Unit.class)){
                    return true;
                }
            }

            Tile moveToTile = getTileAt(to); // finds the tile at the move TO position
            if (!moveToTile.isHabitable()) {
                return false; // mountains and oceans cannot have units on them
            }

            // *** This is only executed if all tests pass ***
            // change the move count
            theUnitInMove.changeMoveCounter(-1);



            // The actual move of the unit
            mapStrategy.getUnits().moveUnitToNewPosition(from,to);

            // The possible city at the to position
            CityImpl cityPossiblyCaptured = (CityImpl) getCityAt(to);

            // If there is a city and it's owned by the other player,
            // the city is set to be owned by the attacker
            if (cityPossiblyCaptured != null &&
                    cityPossiblyCaptured.getOwner() != playerInTurn) {
                cityPossiblyCaptured.setOwner(playerInTurn);
                cityPossiblyCaptured.setProduction(null);
            }

            return true;
        }
        else return false; // if the player in turn does not own the unit
    }

    public void endOfTurn() {
        if(playerInTurn == Player.BLUE) { // A round ends after blue players turn as he/she is the last in round

            Integer currentAge = ageingStrategy.getAge(); // fetches the current Age
            if (ageingStrategy.getEndOfGameAge() == null
                    || ageingStrategy.getEndOfGameAge().compareTo(currentAge) != 0) {
                ageingStrategy.ageProgress(); // advances time

                Iterator it = cityMap.entrySet().iterator(); //Creates an iterator of the cityMap

                //Iterates over every city in the game
                while (it.hasNext()) {
                    Map.Entry pairs = (Map.Entry)it.next();

                    // Adding 6 production to each cities treasury each round
                    CityImpl city = (CityImpl) pairs.getValue();
                    city.addProductionTreasury(6);

                    if (city.getProduction() != null) {

                        // If the city can afford what it is producing, the unit is placed on the map
                        String cityProductionType = city.getProduction();
                        Integer priceOfProduction = getUnitCost(cityProductionType);

                        // only allow production if the city can afford it
                        if(city.getProductionTreasury() >= priceOfProduction) {

                            // getting the first free spot found for unit placement
                            Position newUnitPos = getFirstEmptyTile((Position) pairs.getKey(),city.getOwner());
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
        if(unitType.equals(GameConstants.ARCHER)) return 10;
        if(unitType.equals(GameConstants.LEGION)) return 15;
        if(unitType.equals(GameConstants.SETTLER)) return 30;
        return 0;
    }

    public void changeWorkForceFocusInCityAt( Position p, String balance ) {}

    public void changeProductionInCityAt( Position p, String unitType ) {
        ((CityImpl)getCityAt(p)).setProduction(unitType);
    }

    public void performUnitActionAt( Position p ) {}

    private Position getFirstEmptyTile(Position p, Player player) {
        int[] pos = {p.getRow(), p.getColumn()};
        int sign = 1; // 1=positive -1=negative
        int axis = 0; // 0=y 1=x
        int counter = 0;
        if (isTileEmpty(p, player)) {
            return p;
        } else {
            while(true) { // while true? what ?
                for(axis=0; axis<2; axis++) {
                    for(int i=0; i<counter; i++) {
                        pos[axis] = pos[axis] + sign;
                        if (isTileEmpty(new Position(pos[0], pos[1]), player)) {
                            return new Position(pos[0], pos[1]);
                        }
                    }
                    if(axis==0)
                        sign = sign*(-1);
                }
                counter++;
            }
        }
    }
    private boolean isTileEmpty(Position p, Player player) {
        if (p.getColumn() < 0 || p.getRow() < 0 || p.getColumn() > (GameConstants.WORLDSIZE-1)
                || p.getRow() > (GameConstants.WORLDSIZE-1)) {
            // Returns false if we try to place a unit outside of the map
            return false;
        } else if (getUnitAt(p) != null) {
            return false; // tile is taken

        } else if ( (getTileAt(p).getTypeString() == GameConstants.OCEANS)
                || (getTileAt(p).getTypeString() == GameConstants.MOUNTAINS) ) {
            return false; // tile is either oceans or mountains hence no unit can stay there

        } else if (getCityAt(p) != null && getCityAt(p).getOwner() != player) {
            return false; // there is a city on this tile and it is not the same owner as the unit
        }
        return true;
    }

}
