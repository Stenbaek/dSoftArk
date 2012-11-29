package hotciv.standard;

import hotciv.factories.EtaFactory;
import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.City;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: stenbaek
 * Date: 28/11/12
 * Time: 16.18
 * To change this template use File | Settings | File Templates.
 */
public class TestEtaCiv {

    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new EtaFactory());
    }

    @Test
    public void firstTest(){
        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        c.setWorkForceFocus(GameConstants.productionFocus);
        c.setProduction(GameConstants.SETTLER);
        System.out.println("1 0 " + c.getCityFood());
        for(int i = 1; i < 30; i++){
            game.endOfTurn();
            game.endOfTurn();
            System.out.println("R:1 i:" + i + " F:" + c.getCityFood() + " PT:" + c.getProductionTreasury() + " P:" + c.getSize());
            if(c.getProductionTreasury() == 0){
                System.out.println(Utility.getFriendlySupport(game, new Position(1,1),c.getOwner()));
            }
        }
        System.out.println("");
        assertTrue("City food: " + c.getCityFood(),true);

        c.setWorkForceFocus(GameConstants.productionFocus);

        for(int i = 1; i < 22; i++){
            game.endOfTurn();
            game.endOfTurn();
            System.out.println("R:2 i:" + i + " F:" + c.getCityFood() + " PT:" + c.getProductionTreasury() + " P:" + c.getSize());
        }
        System.out.println("");
        assertTrue("City food: " + c.getCityFood(),true);
    }

     @Test
    public void secondTest(){
        CityImpl c = (CityImpl) game.getCityAt(new Position(4,1));
        c.setWorkForceFocus(GameConstants.foodFocus);
        c.setProduction(GameConstants.ARCHER);
        System.out.println("3 0 " + c.getCityFood());
        for(int i = 1; i < 22; i++){
            game.endOfTurn();
            game.endOfTurn();
            System.out.println("R:3 i:" + i + " F:" + c.getCityFood() + " PT:" + c.getProductionTreasury() + " P:" + c.getSize());
        }
        System.out.println("");
        assertTrue("City food: " + c.getCityFood(),true);

        c.setWorkForceFocus(GameConstants.productionFocus);

        for(int i = 1; i < 22; i++){
            game.endOfTurn();
            game.endOfTurn();
            System.out.println("R:4 i:" + i + " F:" + c.getCityFood() + " PT:" + c.getProductionTreasury() + " P:" + c.getSize());
        }
        System.out.println("");
        assertTrue("City food: " + c.getCityFood(),true);
    }
    //////////////////    Test World Aging    //////////////////
    @Test
    public void ageShouldInitiallyBe4000BC() {
        assertEquals("Age should be 4000BC at start of round", -4000, game.getAge());
    }

    @Test
    public void ageShouldAdvanceTimeBy100AtEndOfFirstRound() {
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Ending a round the time should be incremented by 100",
                -3900, game.getAge());
    }
    @Test
    public void ageShouldAdvanceFrom100BCTo1BC() {
        for(int i=0; i<78; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 100BC", -100, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1BC", -1, game.getAge());
    }
    @Test
    public void ageShouldAdvanceFrom1BCTo1AD(){
        for(int i=0; i<80; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1BC", -1, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1AD", 1, game.getAge());
    }
    @Test
    public void ageShouldAdvanceFrom1ADTo50AD(){
        for(int i=0; i<82; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1AD", 1, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 50AD", 50, game.getAge());
    }
    @Test
    public void ageShouldAdvanceFrom1700ADTo1750AD(){
        for(int i=0; i<150; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1700AD", 1700, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1750AD", 1750, game.getAge());

    }
    @Test
    public void ageShouldAdvanceFrom1875ADTo1900AD(){
        for(int i=0; i<162; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1875AD", 1875, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1900AD", 1900, game.getAge());

    }
    @Test
    public void ageShouldAdvanceFrom1900ADTo1905AD() {
        for(int i=0; i<164; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1900AD", 1900, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1905AD", 1905, game.getAge());
    }
    @Test
    public void ageShouldAdvanceFrom1YearFrom1970AD(){
        for(int i=0; i<192; i++) {
            game.endOfTurn(); // ends the turn and increments the time by 100
        }
        assertEquals("Its age 1970AD", 1970, game.getAge());
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Its age 1971AD", 1971, game.getAge());

    }
    //////////////////    Test Unit Actions    //////////////////
    @Test
    public void redSettlerShouldBeAbleToBuildCity() {
        game.addUnit(new Position(4,3), new Settler(Player.RED));
        game.performUnitActionAt(new Position(4,3));
        City c = game.getCityAt(new Position(4,3));
        assertNotNull("Should be city at (4,3)", c);
        assertNull("Settler should be removed", game.getUnitAt(new Position(4,3)));
        assertEquals("City's owner should be red", Player.RED, c.getOwner());
    }

    @Test
    public void redSettlerShouldNotBeAbleToPerformActionOnBluePlayersTurn() {
        game.addUnit(new Position(4,3), new Settler( Player.RED));
        game.endOfTurn();
        Position p = new Position(4,3);
        game.performUnitActionAt(p);
        assertNull("Red city should not be built on blue players turn", game.getCityAt(p));
    }

    //////////////////    Test World Layout    //////////////////
    @Test
    public void testRandomTilesOnTheDeltaMap() {
        assertEquals("Should be plains at (5,5)", GameConstants.PLAINS,
                game.getTileAt(new Position(5, 5)).getTypeString());
        assertEquals("Should be hills at (4,9)", GameConstants.HILLS,
                game.getTileAt(new Position(4, 9)).getTypeString());
        assertEquals("Should be mountains at (0,5)", GameConstants.MOUNTAINS,
                game.getTileAt(new Position(0, 5)).getTypeString());
        assertEquals("Should be oceans at (8,6)", GameConstants.OCEANS,
                game.getTileAt(new Position(8, 6)).getTypeString());
        assertEquals("Should be forest at (9,1)", GameConstants.FOREST,
                game.getTileAt(new Position(9, 1)).getTypeString());
        assertEquals("Should be oceans at (13,13)", GameConstants.OCEANS,
                game.getTileAt(new Position(13, 13)).getTypeString());
        assertEquals("Should be hills at (14,5)", GameConstants.HILLS,
                game.getTileAt(new Position(14, 6)).getTypeString());
        assertEquals("Should be plains at (7,2)", GameConstants.PLAINS,
                game.getTileAt(new Position(7, 2)).getTypeString());
    }

    @Test
    public void thereShouldBe12ForestsOnTheMap() {
        int n = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                String l = game.getTileAt(new Position(r, c)).getTypeString();
                if (l.equals(GameConstants.FOREST)) {
                    n++;
                }
            }
        }
        assertEquals("There should be 12 forests on the map", 12, n);
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
    @Test
    public void thereShouldBe139PlainsOnTheMap() {
        int n = 0;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                String l = game.getTileAt(new Position(r, c)).getTypeString();
                if (l.equals(GameConstants.PLAINS)) {
                    n++;
                }
            }
        }
        assertEquals("There should be 139 plain tiles on the map", 139, n);
    }

    @Test
    public void shouldHaveBlueCityAt4_5() {
        City c = game.getCityAt(new Position(4,5));
        assertNotNull("There should be a city at (4,5)", c);
        Player p = c.getOwner(); // should be blue
        assertEquals( "City at (4,5) should be owned by blue",
                Player.BLUE, p );
    }

    @Test
    public void shouldHaveRedCityAt8_12() {
        City c = game.getCityAt(new Position(8,12));
        assertNotNull("There should be a city at (8,12)", c);
        Player p = c.getOwner(); // should be red
        assertEquals( "City at (8,12) should be owned by blue",
                Player.RED, p );
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


}
