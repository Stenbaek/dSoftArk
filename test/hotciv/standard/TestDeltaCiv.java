package hotciv.standard;

import hotciv.factories.DeltaFactory;
import hotciv.standard.tiles.Forest;
import hotciv.standard.tiles.Mountain;
import hotciv.standard.tiles.Plain;
import hotciv.strategies.*;
import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 20-11-12
 * Time: 21:09
 */
public class TestDeltaCiv {

    private Game game;

    @Before
    public void setUp(){
        game = new GameImpl(new DeltaFactory());

    }

    @Test
    public void thereShouldBeACityAt8_12(){
        assertNotNull("There should be a Settler at (8,12)",game.getCityAt(new Position(8, 12)));
        assertEquals("City should be at (8,12)", CityImpl.class, game.getCityAt(new Position(8,12)).getClass());
    }

    @Test
    public void thereShouldBeAMountainAt0_5(){
        assertNotNull("There should be a Mountain at (0,5)",game.getTileAt(new Position(0, 5)));
        assertEquals("Mountain should be at (0,5)", Mountain.class, game.getTileAt(new Position(0, 5)).getClass());
    }

    @Test
    public void thereShouldBeAForestAt5_5(){
        assertNotNull("There should be a Forest at (5,5)",game.getTileAt(new Position(5, 5)));
        assertEquals("Forest should be at (5,5)", Forest.class, game.getTileAt(new Position(5, 5)).getClass());
    }

    @Test
    public void thereShouldBeAPlainAt5_6(){
        assertNotNull("There should be a Plain at (5,6)",game.getTileAt(new Position(5, 6)));
        assertEquals("Plain should be at (5,6)", Plain.class, game.getTileAt(new Position(5, 6)).getClass());
    }

    @Test
    public void thereShouldBeATileAtEveryCoordinate(){
        for(int r = 0; r < GameConstants.WORLDSIZE; r++){
            for(int c = 0; c < GameConstants.WORLDSIZE; c++){
                assertNotNull("There should be a Tile at (" + r + "," + c + ") " + game.getTileAt(new Position(r,c)).getClass(), game.getTileAt(new Position(r,c)));
            }
        }
    }

    @Test
    public void thereShouldBe86OceansOnTheMap() {
        int n = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                String l = game.getTileAt(new Position(r, c)).getTypeString();
                if (l.equals(GameConstants.OCEANS)) {
                    n++;
                }
            }
        }
        assertEquals("There should be 86 ocean tiles on the map", 86, n);
    }

    @Test
    public void thereShouldBe10HillsOnTheMap() {
        int n = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                String l = game.getTileAt(new Position(r, c)).getTypeString();
                if (l.equals(GameConstants.HILLS)) {
                    n++;
                }
            }
        }
        assertEquals("There should be 10 hill tiles on the map", 10, n);
    }

    @Test
    public void thereShouldBe137PlainsOnTheMap() {
        int n = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                String l = game.getTileAt(new Position(r, c)).getTypeString();
                if (l.equals(GameConstants.PLAINS)) {
                    n++;
                }
            }
        }
        assertEquals("There should be 137 plain tiles on the map", 137, n);
    }

    @Test
    public void thereShouldBe14ForestsOnTheMap() {
        int n = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                String l = game.getTileAt(new Position(r, c)).getTypeString();
                if (l.equals(GameConstants.FOREST)) {
                    n++;
                }
            }
        }
        assertEquals("There should be 14 forests on the map", 14, n);
    }
    @Test
    public void thereShouldBe9MountainsOnTheMap() {
        int n = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                String l = game.getTileAt(new Position(r, c)).getTypeString();
                if (l.equals(GameConstants.MOUNTAINS)) {
                    n++;
                }
            }
        }
        assertEquals("There should be 9 mountain tiles on the map", 9, n);
    }
}