package hotciv.CivForms;

import hotciv.framework.CivMapStrategy;
import hotciv.framework.Player;
import hotciv.framework.Tile;
import hotciv.standard.*;
import hotciv.framework.GameConstants;
import hotciv.standard.CityImpl;


/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
public class AlphaCivMap implements CivMapStrategy {

    private int size = GameConstants.WORLDSIZE;
    private CityImpl[][] cities = new CityImpl[size][size];
    private Unit[][] units;
    private Tile[][] t = new Tile[size][size]; // 16x16=256

    @Override
    public Unit[][] getUnits() {
        units[2][0] = new Archer(Player.RED);
        units[3][2] = new Legion(Player.BLUE);
        units[4][3] = new Settler(Player.RED);
        return units;
    }

    @Override
    public CityImpl[][] getCities() {
        cities[1][1] = new CityImpl(Player.RED);
        cities[4][1] = new CityImpl(Player.BLUE);
        return cities;
    }

    @Override
    public Tile[][] getWorld() {
        for (int r = 0; r < size; r++) { // all tiles are plains...
            for (int c = 0; c < size; c++) {
                t[r][c] = new Tile(Plain.class, r, c);
            }
        }
        // except for:
        t[1][0] = new Tile(Ocean.class, 1, 0);
        t[0][1] = new Tile(Hill.class, 0, 1);
        t[2][2] = new Tile(Mountain.class, 2, 2);

        return t;
    }
}
