package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

/** Skeleton class for AlphaCiv test cases

   This source code is from the book
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author:
     Henrik B Christensen
     Computer Science Department
     Aarhus University

   This source code is provided WITHOUT ANY WARRANTY either
   expressed or implied. You may study, use, modify, and
   distribute it for non-commercial purposes. For any
   commercial use, see http://www.baerbak.com/
*/
public class TestAlphaCiv {
    private Game game;
    /** Fixture for alphaciv testing. */
    @Before
    public void setUp() {
        game = new GameImpl();
    }

    @Test
    public void shouldHaveRedCityAt1_1() {
        City c = game.getCityAt(new Position(1,1));
        assertNotNull("There should be a city at (1,1)", c);
        Player p = c.getOwner();
        assertEquals( "City at (1,1) should be owned by red",
                Player.RED, p );
    }

    @Test
    public void shouldHaveBlueCityAt4_1() {
        City c = game.getCityAt(new Position(4,1));
        assertNotNull("There should be a city at (4,1)", c);
        Player p = c.getOwner();
        assertEquals( "City at (4,1) should be owned by blue",
                Player.BLUE, p );
    }

    @Test
    public void shouldHaveOceanAt1_0(){
        Tile t = game.getTileAt(new Position(1,0));
        assertNotNull("There should be a tile at position (1,0)" + t.getPosition(),t);
        assertEquals("There should be an ocean at (1,0)", Ocean.class, t.getClass());
    }


    @Test
    public void shouldHaveMountainAt2_2(){
        Tile t = game.getTileAt(new Position(2,2));
        assertNotNull("There should be a tile at position (2,2)" + t.getPosition(),t);
        assertEquals("There should be an mountain at (2,2)", Mountain.class, t.getClass());
    }

    @Test
    public void shouldHaveHillAt0_1(){
        Tile t = game.getTileAt(new Position(0,1));
        assertNotNull("There should be a tile at position (0,1)" + t.getPosition(),t);
        assertEquals("There should be an mountain at (0,1)", Hill.class, t.getClass());
    }

    @Test
    public void redShouldBeFirstInTurn(){
        Player p = game.getPlayerInTurn();
        assertEquals("Red should be first in turn",
            Player.RED, p);
    }

    @Test
    public void afterRedBlueShouldBeInTurn(){
        game.endOfTurn();
        Player p = game.getPlayerInTurn();
        assertEquals("After red, blue player is in turn",
            Player.BLUE, p);
    }

    @Test
    public void afterBlueRedShouldBeInTurn(){
        game.endOfTurn(); game.endOfTurn();
        Player p = game.getPlayerInTurn();
        assertEquals("After blue, red player is in turn",
            Player.RED, p);
    }

    @Test
    public void unitCannotMoveOverMountain(){
        assertFalse("Units cannot move over mountain tiles",
            game.moveUnit(new Position(3,2),new Position(2,2)));
    }

    @Test
    public void unitCannotMoveOverOcean(){
        assertFalse("Units cannot move over ocean tiles",
            game.moveUnit(new Position(2,0),new Position(1,0)));
    }

    @Test
    public void cityPopulationStartsAtOne(){
        City c= new CityImpl(Player.RED);
        assertEquals("city population should start at one",1,c.getSize());
    }

    @Test
    public void cityPopulationRemainsAtOne(){
        CityImpl cStart = (CityImpl)game.getCityAt(new Position(1,1));
        assertEquals("City population stays at one",1,cStart.getSize());
        game.endOfTurn();
        game.endOfTurn();//2 turns to checl that population stays at one
        CityImpl cAfterTwoTurns = (CityImpl)game.getCityAt(new Position(1,1));
        assertEquals("The population of a city remains at one, no matter how many turns",
             1,cAfterTwoTurns.getSize());
    }

    @Test
    public void unitsCanMoveOneTile(){
        Unit u = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
        assertEquals("Units can only move 1 tile",1,u.getMoveCount());
    }

    @Test
    public void worldShouldHavePlainsEverywhereElse(){
        for(int r = 0; r < 16; r++){
            for(int c = 0; c < 16; c++){
                Tile t = game.getTileAt(new Position(r,c));
                assertNotNull("There should never be a null tile - plains everywhere!",t);
                if(!t.getClass().equals(Mountain.class) && !t.getClass().equals(Forest.class)
                        && !t.getClass().equals(Hill.class) && !t.getClass().equals(Ocean.class)){
                    assertEquals("There should be plains everywhere else",Plain.class,t.getClass());
                }
            }
        }
    }
    @Test
    public void gameStartsAt4000bc(){
        int startage = game.getAge();
        assertEquals("The game should start at at 4000bc ", -4000, startage);
    }
    @Test
    public void redShouldWin(){
        Player p1 = game.getWinner();
        assertEquals("Red should dbe the winner of the game",Player.RED,p1);
    }
    public void shouldInitiallyBeRedAcherAt2x0() {
        assertNotNull("2,0 should not be null",
                game.getUnitAt(new Position(2,0))); //unit at 2,0
        assertEquals("2,0 should be and archer",
                GameConstants.ARCHER, //archer
                game.getUnitAt(new Position(2,0)).getTypeString()); //returns unit at 2,0's type
        assertEquals("2,0 should be a red unit",
                Player.RED,
                game.getUnitAt(new Position(2,0)).getOwner()); //returns owner of 2,0
    }

    @Test
    public void shouldInitiallyBeBlueLegionAt3x2() {
        assertNotNull("3,2 should not be null",
                game.getUnitAt(new Position(3,2))); //blue legion at 3,2
        assertEquals("3,2 should be an blue legion",
                GameConstants.LEGION, //legion
                game.getUnitAt(new Position(3,2)).getTypeString()); //returns unit at 3,2's type
        assertEquals("3,2 should be a blue unit",
                Player.BLUE,
                game.getUnitAt(new Position(3,2)).getOwner()); //returns owner of 3,2
    }

    @Test
    public void shouldInitiallyBeRedSettlerAt4x3() {
        assertNotNull("4,3 should not be null",
                game.getUnitAt(new Position(4,3))); //red settler at 4,3
        assertEquals("3,2 should be an red settler",
                GameConstants.SETTLER, //settler
                game.getUnitAt(new Position(4,3)).getTypeString()); //returns unit at 3,2's type
        assertEquals("4,3 should be a red unit",
                Player.RED,
                game.getUnitAt(new Position(4,3)).getOwner()); //returns owner of 3,2
    }
    @Test
    public void shouldAdvanceTimeBy100AtEndOfFirstRound() {
        game.endOfTurn();
        game.endOfTurn();
        assertEquals("Ending a round the time should be incremented by 100",
                -3900, game.getAge());
    }			// start time is -4000 + 100 = - 3900

}