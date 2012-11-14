package hotciv.standard;

import hotciv.framework.City;
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
    private String unitInProduction;

    public CityImpl(Player p){
             this.owner = p;
             cityTreasury =0;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String getProduction() {
        return unitInProduction;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

    public int getProductionTreasury(){
        return cityTreasury;
    }

    public void addProductionTreasury(int amount){
        cityTreasury += amount;
    }

    public void setProduction(String unitType){
        unitInProduction = unitType;
    }

}
