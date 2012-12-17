package hotciv.visual;

import hotciv.teststubs.FixedTestStubCivDieRoll;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.factories.*;
import hotciv.view.tools.UnitActionTool;

public class TestUnitActionTool {

    public static void main(String[] args){
        Game game = new GameImpl(new SemiFactory(new FixedTestStubCivDieRoll()));
        DrawingEditor editor = new MiniDrawApplication("Shift click on a settler to create a city",
                new HotCivFactory4(game));
        game.moveUnit(new Position(5,5), new Position(6,5));

        editor.open();
        editor.setTool(new UnitActionTool(editor, game));
        editor.showStatus("Shift click on a settler to create a city");


    }

}