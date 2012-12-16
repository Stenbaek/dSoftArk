package hotciv.stub;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class UpdateTool extends NullTool {

    private GameImpl game;
    private DrawingEditor editor;

    private int count = 0;

    public UpdateTool(DrawingEditor editor, GameImpl game) {
        this.game = game;
        this.editor = editor;
    }

    public void setGame(GameImpl game) {
        this.game = game;
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        switch (count) {
            case 0:
                editor.showStatus("Move unit from 2,0 to 2,1 counter=" + count);
                game.moveUnit(new Position(2,0), new Position(2,1));
                break;
            case 1:
                editor.showStatus("End of reds turn counter=" + count);
                game.endOfTurn();
                break;
            case 2:
                editor.showStatus("end of blues turn counter=" + count + " End of round, age should now increment");
                game.endOfTurn();
                break;
            case 3:
                editor.showStatus("Move unit from 2,1 to 3,1 counter=" + count);
                game.moveUnit(new Position(2,1), new Position(3,1));
                break;
            case 4:
                editor.showStatus("Advancing time to blue and back to red");
                game.endOfTurn(); game.endOfTurn();
                break;
            case 5:
                editor.showStatus("Attacking unit at 3,2 counter=" + count);
                game.moveUnit(new Position(3,1), new Position(3,2));
                break;
            default:
                editor.showStatus("Nothing more to do!!");
        }
        count++;
    }

}
