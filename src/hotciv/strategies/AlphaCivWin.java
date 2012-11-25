package hotciv.strategies;

import hotciv.framework.Game;
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
        return (game.getAge() >= -3000 ) ? Player.RED : null; //Red is the winner every time
    }

    @Override
    public void incrementWins(Player winner, Game game) {
        //nothing
    }

}
