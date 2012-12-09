package hotciv.standard;

import hotciv.framework.*;

public class GameTranscript implements Game{
    private Game game;

    public GameTranscript(Game g){
        game = g;

    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        if (game.getWinner() != null)
            System.out.println(game.getWinner() + " player has won the game.");
        return game.getWinner();
    }

    @Override
    public int getAge() {
        return game.getAge();
    }
    @Override
    public void addCity(Position p, Player owner) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addUnit(Position p, Unit u) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeUnit(Position p) {
        game.removeUnit(p);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        Unit u1 = game.getUnitAt(from);
        Unit u2 = game.getUnitAt(to);
        if (u2 == null) {
            System.out.println(u1.getOwner() + " player has moved " + u1.getTypeString()
                    + " from " + from + " to " + to);
            return game.moveUnit(from, to);
        } else if (u2.getOwner() != u1.getOwner()) {
            boolean r = game.moveUnit(from, to);
            if (game.getUnitAt(to).getOwner() == u1.getOwner()) {
                System.out.println(u1.getOwner() + " player's " + u1.getTypeString()
                        + " from position " + from + " defeats " + u2.getOwner()
                        + " player's " + u2.getTypeString()
                        + " and moves to position " + to);
                return r;
            } else {
                System.out.println(u1.getOwner() + " player's " + u1.getTypeString()
                        + " from position " + from + " attacks " + u2.getOwner()
                        + " player's " + u2.getTypeString() + " at position" + to
                        + " but is defeated and destroyed");
                return r;
            }
        } else {
            return game.moveUnit(from, to);
        }
    }

    @Override
    public void endOfTurn() {
        Player player = game.getPlayerInTurn();
        System.out.println(player + " player ends his turn.");
        game.endOfTurn();

    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        game.changeWorkForceFocusInCityAt(p, balance);
        System.out.println("city at " + p + " has changed work focus to " + balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        game.changeProductionInCityAt(p, unitType);
        System.out.println("city at " + p + " has changed production to " + unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        Unit u = game.getUnitAt(p);
        if (u.getTypeString() == GameConstants.SETTLER
                && game.getTileAt(p).getTypeString() == GameConstants.PLAINS)
            System.out.println(u.getOwner() + " player has build a city at position " + p);
        game.performUnitActionAt(p);


    }



}
