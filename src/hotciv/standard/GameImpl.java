package hotciv.standard;

import hotciv.framework.*;

/** Skeleton implementation of HotCiv.
 
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

public class GameImpl implements Game {
  private int age;
  private UnitImpl[][] units; // matrix of units in game
  // private CityImpl[][] cities; // snak med simon ang.matrix of cities in game
  public GameImpl(){
      this.age = -4000; // initial start age
  }

  public Tile getTileAt( Position p ) {
    if(p.getRow() == 1 && p.getColumn() == 0){
        return new Ocean(p);
    }else{
        return null;
    }
  }
  /* alternativ til getTile...snak med Simon kræver TileImpl istedet for Ocean
   public Tile getTileAt( Position p ) {
		if (p.equals(new Position(1,0))){ // 1,1 is ocean
			return new TileImpl(GameConstants.OCEANS,1,0);
		} else if(p.equals(new Position(0,1))){ // 0,1 is hills
			return new TileImpl(GameConstants.HILLS,0,1);
		} else if(p.equals(new Position(2,2))){ // is mountains
			return new TileImpl(GameConstants.MOUNTAINS,2,2);
		}

		// All other tiles are of type plains
		return new TileImpl(GameConstants.PLAINS,p.getRow(),p.getColumn());
	}
     */

  public Unit getUnitAt( Position p ) {
      return units[p.getRow()][p.getColumn()]; }

  public City getCityAt( Position p ) {
    if(p.getRow() == 1 && p.getColumn() == 1){
        return new CityImpl(Player.RED);
    }else{
        return null;
    }
  }
  public Player getPlayerInTurn() {
    if((age/100)%2 == 0){
        return Player.RED;
    }else{
        return Player.BLUE;
    }
  }
  public Player getWinner() { return null; }
  public int getAge() { return age; }
  public boolean moveUnit( Position from, Position to ) {
    return false;
  }
  public void endOfTurn() {
      this.age = age+100;
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {}
}
