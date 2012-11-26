package hotciv.strategies;

import hotciv.framework.*;
import hotciv.standard.Utility;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 22/11/12
 * Time: 23.10
 * To change this template use File | Settings | File Templates.
 */
public class EpsilonCivAttack implements CivAttackStrategy {

    private DieRollStrategy dieRollStrategy;

    public EpsilonCivAttack(DieRollStrategy dieRollStrategy) {
        this.dieRollStrategy = dieRollStrategy;
    }

    @Override
    public boolean outcomeOfBattle(Game game, Position attackersPosition, Position defendersPosition) {
        // ************************************
        // ** Calculating defending strength **
        // ************************************
        Unit attackingUnit = game.getUnitAt(attackersPosition);
        int attackingStrength = attackingUnit.getAttackingStrength();
        //Adding friendly support to attacking strength
        attackingStrength += Utility.getFriendlySupport(game, attackersPosition, attackingUnit.getOwner());
        //Adding Terrain factor to attacking strength
        attackingStrength *= Utility.getTerrainFactor(game, attackersPosition);

        // ************************************
        // ** Calculating attacking strength **
        // ************************************
        Unit defendingUnit = game.getUnitAt(defendersPosition);
        int defensiveStrength = defendingUnit.getDefensiveStrength();
        //Adding friendly support to defensive strength
        defensiveStrength += Utility.getFriendlySupport(game, defendersPosition, defendingUnit.getOwner());
        //Adding Terrain factor to defensive strength
        defensiveStrength *= Utility.getTerrainFactor(game, defendersPosition);

        return (attackingStrength * dieRollStrategy.roll() > defensiveStrength * dieRollStrategy.roll());
    }
}
