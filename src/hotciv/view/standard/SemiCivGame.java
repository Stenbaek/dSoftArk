package hotciv.view.standard;

import hotciv.factories.SemiFactory;
import hotciv.standard.GameImpl;
import hotciv.teststubs.FixedTestStubCivDieRoll;
import hotciv.view.CivDrawing;
import hotciv.view.MapView;
import hotciv.view.tools.*;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import javax.swing.*;

public class SemiCivGame {

    public static void main(String[] args) {

        GameImpl game = new GameImpl(new SemiFactory(new FixedTestStubCivDieRoll()));

        DrawingEditor editor = new MiniDrawApplication(
                "SemiCiv",
                new SemiCivViewFactory(game));

        editor.open();

        DefaultTool defTool = new DefaultTool();
        NullTool endOfTurnTool = new EndOfTurnTool(editor, game);
        NullTool unitActionTool = new UnitActionTool(editor, game);
        NullTool moveUnitTool = new MoveUnitTool(editor, game);
        NullTool tileFocusTool = new TileFocusTool(editor, game);

        defTool.addTool(endOfTurnTool);
        defTool.addTool(unitActionTool);
        defTool.addTool(moveUnitTool);
        defTool.addTool(tileFocusTool);

        editor.setTool(defTool);

        editor.showStatus("Welcome to SemiCiv");

    }

}

class SemiCivViewFactory implements Factory {

    private GameImpl game;

    public SemiCivViewFactory(GameImpl g) { game = g;}

    @Override
    public Drawing createDrawing(DrawingEditor editor) {
        return new CivDrawing( editor, game);
    }

    @Override
    public DrawingView createDrawingView(DrawingEditor editor) {
        DrawingView view =
                new MapView(editor, game);
        return view;
    }

    @Override
    public JTextField createStatusField(DrawingEditor areditorg0) {
        JTextField f = new JTextField("Testing default Tool!!");
        f.setEditable(false);
        return f;
    }

}