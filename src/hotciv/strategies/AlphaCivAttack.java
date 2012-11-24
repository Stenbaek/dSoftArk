package hotciv.strategies;

import hotciv.framework.CivAttackStrategy;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Unit;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 22/11/12
 * Time: 23.07
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivAttack implements CivAttackStrategy {

    @Override
    public boolean outcomeOfBattle(Game game, Position attackersPosition, Position defendersPosition) {
        return true;
    }
}
