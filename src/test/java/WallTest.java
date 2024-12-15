import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class WallTest {
    private Wall wall;
    private GenericGraphics graphics;

    @BeforeEach
    public void setUp() {
        wall = new Wall(3, 7);
        graphics = mock(GenericGraphics.class);
    }

    @Test
    public void testDraw() {
        // Act
        wall.draw(graphics);

        // Assert
        verify(graphics).setForegroundColor(TextColor.ANSI.WHITE);
        verify(graphics).putString(new TerminalPosition(3, 7), "|");
    }
}


