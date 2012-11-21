package hotciv.CivForms;

import hotciv.framework.*;
import hotciv.standard.CityImpl;
import hotciv.standard.units.Archer;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivUnitAction implements CivUnitStrategy {

    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }


    public void performUnitActionAt(Position p) {} // does nothing

    @Override
    public boolean moveUnit(Position from, Position to) {
        // Getting the unit
        Unit theUnitInMove = game.getUnitAt(from);
        Player playerInTurn = game.getPlayerInTurn();
        CivMapStrategy mapStrategy = game.getMapStrategy();

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

            Unit unitPossiblyUnderAttack = game.getUnitAt(to); // finds the unit possibly coming under attack

            if (unitPossiblyUnderAttack != null
                    && unitPossiblyUnderAttack.getOwner() == playerInTurn) {
                return false; // if there is a unit and the unit is owned by the player in turn
            }else if(unitPossiblyUnderAttack != null
                    && unitPossiblyUnderAttack.getOwner() != playerInTurn){
                mapStrategy.getUnits().remove(to);
            }

            Tile moveToTile = game.getTileAt(to); // finds the tile at the move TO position
            if (!moveToTile.isHabitable()) {
                return false; // mountains and oceans cannot have units on them
            }

            // *** This is only executed if all tests pass ***
            // change the move count
            theUnitInMove.changeMoveCounter(-1);



            // The actual move of the unit
            mapStrategy.getUnits().moveUnitToNewPosition(from,to);

            // The possible city at the to position
            CityImpl cityPossiblyCaptured = (CityImpl) game.getCityAt(to);

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

    @Override
    public int getUnitCost(String unitType) {
        if(unitType.equals(GameConstants.ARCHER)) return 10;
        if(unitType.equals(GameConstants.LEGION)) return 15;
        if(unitType.equals(GameConstants.SETTLER)) return 30;
        return 0;
    }

}
