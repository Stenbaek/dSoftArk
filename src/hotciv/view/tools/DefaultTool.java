package hotciv.view.tools;

import minidraw.standard.NullTool;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultTool extends NullTool {

    // Tools
    private List<NullTool> tools =
            new ArrayList<NullTool>();

    public DefaultTool() {
    }

    public void mouseDown(MouseEvent e, int x, int y) {
        Iterator<NullTool> iter = tools.iterator();
        while(iter.hasNext()) {
            iter.next().mouseDown(e, x, y);
        }
    }

    public void mouseUp(MouseEvent e, int x, int y) {
        Iterator<NullTool> iter = tools.iterator();
        while(iter.hasNext()) {
            iter.next().mouseUp(e, x, y);
        }
    }

    public void mouseMove(MouseEvent e, int x, int y) {
        Iterator<NullTool> iter = tools.iterator();
        while(iter.hasNext()) {
            iter.next().mouseMove(e, x, y);
        }
    }

    public void mouseDrag(MouseEvent e, int x, int y) {
        Iterator<NullTool> iter = tools.iterator();
        while(iter.hasNext()) {
            iter.next().mouseDrag(e, x, y);
        }
    }

    public void keyDown(KeyEvent e, int key) {
        Iterator<NullTool> iter = tools.iterator();
        while(iter.hasNext()) {
            iter.next().keyDown(e, key);
        }
    }

    public void addTool(NullTool tool) {
        tools.add(tool);
    }

    public void removeTool(NullTool tool) {
        tools.remove(tool);
    }
}
