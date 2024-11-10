import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MonsterTest {
    public Monster monster;
    public GenericGraphics graphics;

    @BeforeEach
    public void setUp() {
        Position position = new Position(8, 5);
        graphics = mock(GenericGraphics.class);
        monster = new Monster(position);
    }

    @Test
    public void testMove() {
        Position originalPosition  = monster.getPosition();
        monster.move(10,10);
        Position newPosition = monster.getPosition();
        assertTrue(newPosition.getX() >= originalPosition.getX() - 1 && newPosition.getX() <= originalPosition.getX() + 1);
        assertTrue(newPosition.getY() >= originalPosition.getY() - 1 && newPosition.getY() <= originalPosition.getY() + 1);
    }

    @Test
    public void testDraw() {
        monster.draw(graphics);
        graphics.setForegroundColor(TextColor.ANSI.RED);
        graphics.putString(new TerminalPosition(monster.getPosition().getX(), monster.getPosition().getY()), "M");
    }
}
