import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Random;

class MonsterTest {
    private Monster monster;
    private Position startPosition;

    @BeforeEach
    void setUp() {
        startPosition = new Position(5, 5);
        monster = new Monster(startPosition);
    }

    @Test
    void testNextPosition() {
        Position nextPosition = monster.nextPosition();
        monster.setPosition(nextPosition);
        Assertions.assertTrue((monster.getPosition().equals(new Position(6, 5)) ||
                monster.getPosition().equals(new Position(5, 4)) ||
                monster.getPosition().equals(new Position(5, 6)) ||
                monster.getPosition().equals(new Position(4, 5))));
    }

    @Test
    void testDraw() {
        GenericGraphics mockGraphics = mock(GenericGraphics.class);
        monster.draw(mockGraphics);
        verify(mockGraphics).setForegroundColor(TextColor.ANSI.RED);
        verify(mockGraphics).putString(new TerminalPosition(5, 5), "M");
    }
}
