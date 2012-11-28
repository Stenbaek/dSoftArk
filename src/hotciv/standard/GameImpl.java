package hotciv.standard;

import hotciv.framework.*;
import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.standard.maps.CityHashMap;
import hotciv.standard.maps.UnitHashMap;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;

import java.util.Iterator;
import java.util.Map;

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

    private Player playerInTurn;

    private Integer age;

    private UnitHashMap<Position,Unit> units;
    private CityHashMap<Position,City> cities;

    private CivAgeStrategy ageingStrategy;
    private CivWinStrategy winningStrategy;
    private CivActionStrategy actionStrategy;
    private CivWorldStrategy worldStrategy;
    private CivUnitStrategy unitStrategy;
    private CivAttackStrategy attackStrategy;
    private CivCityStrategy cityStrategy;

    public GameImpl(AbstractFactory factory){
        this.ageingStrategy = factory.getAgeStrategy();
        this.winningStrategy = factory.getWinningStrategy();
        this.actionStrategy = factory.getActionStrategy();
        this.worldStrategy = factory.getWorldStrategy();
        this.unitStrategy = factory.getUnitStrategy();
        this.attackStrategy = factory.getAttackStrategy();
        this.cityStrategy = factory.getCityStrategy();

        this.units = new UnitHashMap<Position,Unit>();
        this.cities = new CityHashMap<Position,City>();

        this.worldStrategy.populateWorld(units, cities);
        age = -4000;
        playerInTurn = Player.RED;
    }

    public Tile getTileAt( Position p ) {
        return worldStrategy.getWorld().get(p);
    }

    public Unit getUnitAt( Position p ) {
        return (Unit) units.get(p);
    }

    public City getCityAt( Position p ) {
        return (City) cities.get(p);
    }

    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    public Player getWinner() {
        return winningStrategy.getWinner(this);
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit( Position from, Position to ) {
        // Getting the unit
        Unit theUnitInMove = getUnitAt(from);
        Player playerInTurn = getPlayerInTurn();

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

            if (theUnitInMove.getClass().equals(Archer.class)){
                Archer archer = (Archer)theUnitInMove;
                if(archer.isFortified()){
                    return false; //Unit is fortified, cannot move
                }
            }

            Unit unitPossiblyUnderAttack = getUnitAt(to); // finds the unit possibly coming under attack

            if (unitPossiblyUnderAttack != null
                    && unitPossiblyUnderAttack.getOwner() == playerInTurn) {
                return false; // if there is a unit and the unit is owned by the player in turn
            }else if(unitPossiblyUnderAttack != null
                    && unitPossiblyUnderAttack.getOwner() != playerInTurn){
                if(fight(from,to)){
                    winningStrategy.incrementWins(playerInTurn, this);
                }else{
                    return false;
                }
            }

            Tile moveToTile = getTileAt(to); // finds the tile at the move TO position
            if(!moveToTile.isHabitable()){
                return false;
            }

            // *** This is only executed if all tests pass ***

            // change the move count
            theUnitInMove.changeMoveCounter(-1);
            units.moveUnitToNewPosition(from, to); // The actual move of the unit

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

    public void updateAge(){
        this.age = ageingStrategy.ageProgress(this.age);
    }

    public void endOfTurn() {
        if(playerInTurn == Player.BLUE) { // A round ends after blue players turn as he/she is the last in round

            Integer currentAge = getAge(); // fetches the current Age
            updateAge(); // advances time

            Iterator it = this.cities.iterator(); //Creates an iterator of the cityMap

            //Iterates over every city in the game
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry)it.next();

                // Adding 6 production to each cities treasury each round
                CityImpl city = (CityImpl) pairs.getValue();

                //Updating food and production treasury
                cityStrategy.updateCityRoundProduction(city, (Position) pairs.getKey(), this);
                cityStrategy.updateCityPopulation(city);

                if (city.getProduction() != null) {

                    // If the city can afford what it is producing, the unit is placed on the map
                    String cityProductionType = city.getProduction();
                    Integer priceOfProduction = getUnitCost(cityProductionType);

                    // only allow production if the city can afford it
                    if(city.getProductionTreasury() >= priceOfProduction) {

                        // getting the first free spot found for unit placement
                        EmptyTileIterator emptyTileIterator = new EmptyTileIterator((Position) pairs.getKey(), city.getOwner(), this);
                        Position newUnitPos = emptyTileIterator.next();
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
                        addUnit(newUnitPos, theNewUnit);
                        city.addProductionTreasury((-priceOfProduction));
                    }
                }
            }

            unitStrategy.restoreAllMovement(units); //restores movement-count for all units
        }

        // swaps the players each turn
        if (playerInTurn == Player.RED) {
            playerInTurn = Player.BLUE;
        } else {
            playerInTurn = Player.RED;
        }

    }

    private int getUnitCost(String unitType){
        return unitStrategy.getUnitCost(unitType);
    }

    public void changeWorkForceFocusInCityAt( Position p, String balance ) {
        getCityAt(p).setWorkForceFocus(balance);
    }

    public void changeProductionInCityAt( Position p, String unitType ) {
        getCityAt(p).setProduction(unitType);
    }

    @Override
    public void performUnitActionAt( Position p ) {
        Unit unit = getUnitAt( p );
        if(unit != null && unit.getOwner().equals(playerInTurn)){
            actionStrategy.performUnitAction(unit, p, this);
        }
    }

    @Override
    public void addUnit(Position p, Unit u) {
        units.put(p,u);
    }

    @Override
    public void removeUnit(Position p) {
        units.remove(p);
    }

    @Override
    public void addCity(Position p, Player player) {
        cities.put(p,new CityImpl(player));
    }

    public boolean fight(Position attackingPlayerPosition, Position defendingPlayerPosition) {
        if (attackStrategy.outcomeOfBattle(this,attackingPlayerPosition,defendingPlayerPosition)) {
            removeUnit(defendingPlayerPosition);
            return true;
        }else{
            removeUnit(attackingPlayerPosition);
            return false;
        }
    }

}