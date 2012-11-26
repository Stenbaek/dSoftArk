package hotciv.standard;

import hotciv.factories.EpsilonFactory;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;
import hotciv.teststubs.FixedTestStubCivDieRoll;
import org.junit.Before;
import org.junit.Test;
import hotciv.standard.classes.*;

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
public class TestEpsilonCiv {
    private Game game;

    @Before
    public void setUp() {
        game = new GameImpl(new EpsilonFactory(new FixedTestStubCivDieRoll()));
    }

    @Test
    public void redShouldWinAfter3SuccessfulAttacks(){
        for(int i = 0; i < 3; i++){
            game.addUnit(new Position(5,6),new Legion(Player.RED));
            game.addUnit(new Position(6,6),new Settler(Player.BLUE));

            game.moveUnit(new Position(5,6),new Position(6,6));
            game.removeUnit(new Position(6,6));
        }
        assertTrue("RED should be the winner after 3 successful attacks",(game.getWinner().equals(Player.RED)));
    }

    @Test
    public void blueShouldWinAfter3SuccessfulAttacks(){
        game.endOfTurn();
        for(int i = 0; i < 3; i++){
            game.addUnit(new Position(5,6),new Legion(Player.BLUE));
            game.addUnit(new Position(6,6),new Settler(Player.RED));

            game.moveUnit(new Position(5,6),new Position(6,6));
            game.removeUnit(new Position(6,6));
        }
        assertTrue("RED should be the winner after 3 successful attacks",(game.getWinner().equals(Player.BLUE)));
    }

    //Attacker gets deleted from board/world after attacker loses
    @Test
    public void attackerGetsDeletedFromWorldAfterLoss(){
        game.addUnit(new Position(5,6),new Legion(Player.BLUE));
        game.addUnit(new Position(6,6),new Settler(Player.RED));

        assertEquals("There should be a Settler at (6,6)", game.getUnitAt(new Position(6,6)).getClass(),Settler.class);
        assertEquals("There should be a Legion at (5,6)", game.getUnitAt(new Position(5,6)).getClass(),Legion.class);
        assertFalse("Settler should not be able to move to (5,6)",game.moveUnit(new Position(6,6),new Position(5,6)));

        assertEquals("Legion should be the only unit left after battle, at (5,6)",game.getUnitAt(new Position(5,6)).getClass(),Legion.class);
        assertNull("Settler should be deleted at (6,6)",game.getUnitAt(new Position(6,6)));
    }
    /**
     * A unit should attack another but be defeated because it's attack strength
     * is less than the defense strength of the defender.
     * The terrain- or adjacent friend factor should not come into play.
     *
     * The effect of using a fixed dice is that all randomness
     * of using a "dice" is removed and we can test the algorithm, not the random nature
     * of a dice.
     *
     * Archer attack strength 		= 2 * fixed dice(2) = 4
     * Settler defense strength 	= 3 * fixed dice(2) = 6
     *
     * TODO Hvordan kan man bevise at denne test rentfaktisk tester dice effekten, når dice
     * er fixed, og der yderligere giver den samme bonus til attacker og defender ?
     */
    @Test
    public void diceFactorShouldMultiplyStrengthByTwo() {
        game.addUnit(new Position(10,3), new Archer(Player.RED));
        game.addUnit(new Position(10,2), new Settler(Player.BLUE));
        game.moveUnit(new Position(10,3),new Position(10, 2));
        assertEquals("The unit at 10,2 should still be a settler", GameConstants.SETTLER,
                game.getUnitAt(new Position(10,2)).getTypeString());
    }
    /**
     * Legion at (11,2) and friend at (11,1) attacks red archer at (10,2) with friends
     * at (10,3) (10,1) and (9,2).
     *
     * The legion should have a total attack strength of:
     * 4 + 1 = 5
     *
     * The archer should have a defense of:
     * 3 + 1 + 1 + 1 = 6
     */
    @Test
    public void attackStrengthOfRedArcherWithFriendOnAdjacentTile() {
        game.addUnit(new Position(10, 3), new Archer(Player.RED));
        game.addUnit(new Position(10, 2), new Archer(Player.RED));
        game.addUnit(new Position(10, 1), new Archer(Player.RED));
        game.addUnit(new Position(9, 2), new Archer(Player.RED));

        game.addUnit(new Position(11, 2), new Legion(Player.BLUE));
        game.addUnit(new Position(11, 1), new Legion(Player.BLUE));

        game.endOfTurn(); // new blue player is in turn
        boolean move = game.moveUnit(new Position(11,2), new Position(10,2));
        assertFalse("The move should return false, since the attacker dies", move);
        assertEquals("There should still be a archer at 10,2", Archer.class,
                game.getUnitAt(new Position(10,2)).getClass());
    }
    @Test
    public void redSettlerAt4x3ShouldNotKillBlueLegionAt3x2WhenSettlerIsAttacking() {
        game.moveUnit(new Position(4,3), new Position(3,2));
        assertEquals("There should still be a legion at (3,2)", GameConstants.LEGION,
                game.getUnitAt(new Position(3,2)).getTypeString());
    }

    @Test
    public void blueLegionAt3x2ShouldKillRedSettlerAt4x3WhenLegionIsAttacking() {
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(4,3));
        assertEquals("After attack, there should be a legion at (4,3)",GameConstants.LEGION,
                game.getUnitAt(new Position(4,3)).getTypeString());
    }
    @Test
    public void blueAttackingArcherShouldDieToLegionInCityAt8x12() {
        // Changing the map strategy to ExternalMapStrategy as it has trees
        game = new GameImpl(new ExternalMapTestFactory(new FixedTestStubCivDieRoll()));
        game.addUnit(new Position(8,12), new Legion(Player.RED));
        game.addUnit(new Position(8,11), new Archer(Player.BLUE));
        game.endOfTurn(); // skipping to blue
        game.moveUnit(new Position(8, 11), new Position(8, 12));
                assertEquals("The unit at the city should still be the legion", GameConstants.LEGION,
                game.getUnitAt(new Position(8, 12)).getTypeString());
    }

}
