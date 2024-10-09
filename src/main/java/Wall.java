import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class Wall {
    private int x;
    private int y;
    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void draw(TextGraphics graphics) {
        graphics.putString(new TerminalPosition(x, y), "|");
    }
}
