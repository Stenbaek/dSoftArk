package hotciv.strategies;

import hotciv.framework.*;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Settler;


/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 21/11/12
 * Time: 07.32
 * To change this template use File | Settings | File Templates.
 */
public class GammaCivAction implements CivActionStrategy {

    @Override
    public void performUnitAction( Unit unit, Position p, Game game ){
        if(unit.getClass().equals(Settler.class)){
            game.removeUnit(p);
            game.addCity(p,unit.getOwner());
        }else if(game.getUnitAt(p).getClass().equals(Archer.class)){
            Archer archer = (Archer) unit;
            archer.toggleFortified();
        }else{
            //nothing happens, no unit is chosen
        }
    }

}
