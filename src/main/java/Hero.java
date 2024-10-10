import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element {

    public Hero(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }
}
