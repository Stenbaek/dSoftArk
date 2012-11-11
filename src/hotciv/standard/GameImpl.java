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
  private CityImpl[][] cities; // snak med simon ang.matrix of cities in game
  private Player playerInTurn = Player.RED;
  public GameImpl(){
      this.age = -4000; // initial start age
      this.createWorld();
  }
  private void createWorld(){

      units = new UnitImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];

      // Creating initial units, map and cities
      units[2][0] = new UnitImpl(GameConstants.ARCHER, Player.RED);
      units[3][2] = new UnitImpl(GameConstants.LEGION, Player.BLUE);
      units[4][3] = new UnitImpl(GameConstants.SETTLER, Player.RED);



      cities = new CityImpl[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
      cities[1][1] = new CityImpl(Player.RED);
      cities[4][1] = new CityImpl(Player.BLUE);

  }

  /*public Tile getTileAt( Position p ) {
    if(p.getRow() == 1 && p.getColumn() == 0){
        return new Ocean(p);
    }else{
        return null;
    } /*
  }
  /* alternativ til getTile...snak med Simon kræver TileImpl istedet for Ocean*/
   public Tile getTileAt( Position p ) {
		if (p.equals(new Position(1,0))){ // 1,1 is ocean
			return new Ocean(p);
		} else if(p.equals(new Position(0,1))){ // 0,1 is hills
			return new Hill(p);
		} else if(p.equals(new Position(2,2))){ // is mountains
			return new Mountain(p);
		}

		// All other tiles are of type plains
		return new Plain(p);
	}


  public Unit getUnitAt( Position p ) {
      return units[p.getRow()][p.getColumn()]; }

  public City getCityAt( Position p ) {
    if(p.equals(new Position(1,1))){
        return new CityImpl(Player.RED);
    }else if(p.equals(new Position(4,1))){
        return new CityImpl(Player.BLUE);
    }else{
        return null;
    }
  }
  public Player getPlayerInTurn() {
      return playerInTurn;
      /*
      if((age/100)%2 == 0){
        return Player.RED;
    }else{
        return Player.BLUE;
    }  */
  }
  public Player getWinner() { return Player.RED; }
  public int getAge() { return age; }
  public boolean moveUnit( Position from, Position to ) {

    return false;
  }
  public void endOfTurn() {
      if(playerInTurn == Player.BLUE) { // A round ends after blue players turn as he/she is the last in round
          age += 100; // advances time by 100 years
          for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
              for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                  if (cities[r][c] != null) {
                      // Adding 6 production to each cities treasury each round
                      cities[r][c].addProductionTreasury(6);

                      if (cities[r][c].getProduction() != null) {

                          // If the city can afford what it is producing, the unit is placed on the map
                          if(cities[r][c].getProductionTreasury()
                                  >= getUnitCost(cities[r][c].getProduction())) {

                              // places the unit on map
                              units[r][c] = new UnitImpl(cities[r][c].getProduction(), cities[r][c].getOwner());
                          }
                      }
                  }

                  // only selects units with 0 move points
                  if (units[r][c] != null && units[r][c].getMoveCount() == 0) {
                      units[r][c].changeMoveCounter(1); // sets the unit moveCount to 1 if it is zero (current invariant)
                  }
              }
          }
      }

      // swaps the players each turn
      if (Player.RED == playerInTurn) {
          playerInTurn = Player.BLUE;
      } else {
          playerInTurn = Player.RED;
      }

  }
  private int getUnitCost(String unitType){
      if(unitType.equals(GameConstants.ARCHER)) return 10;
      if(unitType.equals(GameConstants.LEGION)) return 15;
      if(unitType.equals(GameConstants.SETTLER)) return 30;
      return 0;
  }
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {
      ((CityImpl)getCityAt(p)).setProduction(unitType);
  }
  public void performUnitActionAt( Position p ) {}


}
