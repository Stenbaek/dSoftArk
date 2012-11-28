package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 07-11-12
 * Time: 12:43
 */
public class CityImpl implements City{

    public Player owner;
    private int cityTreasury;

    private int cityPopulation;
    private int cityFood;

    private String cityWorkForceFocus = GameConstants.productionFocus;

    private String unitInProduction;

    public CityImpl(Player owner){
        this.owner = owner;
        cityTreasury = 0;
        cityPopulation = 1;
        cityFood = 0;
    }


    public Player getOwner() {
        return this.owner;
    }


    public int getSize() {
        return cityPopulation;
    }


    public String getProduction() {
        return unitInProduction;
    }


    public String getWorkforceFocus() {
        return cityWorkForceFocus;
    }

    public int getProductionTreasury(){
        return cityTreasury;
    }

    public int getCityFood(){
        return cityFood;
    }

    public void addProductionTreasury(int amount){
        cityTreasury = cityTreasury + amount;
    }

    public void addFood(int amount){
        cityFood = cityFood + amount;
    }

    public void resetFood(){
        cityFood = 0;
    }

    public void setProduction(String unitType){
        unitInProduction = unitType;
    }

    public void incrementPopulation(){
        cityPopulation++;
    }

    public void setWorkForceFocus(String focus){
        cityWorkForceFocus = (focus.equals(GameConstants.productionFocus) || focus.equals(GameConstants.foodFocus)) ? focus : this.cityWorkForceFocus;
    }

    public void setOwner(Player p) {
        owner = p;
    }
}
