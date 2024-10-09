import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;


public class Hero {
    private Position position;
    public Hero(Position position) {
        this.position = position;
    }
    public Position getPosition() {
        return position;
    }
    public void draw(TextGraphics graphics) {
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1 );
    }
    public Position moveLeft() {
        return new Position(position.getX() -1 , position.getY());
    }
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }
}

