package hotciv.view.tools;

import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

public abstract class AbstractTool extends NullTool {

    private DrawingEditor editor;
    private NullTool defaultTool;

    public AbstractTool(DrawingEditor editor, NullTool defaultTool) {
        this.editor = editor;
        this.defaultTool = defaultTool;
    }

    public void resetTool() {
        editor.setTool(defaultTool);
    }
}
