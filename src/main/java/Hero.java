import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Hero {
    private int x;
    private int y;
    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void moveUp() {
        this.y -= 1;
    }
    public void moveDown() {
        this.y += 1;
    }
    public void moveLeft() {
        this.x -= 1;
    }
    public void moveRight() {
        this.x += 1;
    }
    public void draw(Screen screen) {
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
    }
}


