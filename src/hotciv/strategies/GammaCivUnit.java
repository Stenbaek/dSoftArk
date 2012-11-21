package hotciv.strategies;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Settler;


/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 07.32
 * To change this template use File | Settings | File Templates.
 */
public class GammaCivUnit extends AlphaCivUnit {

    private Game game;

    public void setGame(Game game) {
        super.setGame(game);
        this.game = game;
    }

    @Override
    public void performUnitActionAt(Position p){
        Unit unit = game.getUnitAt(p);
        if(unit != null && unit.getOwner().equals(game.getPlayerInTurn())){
            if(unit.getClass().equals(Settler.class)){
                Player owner = unit.getOwner();
                game.getMapStrategy().getUnits().remove(p);
                game.getMapStrategy().addCity(p,owner);
            }else if(game.getUnitAt(p).getClass().equals(Archer.class)){
                Archer archer = (Archer) unit;
                archer.toggleFortified();
            }else{
                //nothing happens, no unit is chosen
            }
        }
    }

}
