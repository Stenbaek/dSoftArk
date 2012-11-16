package hotciv.standard;

import hotciv.framework.*;

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

    public GameImpl(CivAgeStrategy ageing){
        this.age = -4000; // initial start age
        this.createWorld();
        this.ageing = ageing;
    }

    private void createWorld(){
        // Initializing the boundaries of the world
        units = new Unit[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        cities = new CityImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

        // Populating the map with units
        units[2][0] = new Archer(Player.RED);
        units[3][2] = new Legion(Player.BLUE);
        units[4][3] = new Settler(Player.RED);

        //Populating the map with cities
        cities[1][1] = new CityImpl(Player.RED);
        cities[4][1] = new CityImpl(Player.BLUE);
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
        if (age == -3000) return Player.RED;
        else return null;
    }

    public int getAge() {
        return ageing.getAge();
    }

    public boolean moveUnit( Position from, Position to ) {
        //We check that the move is legal - within the boundaries of the world.
        if(from.getColumn() < 0 || from.getRow() < 0 ||
                to.getColumn() < 0 || to.getRow() < 0) return false; //Outside the array
        if(from.getColumn() >= GameConstants.WORLDSIZE ||
                from.getRow() >= GameConstants.WORLDSIZE ||
                to.getColumn() >= GameConstants.WORLDSIZE ||
                to.getRow() >= GameConstants.WORLDSIZE) return false; //Outside the world

        // Getting the unit
        Unit theUnitInMove = units[from.getRow()][from.getColumn()];

        if(!(to.getColumn() >= from.getColumn()-1 &&
                to.getColumn() <= from.getColumn()+1 &&
                to.getRow() >= from.getRow()-1 &&
                to.getRow() <= from.getRow()+1)) return false; //Unit can only move within the 8 adjacent tiles

        // Checking the unit is owned by the player in turn
        if( theUnitInMove.getOwner() == playerInTurn){

            if (theUnitInMove.getMoveCount() < 1) return false; // returns false if the unit has < 1 move points left

            Unit unitPossiblyUnderAttack = getUnitAt(to); // finds the unit possibly coming under attack

            if (unitPossiblyUnderAttack != null
                    && unitPossiblyUnderAttack.getOwner() == playerInTurn)
                    return false; // if there isn't any units or the unit there is that of the player in turn

            Tile moveToTile = getTileAt(to); // finds the tile at the move TO position
            if (moveToTile.getClass().equals(Mountain.class)
                    || moveToTile.getClass().equals(Ocean.class))
                    return false; // mountains and oceans cannot have units on them

            // *** This is only executed if all tests pass ***
            // change the move count
            theUnitInMove.changeMoveCounter(-1);

            // The actual move of the unit
            units[to.getRow()][to.getColumn()] = theUnitInMove; // move to
            units[from.getRow()][from.getColumn()] = null; // reset from
            return true;
        }
        else return false; // if the player in turn does not own the unit
    }

    public void endOfTurn() {
        if(playerInTurn == Player.BLUE) { // A round ends after blue players turn as he/she is the last in round
            age += 100; // advances time by 100 years
            for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
                for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                    if (cities[r][c] != null) {
                        // Adding 6 production to each cities treasury each round
                        cities[r][c].addProductionTreasury(6);
                        if (cities[r][c].getProduction() != null) {
                            // If the city can afford what it is producing, the unit is placed on the map
                            if(cities[r][c].getProductionTreasury()
                                    >= getUnitCost(cities[r][c].getProduction())) {
                                // places the unit on map
                                units[r][c] = new UnitImpl(cities[r][c].getProduction(), cities[r][c].getOwner());
                            }
                        }
                    }
                    // only selects units with 0 move points
                    if (units[r][c] != null && units[r][c].getMoveCount() == 0) {
                        units[r][c].changeMoveCounter(1); // sets the unit moveCount to 1 if it is zero (current invariant)
                    }
                }
            }
        }

        // swaps the players each turn
        if (Player.RED == playerInTurn) {
            playerInTurn = Player.BLUE;
        } else {
            playerInTurn = Player.RED;
        }

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

}
