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

    private int age;
    private Unit[][] units; // matrix of units in game
    private CityImpl[][] cities; // matrix of cities in game
    private Player playerInTurn = Player.RED;
    private CivAgeStrategy ageing;
    private CivWinStrategy winning;
    private CivMovementStrategy move;
    private CivMapStrategy mape;
    private CivUnitStrategy unitAct;
   private Tile[][] map;

    public GameImpl(CivAgeStrategy ageing, CivWinStrategy winning, CivMovementStrategy move, CivMapStrategy mape,CivUnitStrategy unitAct){
        this.age = -4000; // initial start age
        this.createWorld();
        this.ageing = ageing;
        this.winning = winning;
        this.move = move;
        this.mape = mape;
        this.unitAct = unitAct;
        this.unitAct.setGame(this);

    }

    private void createWorld(){
        units = mape.getUnits();
        cities = mape.getCities();
        map = mape.getWorld();

    }

    public Tile getTileAt( Position p ) {
        if (p.equals(new Position(1,0))){ // 1,1 is ocean
            return new Ocean(p);
        } else if(p.equals(new Position(0,1))){ // 0,1 is hills
            return new Hill(p);
        } else if(p.equals(new Position(2,2))){ // is mountains
            return new Mountain(p);
        }
        return new Plain(p); // All other tiles are of type plains
    }

    public Unit getUnitAt( Position p ) {
        return units[p.getRow()][p.getColumn()];
    }

    public City getCityAt( Position p ) {
        return cities[p.getRow()][p.getColumn()];
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return winning.getWinner(this);
    }

    public int getAge() {
        return ageing.getAge();
    }

    public boolean moveUnit( Position from, Position to ) {

        // getting the unit
        UnitImpl theUnitInMove = (UnitImpl) units[from.getRow()][from.getColumn()];

        // Checking the unit is owned by the player in turn
        if( theUnitInMove.getOwner() == playerInTurn){

            if (theUnitInMove.getMoveCount() < 1) {
                return false; // returns false if the unit has < 1 move points left
            }

            Unit unitPossiblyUnderAttack = getUnitAt(to); // finds the unit possibly coming under attack

            if (unitPossiblyUnderAttack != null
                    && unitPossiblyUnderAttack.getOwner() == playerInTurn) {
                return false; // if there is a unit and the unit is owned by the player in turn
            }

            Tile moveToTile = getTileAt(to); // finds the tile at the move TO position
            if (moveToTile.getTypeString().equals(GameConstants.MOUNTAINS)
                    || moveToTile.getTypeString().equals(GameConstants.OCEANS)) {
                return false; // mountains and oceans cannot have units on them
            }

            // *** This is only executed if all tests pass ***
            // change the move count
            theUnitInMove.changeMoveCounter(-1);

            // The actual move of the unit
            units[to.getRow()][to.getColumn()] = theUnitInMove; // move to
            units[from.getRow()][from.getColumn()] = null; // reset from

            // The possible city at the to position
            CityImpl cityPossiblyCaptured = cities[to.getRow()][to.getColumn()];

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

            Integer currAge = ageing.getAge(); // fetches the current Age
            if (ageing.getEndOfGameAge() == null
                    || ageing.getEndOfGameAge().compareTo(currAge) != 0) {
                ageing.ageProgress(); // advances time

                // runs from 0 to worldsize
                for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
                    for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                        performEndOfRoundActions(r,c);
                    }
                }
            } else {
                endOfRoundActions();
            }
        }

        // swaps the players each turn
        if (Player.RED == playerInTurn) {
            playerInTurn = Player.BLUE;
        } else {
            playerInTurn = Player.RED;
        }

    }
    private void endOfRoundActions(){
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
        }
    }


    private void performEndOfRoundActions(int r, int c) {
        if (cities[r][c] != null) {
            // Adding 6 production to each cities treasury each round
            cities[r][c].addProductionTreasury(6);

            if (cities[r][c].getProduction() != null) {

                // If the city can afford what it is producing, the unit is placed on the map
                CityImpl currentCity = cities[r][c];
                String cityProductionType = currentCity.getProduction();
                int priceOfProduction = getUnitCost(cityProductionType);

                // only allow production if the city can afford it
                if(cities[r][c].getProductionTreasury() >= priceOfProduction) {

                    // getting the first free spot found for unit placement
                    Position newUnitPos = getFirstEmptyTile(new Position(r,c),currentCity.getOwner());

                    // Creates the new unit
                    UnitImpl theNewUnit = new UnitImpl(cityProductionType, currentCity.getOwner());
                    // places the unit on map
                    units[newUnitPos.getRow()][newUnitPos.getColumn()] = theNewUnit;
                    currentCity.addProductionTreasury((-priceOfProduction));
                }
            }
        }
    }

    private int getUnitCost(String unitType){
        if(unitType.equals(Archer.class)) return 10;
        if(unitType.equals(Legion.class)) return 15;
        if(unitType.equals(Settler.class)) return 30;
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
