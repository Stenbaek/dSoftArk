package hotciv.strategies;

import hotciv.framework.CivWinStrategy;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.standard.GameImpl;

import javax.swing.text.html.HTMLDocument;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 22/11/12
 * Time: 23.08
 */
public class EpsilonCivWin implements CivWinStrategy {

    private HashMap<Player,Integer> successfulAttacks;

    public EpsilonCivWin(){
        successfulAttacks = new HashMap();
    }

    @Override
    public Player getWinner(GameImpl game) {
        for(Iterator i = successfulAttacks.entrySet().iterator(); i.hasNext();){
            Map.Entry pairs = (Map.Entry) i.next();
            if (pairs.getValue().equals(3)){
                return (Player) pairs.getKey();
            }
        }
        return null;
    }

    @Override
    public void incrementWins(Player winner, Game game) {
        Integer successes = 1;
        if(successfulAttacks.containsKey(winner)){
            successes = successfulAttacks.get(winner)+1;
            successfulAttacks.remove(winner);
        }
        successfulAttacks.put(winner,successes);
    }
}
