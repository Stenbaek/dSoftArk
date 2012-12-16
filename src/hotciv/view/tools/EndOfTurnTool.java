package hotciv.view.tools;

import hotciv.framework.Game;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;


/** End the turn tool, that ends the turn in a game. */
public class EndOfTurnTool extends NullTool {
    private Game game;
    private DrawingEditor editor;
    private int shieldSizeX = 27, shieldsizeY = 39;

    public EndOfTurnTool(DrawingEditor editor, Game game) {
        this.editor = editor;
        this.game = game;
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        // if you click inside the shield, the turn ends and time is updated
        if ( x >= GfxConstants.TURN_SHIELD_X && x <= GfxConstants.TURN_SHIELD_X + shieldSizeX
                && y > GfxConstants.TURN_SHIELD_Y
                && y <= GfxConstants.TURN_SHIELD_Y + shieldsizeY) {
            game.endOfTurn();
            editor.drawing().requestUpdate();
        }
    }
}
