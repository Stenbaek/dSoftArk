package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Thomas
 * Date: 07-11-12
 * Time: 12:43
 * To change this template use File | Settings | File Templates.
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
        return this.owner;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getSize() {
        return 1;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getProduction() {
        return unitInProduction;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getWorkforceFocus() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
