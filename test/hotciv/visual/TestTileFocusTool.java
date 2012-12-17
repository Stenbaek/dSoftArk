package hotciv.visual;

import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import hotciv.factories.*;
import hotciv.framework.*;
import hotciv.standard.GameImpl;
import hotciv.view.tools.TileFocusTool;



public class TestTileFocusTool
{
    public static void main(String args[])
    {
        Game game = new GameImpl(new AlphaFactory());
        DrawingEditor editor = new MiniDrawApplication( "Click on cities and units", new HotCivFactory4(game) );
        editor.open();
        editor.setTool( new TileFocusTool(editor, game));
    }
}