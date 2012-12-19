package hotciv.visual;

import minidraw.standard.*;
import minidraw.framework.*;
import hotciv.framework.*;
import hotciv.view.tools.*;
import hotciv.stub.*;

public class TestEndOfTurnTool {

    public static void main(String[] args) {

        Game game = new StubGame2();

        DrawingEditor editor =
                new MiniDrawApplication( "Click Turn shield to see changes",
                        new HotCivFactory4(game) );
        editor.open();
        editor.setTool( new EndOfTurnTool(editor,game) );
        editor.showStatus("Click TURN SHIELD to 'end the turn'");
    }
}