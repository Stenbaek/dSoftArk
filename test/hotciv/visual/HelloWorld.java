package hotciv.visual;

import hotciv.factories.AlphaFactory;
import hotciv.standard.GameImpl;
import hotciv.standard.classes.StubObserverImpl;
import hotciv.stub.UpdateTool;
import hotciv.view.CivDrawing;
import hotciv.view.MapView;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.DrawingView;
import minidraw.framework.Factory;
import minidraw.standard.MiniDrawApplication;

import javax.swing.*;

public class HelloWorld {

    /**
     * @param args
     */
    public static void main(String[] args) {
        GameImpl game = new GameImpl(new AlphaFactory());
        game.addObserver(new StubObserverImpl());

        DrawingEditor editor = new MiniDrawApplication(
                "Hello world!!",
                new HelloWorldFactory(game));

        editor.open();

        editor.setTool( new UpdateTool(editor, game) );

        editor.showStatus("Hello World!!!");
    }

}

class HelloWorldFactory implements Factory {

    private GameImpl game;

    public HelloWorldFactory(GameImpl g) { game = g; }

    @Override
    public Drawing createDrawing(DrawingEditor editor) {
        return new CivDrawing( editor, game );
    }

    @Override
    public DrawingView createDrawingView(DrawingEditor editor) {
        DrawingView view = new MapView(editor, game);
        return view;
    }

    @Override
    public JTextField createStatusField(DrawingEditor editor) {
        JTextField f = new JTextField("Hello World!!!");
        f.setEditable(false);
        return f;
    }

}
