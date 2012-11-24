package hotciv.framework;

import hotciv.standard.GameImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 17-11-12
 * Time: 17:03
 * To change this template use File | Settings | File Templates.
 */
public interface CivWinStrategy {
    Player getWinner(GameImpl game);

    void incrementWins(Player winner);
}
