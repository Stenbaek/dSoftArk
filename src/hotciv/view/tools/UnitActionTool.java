package hotciv.view.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class UnitActionTool extends NullTool
{
    private DrawingEditor editor;
    private Game game;


    public UnitActionTool(DrawingEditor editor, Game g)
    {
        this.editor = editor;
        this.game = g;

    }

    public void mouseDown(MouseEvent e, int x, int y)
    {
        if ( e.isShiftDown() ) {
            Position position = GfxConstants.getPositionFromXY(x, y);

            // Testing for units
            Unit unit = game.getUnitAt(position);
            if ( unit != null ) {
                if (game.getPlayerInTurn() == unit.getOwner()) {
                    game.performUnitActionAt(position);
                    if (unit.getTypeString().equals(GameConstants.ARCHER)) {
                        editor.showStatus("The Archer has been fortified");
                    } else if (unit.getTypeString().equals(GameConstants.SETTLER)) {
                        editor.showStatus("Congratz with your new city");
                    }
                } else {
                    editor.showStatus("That is an enemy unit...stoopid");
                    return;
                }
            }
        }
    }
}