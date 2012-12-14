package hotciv.standard.classes;

import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.Stack;

public class StubObserverImpl implements GameObserver {
    private Stack<Player> players = new Stack<Player>();
    private Stack<Integer> age = new Stack<Integer>();
    private Stack<Position> positions = new Stack<Position>();

    @Override
    public void tileFocusChangedAt(Position position) {

    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        this.age.push(age);
        this.players.push(nextPlayer);
    }

    @Override
    public void worldChangedAt(Position pos) {
        this.positions.push(pos);

    }

    public Player popPlayer() {
        return players.pop();
    }

    public int popAge() {
        return age.pop();
    }

    public Position popLastChangedPosition() {
        return positions.pop();
    }
}
