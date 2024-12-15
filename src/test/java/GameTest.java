import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameTest {
    private Game game;
    private Screen screenMock;
    private Graphics graphicsMock;
    private Arena arenaMock;

    @BeforeEach
    void setUp() throws IOException {
        screenMock = mock(Screen.class);
        graphicsMock = mock(Graphics.class);
        arenaMock = mock(Arena.class);

        game = new Game();

        injectMock(game, "screen", screenMock);
        injectMock(game, "graphics", graphicsMock);
        injectMock(game, "arena", arenaMock);
    }

    @Test
    void testDraw() throws IOException {
        game.draw();
        verify(screenMock).clear();
        verify(arenaMock).draw(graphicsMock);
        verify(screenMock).refresh();
    }

    @Test
    void testProcessKey() {
        KeyStroke keyStroke = mock(KeyStroke.class);
        game.processKey(keyStroke);
        verify(arenaMock).processKey(keyStroke);
    }

    @Test
    void testRunQuitKey() throws IOException {
        when(screenMock.readInput())
                .thenReturn(new KeyStroke('q', false, false));
        game.run();
        verify(screenMock).close();
    }

    @Test
    void testRunEOFKey() throws IOException {
        when(screenMock.readInput())
                .thenReturn(new KeyStroke(KeyType.EOF));
        game.run();
        verify(screenMock).close();
    }

    @Test
    void testRunExceptionHandling() throws IOException {
        when(screenMock.readInput()).thenThrow(new IOException("Test exception"));
        game.run();
        verify(screenMock).close();
    }

    private void injectMock(Object target, String fieldName, Object mock) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, mock);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to inject mock for field: " + fieldName, e);
        }
    }
}
