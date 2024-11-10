import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PositionTest {
    private Position position;

    @BeforeEach
    public void setUp() {
        position = new Position(5, 4);
    }

    @Test
    public void testGetX() {
        assertEquals(position.getX(), 5);
    }

    @Test
    public void testGetY() {
        assertEquals(position.getY(), 4);
    }

    @Test
    public void testSetX() {
        position.setX(10);
        assertEquals(position.getX(), 10);
    }

    @Test
    public void testSetY() {
        position.setY(11);
        assertEquals(position.getY(), 11);
    }

    @Test
    public void testEquals_equal() {
        Position position2 = new Position(5, 4);
        assertTrue(position.equals(position2));
    }

    @Test
    public void testEquals_different() {
        Position position2 = new Position(4, 5);
        assertFalse(position.equals(position2));
    }
}
