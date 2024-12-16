import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CoinTest {
    private Coin coin;
    private GenericGraphics graphicsMock;
    private Position positionMock;

    @BeforeEach
    void setUp() {
        positionMock = mock(Position.class);
        graphicsMock = mock(GenericGraphics.class);
        coin = new Coin(positionMock);
    }

    @Test
    void testDraw() {
        when(positionMock.getX()).thenReturn(5);
        when(positionMock.getY()).thenReturn(10);

        coin.draw(graphicsMock);

        verify(graphicsMock).setForegroundColor(TextColor.ANSI.YELLOW);
        verify(graphicsMock).putString(new TerminalPosition(5, 10), "$");
    }
}