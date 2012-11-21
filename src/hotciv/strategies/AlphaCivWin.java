package hotciv.strategies;

import hotciv.framework.Player;
import hotciv.framework.CivWinStrategy;
import hotciv.standard.GameImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 17-11-12
 * Time: 17:05
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivWin implements CivWinStrategy {

    public Player getWinner(GameImpl game){
        return Player.RED; //Red is the winner every time
    }


}
