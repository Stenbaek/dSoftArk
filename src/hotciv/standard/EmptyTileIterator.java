package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 23/11/12
 * Time: 15.25
 * To change this template use File | Settings | File Templates.
 */
public class EmptyTileIterator implements Iterator{

    private Position p;
    private Player player;
    private Game game;

    public EmptyTileIterator(Position p, Player player, Game game){
        this.p = p;
        this.player = player;
        this.game = game;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Position next() {
        int[] pos = {this.p.getRow(), this.p.getColumn()};
        int sign = 1; // 1=positive -1=negative
        int axis = 0; // 0=y 1=x
        int counter = 0;
        if (isTileEmpty(p, player)) {
            return p;
        } else {
            while(true) { // while true? what ?
                for(axis=0; axis<2; axis++) {
                    for(int i=0; i<counter; i++) {
                        pos[axis] = pos[axis] + sign;
                        if (isTileEmpty(new Position(pos[0], pos[1]), player)) {
                            return new Position(pos[0], pos[1]);
                        }
                    }
                    if(axis==0)
                        sign = sign*(-1);
                }
                counter++;
            }
        }
    }

    @Override
    public void remove() {
        // nothing
    }

    private boolean isTileEmpty(Position p, Player player) {
        if (p.getColumn() < 0 || p.getRow() < 0 || p.getColumn() > (GameConstants.WORLDSIZE-1)
                || p.getRow() > (GameConstants.WORLDSIZE-1)) {
            // Returns false if we try to place a unit outside of the map
            return false;
        } else if (game.getUnitAt(p) != null) {
            return false; // tile is taken

        } else if ( !game.getTileAt(p).isHabitable() ) {
            return false; // tile is inhabitable

        } else if (game.getCityAt(p) != null && game.getCityAt(p).getOwner() != player) {
            return false; // there is a city on this tile and it is not the same owner as the unit
        }
        return true;
    }
}
