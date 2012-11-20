package hotciv.CivForms;

import hotciv.framework.CivUnitStrategy;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class GammaCivUnitAction implements CivUnitStrategy {

    private GameImpl game;

    public void setGame(GameImpl game) {
        this.game = game;
    }

    @Override
    public void performUnitActionAt(Position p) {
        UnitImpl u = (UnitImpl) game.getUnitAt(p);
        if (game.getPlayerInTurn().equals(units.getOwner())) { // action can only be performed by player in turn
            if (GameConstants.ARCHER.equals(u.getTypeString())) { // if archer
                if (u.getDefensiveStrength() == 3) { // if not fortified
                    u.setDefensiveStrength(6); // double defensive strength
                    u.changeMoveCount(-1);	// movement counter is set to zero
                } else { // if fortified
                    u.setDefensiveStrength(3); // defense strength set to half
                }
            } else if (GameConstants.SETTLER.equals(u.getTypeString())) { // if settler
                game.setCityAt(p, new CityImpl(u.getOwner())); // set new city at unit's position
                game.setUnitAt(p, null); // remove unit
            }
        }
    }

}
