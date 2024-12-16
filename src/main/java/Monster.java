import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;

public class Monster extends Element {
    private static Random random = new Random();

    public static void setRandom(Random newRandom) {
        random = newRandom;
    }

    public Monster(Position position) {
        super(position);
    }

    public Position nextPosition() {
        Random rand = new Random();
        int n = rand.nextInt(4);
        Position pos= null;
        switch (n) {
            case 0:
                pos = new Position(position.getX() + 1, position.getY());
                break;
            case 1:
                pos = new Position(position.getX(), position.getY() - 1);
                break;
            case 2:
                pos = new Position(position.getX(), position.getY() + 1);
                break;
            case 3:
                pos = new Position(position.getX() - 1, position.getY());
                break;
            default:
                break;
        }
        return pos;
    }

    @Override
    public void draw(GenericGraphics graphics) {
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

}
