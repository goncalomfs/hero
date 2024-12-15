import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GraphicsTest {
    private Graphics graphics;
    private TextGraphics textGraphics;

    @BeforeEach
    public void setUp() {
        textGraphics = mock(TextGraphics.class);
        graphics = new Graphics(textGraphics);
    }

    @Test
    public void testEnableModifiers() {
        SGR sgr = SGR.BOLD;
        graphics.enableModifiers(sgr);
        verify(textGraphics).enableModifiers(sgr);
    }

    @Test
    public void testSetForegroundColor() {
        TextColor color = TextColor.ANSI.RED;
        graphics.setForegroundColor(color);
        verify(textGraphics).setForegroundColor(color);
    }

    @Test
    public void testPutString() {
        TerminalPosition position = new TerminalPosition(5, 5);
        String str = "Test";
        graphics.putString(position, str);
        verify(textGraphics).putString(position, str);
    }
}
