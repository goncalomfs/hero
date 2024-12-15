import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HeroTest {
    private Hero hero;
    private GenericGraphics graphics;

    @BeforeEach
    public void setUp() {
        Position position = new Position(5, 5);
        graphics = mock(GenericGraphics.class);
        hero = new Hero(position);

    }

    @Test
    public void testDraw() {
        hero.draw(graphics);
        verify(graphics).enableModifiers(SGR.BOLD);
        verify(graphics).setForegroundColor(TextColor.ANSI.GREEN_BRIGHT);
        verify(graphics).putString(new TerminalPosition(5, 5), "X");
    }

    @Test
    public void testMoveUp() {
        Position newPosition = hero.moveUp();
        assertEquals(new Position(5, 4), newPosition);
    }

    @Test
    public void testMoveDown() {
        Position newPosition = hero.moveDown();
        assertEquals(new Position(5, 6), newPosition);
    }

    @Test
    public void testMoveLeft() {
        Position newPosition = hero.moveLeft();
        assertEquals(new Position(4, 5), newPosition);
    }

    @Test
    public void testMoveRight() {
        Position newPosition = hero.moveRight();
        assertEquals(new Position(6, 5), newPosition);
    }
}
