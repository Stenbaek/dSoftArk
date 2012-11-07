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
      //hej
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
  public void redShouldBeFirstInTurn(){
      Player p = game.getPlayerInTurn();
      assertEquals("Red should be first in turn",
        Player.RED, p);
  }

  @Test
  public void afterRedBlueShouldBeInTurn(){
      game.endOfTurn();
      Player p = game.getPlayerInTurn();
      assertEquals("Red should be first in turn",
        Player.BLUE, p);
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
      assertEquals("The population of a city remains at one, no matter how many turns",1,cAfterTwoTurns.getSize());
  }
  @Test
  public void unitsCanMoveOneTile(){
      Unit u = new UnitImpl(GameConstants.ARCHER, Player.BLUE);
      assertEquals("Units can only move 1 tile",1,u.getMoveCount());
  }
}