package hotciv.strategies;

import hotciv.framework.CivWinStrategy;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 24-11-12
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
public class ZetaCivWin implements CivWinStrategy {

    private CivWinStrategy betaWin = new BetaCivWin();
    private CivWinStrategy epsilonWin = new EpsilonCivWin();

    @Override
    public Player getWinner(GameImpl game) {
        if(game.getAge() <= -2100)
            return betaWin.getWinner(game);
        else return epsilonWin.getWinner(game);
    }

    @Override
    public void incrementWins(Player winner) {
        epsilonWin.incrementWins(winner);
    }
}
