package hotciv.standard;

import hotciv.factories.EtaFactory;
import hotciv.framework.*;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.City;
import hotciv.standard.units.Archer;
import hotciv.standard.units.Legion;
import hotciv.standard.units.Settler;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import org.junit.*;
import static org.junit.Assert.*;
import hotciv.standard.classes.ExternalMapTestFactory;


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
        CityImpl c = (CityImpl) game.getCityAt(new Position(8,12));
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
        CityImpl c = (CityImpl) game.getCityAt(new Position(4,5));
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

}
