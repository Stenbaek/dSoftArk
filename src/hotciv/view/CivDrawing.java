package hotciv.view;

import hotciv.framework.*;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.ImageFigure;
import minidraw.standard.StandardDrawing;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/** CivDrawing is a specialized Drawing (model component) from
 * MiniDraw that dynamically builds the list of Figures for MiniDraw
 * to render the Unit and other information objects that are visible
 * in the Game instance.
 *
 * This is a TEMPLATE for the dSoftArk Exercise! This means
 * that it is INCOMPLETE and that there are several options
 * for CLEANING UP THE CODE when you add features to it!

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

public class CivDrawing extends StandardDrawing
        implements Drawing, GameObserver {

    /** the Game instance that this UnitDrawing is going to render units
     * from */
    protected Game game;
    protected DrawingEditor editor;

    /** store all moveable figures visible in this drawing = units */
    protected Map<Unit,UnitFigure> unitFigureMap = null;
    protected Map<City, CityFigure> cityFigureMap = null;

    private ImageFigure turnShieldIcon;
    private ImageFigure unitShieldIcon;

    private TextFigure gameAgeText;
    private TextFigure unitMovesLeft;

    public CivDrawing( DrawingEditor editor, Game game ) {
        super();
        this.game = game;
        this.editor = editor;

        // register this unit drawing as listener to any game state
        // changes...
        game.addObserver(this);

        // Instantiating figure hashmaps
        unitFigureMap = new HashMap<Unit, UnitFigure>();
        cityFigureMap = new HashMap<City, CityFigure>();

        // ... and build up the set of figures associated with
        // units and cities in the game.
        defineWorld();

        // and the set of 'icons' in the status panel
        defineIcons();

        // Defining game UI text
        defineText();
        changeText(); // initialisation of text

    }

    /** The UnitDrawing should not allow client side
     * units to add and manipulate figures; only figures
     * that renders game objects are relevant, and these
     * should be handled by observer events from the game
     * instance. Thus this method is 'killed'.
     */
    public Figure add(Figure arg0) {
        throw new RuntimeException("Should not be used...");
    }

    /**
     * Defines the world. i.e. places units, cities and tile icons on
     * the gui map
     */
    private void defineWorld() {
        // ensure no units of the old list are accidental in
        // the selection!
        clearSelection();

        Position p;
        for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
            for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
                p = new Position(r,c);
                addCityFigure(p);
                addUnitFigure(p);
            }
        }

    }

    /**
     * Asks the game instance for a unit at Position p,
     * places a unit on the map if found
     * @param p Position
     */
    private void addUnitFigure(Position p) {
        Unit unit = game.getUnitAt(p);
        if ( unit != null ) {
            String type = unit.getTypeString();
            // convert the unit's Position to (x,y) coordinates
            Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                    GfxConstants.getYFromRow(p.getRow()) );
            UnitFigure unitFigure =
                    new UnitFigure( type, point, unit );
            unitFigure.addFigureChangeListener(this);
            unitFigureMap.put(unit, unitFigure);

            // also insert in superclass list as it is
            // this list that is iterated by the
            // graphics rendering algorithms
            super.add(unitFigure);
        }
    }

    /**
     * Asks the game instance for a city at Position p,
     * places a cityFigure on the map if found
     * @param p Position
     */
    private void addCityFigure(Position p) {
        City city = game.getCityAt(p);

        if ( city != null ) {
            Point point = new Point( GfxConstants.getXFromColumn(p.getColumn()),
                    GfxConstants.getYFromRow(p.getRow()) );

            CityFigure cf = new CityFigure(city, point);
            cf.addFigureChangeListener(this);
            cityFigureMap.put(city, cf);

            super.add(cf);
        }
    }

    private void defineIcons() {
        // very much a template implementation :)
        turnShieldIcon =
                new ImageFigure( "redshield",
                        new Point( GfxConstants.TURN_SHIELD_X,
                                GfxConstants.TURN_SHIELD_Y ) );
        // insert in superclass figure list to ensure graphical
        // rendering.
        super.add(turnShieldIcon);

        unitShieldIcon =
                new ImageFigure( "redshield",
                        new Point( GfxConstants.UNIT_SHIELD_X,
                                GfxConstants.UNIT_SHIELD_Y));
        super.add(unitShieldIcon);
    }

    private void defineText() {
        gameAgeText = new TextFigure("4000 BC",
                new Point(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y));
        super.add(gameAgeText);
        unitMovesLeft = new TextFigure( "",
                new Point( GfxConstants.UNIT_COUNT_X,
                        GfxConstants.UNIT_COUNT_Y));
        super.add(unitMovesLeft);
    }

    private void changeText() {
        int gameAge = game.getAge();
        String gameAgeStr;
        if (gameAge <= 0) {
            gameAgeStr = Math.abs(gameAge) + " BC";
        } else {
            gameAgeStr = gameAge + " AD";
        }
        gameAgeText.setText(gameAgeStr);
    }

    // === Observer Methods ===

    public void worldChangedAt(Position pos) {
        System.out.println( "UnitDrawing: world changes at "+pos);
        clearSelection();
        // this is a really brute-force algorithm: destroy
        // all known units and build up the entire set again
        for ( Figure u : unitFigureMap.values() ) {
            super.remove(u);
        }
        for ( Figure c : cityFigureMap.values() ) {
            super.remove(c);
        }
        defineWorld();
    }

    public void turnEnds(Player nextPlayer, int age) {
        System.out.println( "UnitDrawing: turnEnds for "+
                nextPlayer+" at "+age );
        String playername = "red";
        if ( nextPlayer == Player.BLUE ) { playername = "blue"; }
        turnShieldIcon.set( playername+"shield",
                new Point( GfxConstants.TURN_SHIELD_X,
                        GfxConstants.TURN_SHIELD_Y ) );
        changeText();
    }

    public void tileFocusChangedAt(Position position) {
        if (position.getRow() > GameConstants.WORLDSIZE ||
                position.getColumn() > GameConstants.WORLDSIZE) {
            return;
        }

        Unit posUnit = game.getUnitAt(position);
        Point pointOfInterrest =
                new Point(GfxConstants.UNIT_SHIELD_X, GfxConstants.UNIT_SHIELD_Y);
        if (posUnit != null) {
            // sets the unit movement text with the moveCount of the selected unit
            unitMovesLeft.setText(""+posUnit.getMoveCount());

            // sets the UNIT_SHIELD color based on the owner of selected unit
            String playerInTurn = "" + posUnit.getOwner();
            String player = playerInTurn.toLowerCase() + "shield";
            unitShieldIcon.set(player, pointOfInterrest);
        }

        City posCity = game.getCityAt(position);
        if (posCity != null ) {

        }
    }
}
