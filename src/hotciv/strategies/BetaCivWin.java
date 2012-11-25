package hotciv.strategies;

import hotciv.framework.*;
import hotciv.standard.*;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 17-11-12
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */
public class BetaCivWin implements CivWinStrategy {

    public Player getWinner(GameImpl game) {
        City c1 = game.getCityAt(new Position(1,1)); // initially red city
        City c2 = game.getCityAt(new Position(4,1)); // initially blue city
        if ( c1.getOwner().equals(c2.getOwner()) ) { // if both cities are owned by the same player
            return c1.getOwner(); // player has won the game
        } else {
            return null; // the game has not been won
        }
    }

    @Override
    public void incrementWins(Player winner) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
