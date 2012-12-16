package hotciv.view.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class TileFocusTool extends NullTool
{
    private Game game;
    private Position position;


    public TileFocusTool(DrawingEditor editor, Game game)
    {
        this.game = game;
    }

    public void mouseUp(MouseEvent e, int x, int y)
    {
        position = GfxConstants.getPositionFromXY(x, y);

        //Skips if trying to move outside the world
        if(position.getRow() < GameConstants.WORLDSIZE
                && position.getRow() < GameConstants.WORLDSIZE)
        {
            game.setTileFocus(position);
        }
    }
}