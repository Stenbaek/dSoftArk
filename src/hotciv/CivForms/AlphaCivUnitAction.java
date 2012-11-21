package hotciv.CivForms;

import hotciv.framework.CivUnitStrategy;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.GameImpl;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivUnitAction implements CivUnitStrategy {

    public void setGame(GameImpl game) {}


    public void performUnitActionAt(Position p) {} // does nothing

    @Override
    public Map<Position, Unit> getAllUnits() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addUnit(Position p, Unit u) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
