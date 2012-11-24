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

    private CivWinStrategy betawin = new BetaCivWin();
    //private CivWinStrategy epsilonwin = new EpsilonCivWin();

    @Override
    public Player getWinner(GameImpl game) {
        if(game.getAge() >= -1000)
            return betawin.getWinner(game);
        else
            return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
