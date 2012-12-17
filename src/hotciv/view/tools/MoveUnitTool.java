package hotciv.view.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class MoveUnitTool extends NullTool {

    private Game game;
    private DrawingEditor editor;
    private Drawing drawing;
    private Figure theFigureInMove = null;
    private int currFigX, currFigY;
    private int initFigX, initFigY;

    public MoveUnitTool(DrawingEditor editor, Game game) {
        this.game = game;
        this.editor = editor;
        drawing = this.editor.drawing();
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        theFigureInMove = drawing.findFigure(x,y);
        if ( isAFigure(theFigureInMove) ) {
            // setting the start x and y locations
            initFigX = currFigX = x; initFigY = currFigY = y;
            drawing.addToSelection(theFigureInMove);
        }
    }

    public void mouseDrag(MouseEvent e, int x, int y) {
        if ( isAFigure(theFigureInMove) ) {
			/*
			 * We want the difference in x,y movement so we subtract the 
			 * initial x,y from the new x,y.
			 * The result is relative move
			 */
            theFigureInMove.moveBy((x-currFigX), (y-currFigY));
            // setting the updated figure location
            currFigX = x; currFigY = y;
        }
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        if ( isAFigure(theFigureInMove) ) {
            Position from = GfxConstants.getPositionFromXY(initFigX, initFigY);
            Position to = GfxConstants.getPositionFromXY(x, y);
            if (to.getRow() > GameConstants.WORLDSIZE
                    || to.getRow() < 0 || to.getColumn() < 0
                    || to.getColumn() > GameConstants.WORLDSIZE) {
                editor.showStatus("Illigal move");
                resetLocation(initFigX, x, initFigY, y);
                return;
            }
            boolean moveLegality = game.moveUnit(from, to);
            if (!moveLegality) {
                // move wasen't legal, so resetting the unit to its initial pos
                resetLocation(initFigX, x, initFigY, y);
            }

            drawing.removeFromSelection(theFigureInMove);
            theFigureInMove = null;
        }
    }

    private void resetLocation(int initialX, int x, int initialY, int y) {
        theFigureInMove.moveBy(initialX-x, initialY-y);
    }

    private boolean isAFigure(Figure f) {
        if (f != null) {
            return f.getClass().getSimpleName().equals("UnitFigure") ? true : false;
        }
        else return false;
    }
}
