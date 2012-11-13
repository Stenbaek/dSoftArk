package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import java.security.acl.Owner;

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
        Unit u = new UnitImpl(GameConstants.ARCHER, Player.RED);
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
        int startAge = game.getAge();
        assertEquals("The game should start at at 4000bc ", -4000, startAge);
    }
    @Test
    public void redShouldWin(){
        Player p1 = game.getWinner();
        assertEquals("Red should dbe the winner of the game",Player.RED,p1);
    }
    @Test
    public void shouldInitiallyBeRedArcherAt2x0() {
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
    @Test
    public void redShouldNotBeAbleToMoveBlueUnits(){
        Player p = game.getPlayerInTurn();
        // test that it indeed is reds turn
        assertEquals("current player in turn is red", Player.RED, p);
        // fetches a blue unit
        Unit u = game.getUnitAt(new Position(3,2));
        // tests that it indeed blue
        assertEquals("This unit should be blue", Player.BLUE, u.getOwner());
        boolean b = game.moveUnit(new Position(3,2), new Position(3,3));
        assertFalse("Not a legal move, red should not be able to move a blue unit", b);
    }
    @Test
    public void blueShouldNotBeAbleToMoveRedUnits(){
        game.endOfTurn();
        Player p = game.getPlayerInTurn();
        assertEquals("current player in turn is blue", Player.BLUE,p);
        Unit u = game.getUnitAt(new Position(2,0));
        assertEquals("This unit should be red",Player.RED, u.getOwner());
        boolean b = game.moveUnit(new Position(2,0),new Position(3,0));
        assertFalse("not a legal move, blue should not be able to move a red unit",b);

    }
    /**
     * Tests that when adding production to a cities production treasury
     * the amount is reflected in the cities treasury
     */
    @Test
    public void shouldReturn100WhenSetProductionTo100() {
        CityImpl c = new CityImpl(Player.RED);
        c.addProductionTreasury(100);
        assertEquals("Should return the ammount (100) set by calling setProductionTreasury",
        100, c.getProductionTreasury());
    }

    /**
     * Tests adding production 2x100 which should be 200
     */
    @Test
    public void shouldReturn200AfterSetOf2x100() {
        CityImpl c = new CityImpl(Player.RED);
        c.addProductionTreasury(100);
        c.addProductionTreasury(100);
        assertEquals("Should return the ammount (200) set by calling setProductionTreasury(100) two times",
        200, c.getProductionTreasury());
    }

    /**
     * Tests endOfRound addition of production to cities
     */
    @Test
    public void citiesProduce6ProductionAfterEndOfEachRound() {
        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        assertEquals("Cities should start with 0 in production treasury", 0 ,c.getProductionTreasury());
        game.endOfTurn();
        game.endOfTurn(); // ends the current round so resources added
        CityImpl c2 = (CityImpl) game.getCityAt(new Position(1,1));
        assertEquals("Cities should have 6 production after first round", 6, c2.getProductionTreasury());
    }

    /**
     * Tests that a city that isn't producing anything a city should have 12 production after 2 rounds (4 turns)
     */
    @Test
    public void after2RoundsCitiesShouldHave12ProductionWithNothingInProduction() {
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();
        game.endOfTurn();// incrementing time by two rounds
        CityImpl c = (CityImpl) game.getCityAt(new Position(1,1));
        assertEquals("After two rounds the city at (1,1) should have 12 production in it's treasury",
                12, c.getProductionTreasury());
    }
    @Test
     public void anyUnitCannotMoveToAnyMountainTileFromAnyDirection() {
        game.endOfTurn(); // Now blue is in turn
        boolean b = game.moveUnit(new Position(3,2), new Position(3,3));
        assertTrue("This move should be legal. blue is in turn, " +
                "its his unit and there's nothing on 3,3 which is plains", b);
        game.endOfTurn();
        game.endOfTurn(); // its now blue again
        // the move to the mountain tile
        b = game.moveUnit(new Position(3,3), new Position(2,2));
        assertFalse("The unit should not be able to move to 2,2 as it is a mountain tile", b);
    }

    @Test
    public void archerAt2_0CannotMoveToOceanTileAt1_0(){
        Unit archer = game.getUnitAt(new Position(2, 0));
        assertEquals("The unit at (2,0) Should be an archer", game.getUnitAt(new Position(2, 0)).getTypeString(), GameConstants.ARCHER);
        assertTrue("The archer should be own by RED", (archer.getOwner() == Player.RED));
        assertFalse("The archer should not be able to move to ocean tile", game.moveUnit(new Position(2,0),new Position(1,0)));
    }
    @Test
    public void archerCanMoveToEmptyPlainTile(){
        Unit archer = game.getUnitAt(new Position(2, 0));
        assertEquals("The unit at (2,0) Should be an archer", game.getUnitAt(new Position(2, 0)).getTypeString(), GameConstants.ARCHER);
        assertTrue("The archer should be able to move to empty plain", game.moveUnit(new Position(2, 0), new Position(3, 0)));
    }

    @Test
    public void typeAndOwnerRemainsUnchangedAfterMovement(){
        Unit archer = game.getUnitAt(new Position(2, 0));
        Player ownerRoundOne = archer.getOwner();
        String typeRoundOne = archer.getTypeString();

        game.moveUnit(new Position(2,0), new Position(3,0));

        Unit archerAfterMovement = game.getUnitAt(new Position(3, 0));
        assertEquals("Owner of unit should not have changed during movement",ownerRoundOne,archerAfterMovement.getOwner());
        assertEquals("Type of unit should not have changed during movement",typeRoundOne,archerAfterMovement.getTypeString());
    }

    @Test
    public void redUnitAttacksAndDestroysBlueUnit(){
        assertTrue("unit at (2,0) should be able to move to (2,1)",game.moveUnit(new Position(2,0), new Position(2,1))); //RED is in turn, and moves towards blue legion
        game.endOfTurn(); //Turn ends, and BLUE is in turn
        assertTrue("unit at (3,2) should be able to move to (3,1)", game.moveUnit(new Position(3, 2), new Position(3, 1))); //BLUE moves towards RED archer
        game.endOfTurn(); //Turn ends, and RED is in turn
        assertTrue("unit at (2,1) should be able to move to (3,1)", game.moveUnit(new Position(2, 1), new Position(3, 1))); //RED moves and attacks BLUE legion - BLUE legion is destroyed
        game.endOfTurn(); //Turn ends, and BLUE is in turn
        assertFalse("BLUE should not be able to move unit at (3,1) to (3,2) - it is destroyed", game.moveUnit(new Position(3, 1), new Position(3, 2))); //BLUE tries to move an unit from (3,1), but BLUE has no units on (3,1), since it was destroyed by RED
        assertEquals("Owner of unit at (3,1) Should be RED", game.getUnitAt(new Position(3, 1)).getOwner(), Player.RED); //Only RED archer remains at (3,1)
    }

    @Test
    public void blueUnitAttacksAndDestroysRedUnit(){
        assertTrue("unit at (2,0) should be able to move to (2,1)",game.moveUnit(new Position(2,0), new Position(2,1))); //RED is in turn, and moves towards blue legion
        game.endOfTurn(); //Turn ends, and BLUE is in turn
        assertTrue("unit at (3,2) should be able to move to (3,1)",game.moveUnit(new Position(3,2), new Position(3,1))); //BLUE moves towards RED archer
        game.endOfTurn(); //Turn ends, and RED is in turn
        game.endOfTurn(); //RED makes an unbelievably stupid move, and ends his turn - BLUE is now in turn, and is in key position to destroy RED archer
        assertTrue("unit at (3,1) should be able to move to (2,1)",game.moveUnit(new Position(3,1), new Position(2,1))); //BLUE moves and attacks RED archer - RED archer is destroyed
        game.endOfTurn(); //Turn ends, and BLUE is in turn
        assertFalse("BLUE should not be able to move unit at (3,1) to (3,2) - it is destroyed",game.moveUnit(new Position(2,1), new Position(3,1))); //RED tries to move an unit from (2,1), but RED has no units on (2,1), since it was destroyed by BLUE
        assertEquals("Owner of unit at (3,1) Should be RED",game.getUnitAt(new Position(2,1)).getOwner(), Player.BLUE); //Only RED archer remains at (3,1)
    }

    @Test
    public void redShouldNotBeAbleToAttackOwnUnit(){
        assertTrue("unit at (2,0) should be able to move to (2,1)",game.moveUnit(new Position(2,0), new Position(2,1))); //RED is in turn, and moves towards blue legion
        assertTrue("unit at (4,3) should be able to move to (4,2)",game.moveUnit(new Position(4,3), new Position(4,2)));
        game.endOfTurn(); //Turn ends, and BLUE is in turn
        assertTrue("unit at (3,2) should be able to move to (3,3)", game.moveUnit(new Position(3, 2), new Position(3, 3))); //BLUE moves towards RED archer
        game.endOfTurn(); //Turn ends, and RED is in turn
        assertTrue("unit at (4,2) should be able to move to (3,2)", game.moveUnit(new Position(4, 2), new Position(3, 2)));
        assertTrue("unit at (2,1) should be able to move to (3,1)",game.moveUnit(new Position(2,1), new Position(3,1))); //RED is in turn, and moves towards blue legion
        game.endOfTurn();
        game.endOfTurn();
        assertFalse("Red archer cannot attack own settler", game.moveUnit(new Position(3, 1), new Position(3, 2)));
    }
    @Test
    public void chooseCityProduction(){
    game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION); // sets production to legion
    String prod = game.getCityAt(new Position(1,1)).getProduction();
    assertNotNull("City production should not be null",prod);
    assertEquals("City production should be a legion",GameConstants.LEGION,prod);
    }
    @Test
    public void after5TurnsGameTineShouldBe3500BC() {

        for(int x=0;x<10;x++)
        {
            game.endOfTurn();
        }
        			// start time is -4000 + 300 = - 350
        assertEquals("After 5 turns game time should be 3500BC",
                -3500, game.getAge());
    }
    @Test
    public void unitCanMove1tilePerTurn(){
        assertTrue("unit at (2,0) should be able to move to (2,1)",game.moveUnit(new Position(2,0), new Position(2,1))); //RED is in turn, and moves towards blue legion
        assertFalse("unit at (2,1) should not be able to move to (3,1)", game.moveUnit(new Position(2, 1), new Position(3, 1))); //RED is still in turn, and moves towards blue legion
    }
    @Test
    public void unitCanMove1tileAfterTurn(){
        Unit u = new UnitImpl(GameConstants.ARCHER, Player.RED);
        assertTrue("unit at (2,0) should be able to move to (2,1)",game.moveUnit(new Position(2,0), new Position(2,1)));
        game.endOfTurn(); //Blue's turn
        game.endOfTurn(); //Red's turn
        assertEquals("Units can only move 1 tile",1,u.getMoveCount());
    }
    @Test
    public void cityCanProduceAnArcherAfter2Turns(){
        game.changeProductionInCityAt(new Position(1,1), GameConstants.ARCHER); // sets production to archer
        String prod = game.getCityAt(new Position(1,1)).getProduction();
        assertNotNull("City production should not be null",prod);
        assertEquals("City production should be a archer",GameConstants.ARCHER,prod);
        game.endOfTurn();//blue's turn
        game.endOfTurn();//red's turn
        assertNull("Archer should not be ready",game.getUnitAt(new Position(1,1)));
        game.endOfTurn();//blue's turn
        game.endOfTurn();//red's turn
        Unit unit = game.getUnitAt(new Position(1,1));
        assertNotNull("Unit should be ready",unit);
        assertEquals("Unit should an Archer",unit.getTypeString(),GameConstants.ARCHER);
        assertEquals("Archer should be own by RED",unit.getOwner(),Player.RED);
    }
    @Test
    public void cityCanProduceALegionAfter3Turns(){
        Position cityPosition = new Position(4,1);
        game.endOfTurn();
        game.changeProductionInCityAt(cityPosition, GameConstants.LEGION); // sets production to archer
        String prod = game.getCityAt(cityPosition).getProduction();
        assertNotNull("City production should not be null",prod);
        assertEquals("City production should be a archer",GameConstants.LEGION,prod);
        game.endOfTurn();//blue's turn
        game.endOfTurn();//red's turn
        game.endOfTurn();//blue's turn
        game.endOfTurn();//red's turn
        assertNull("Archer should not be ready",game.getUnitAt(cityPosition));
        game.endOfTurn();//blue's turn
        game.endOfTurn();//red's turn
        Unit unit = game.getUnitAt(cityPosition);
        assertNotNull("Unit should be ready",unit);
        assertEquals("Unit should be a " + GameConstants.LEGION,unit.getTypeString(),GameConstants.LEGION);
        assertEquals("Archer should be own by RED",unit.getOwner(),Player.BLUE);
    }
}