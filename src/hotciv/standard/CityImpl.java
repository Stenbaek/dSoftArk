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

    public CityImpl(Player p){
             this.owner = p;
    }

    @Override
    public Player getOwner() {
        return this.owner;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getSize() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getProduction() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getWorkforceFocus() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
