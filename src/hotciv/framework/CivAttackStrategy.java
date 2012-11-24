package hotciv.framework;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 22/11/12
 * Time: 23.03
 * To change this template use File | Settings | File Templates.
 */
public interface CivAttackStrategy {

    public boolean outcomeOfBattle(Game game, Position attackersPosition, Position defendersPosition);

}
