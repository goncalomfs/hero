import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;

public class Monster extends Element {
    public Monster(Position position) {
        super(position);
    }
    public void move(int width, int height) {
        Random random = new Random();
        int addX = random.nextInt(3) - 1;
        int addY = random.nextInt(3) - 1;
        int newX = this.position.getX() + addX;
        int newY = this.position.getY() + addY;
        if (newX > 0 && newX < width - 1 && newY > 0 && newY < height - 1) {
            setPosition(new Position(newX, newY));
        }
    }

    @Override
    public void draw(GenericGraphics graphics) {
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

}
