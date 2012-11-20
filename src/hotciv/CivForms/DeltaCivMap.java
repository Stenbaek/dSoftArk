package hotciv.CivForms;

import hotciv.framework.CivMapStrategy;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.Player;
import hotciv.standard.CityImpl;
import hotciv.standard.UnitImpl;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
public class DeltaCivMap implements CivMapStrategy {

    private static int size = GameConstants.WORLDSIZE;
    private CityImpl[][] cities = new CityImpl[size][size];
    private UnitImpl[][] units = new UnitImpl[size][size];
    private Tile[][] t = new Tile[size][size]; // 16x16=256

    @Override
    public UnitImpl[][] getUnits() {
        units[4][4] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
        units[3][8] = new UnitImpl(GameConstants.ARCHER, Player.RED);
        units[5][5] = new UnitImpl(GameConstants.SETTLER, Player.RED);
        return units;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CityImpl[][] getCities() {
        cities[8][12] = new CityImpl(Player.RED);
        cities[4][5] = new CityImpl(Player.BLUE);
        return cities;
    }

    @Override
    public Tile[][] getWorld(Position p) {
        String[] layout = deltaCivMap();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (layout[r].charAt(c) == 'o') {
                    t[r][c] = new Ocean(p);
                } else if (layout[r].charAt(c) == 'p') {
                    t[r][c] = new TileImpl(GameConstants.PLAINS, r, c);
                } else if (layout[r].charAt(c) == 'm') {
                    t[r][c] = new TileImpl(GameConstants.MOUNTAINS, r, c);
                } else if (layout[r].charAt(c) == 'h') {
                    t[r][c] = new TileImpl(GameConstants.HILLS, r, c);
                } else if (layout[r].charAt(c) == 'f')
                    t[r][c] = new TileImpl(GameConstants.FOREST, r, c);
            }
        }
        return t;  //To change body of implemented methods use File | Settings | File Templates.
    }
    private String[] deltaCivMap() {
        String[] stringMap = new String[16];
        try {

            FileInputStream fStream = new FileInputStream("map/map.txt");

            DataInputStream input = new DataInputStream(fStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            String line = "";

            // reads map from file
            int i = 0;
            while (( line = br.readLine()) != null) {
                stringMap[i] = line;
                i++; // Incrementing i
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringMap;
    }
}
