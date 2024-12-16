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
    void testEqualsSameObject() {
        Position p = new Position(5, 10);
        assertEquals(p, p);
    }

    @Test
    void testEqualsNullObject() {
        Position p = new Position(5, 10);
        assertNotEquals(null, p);
    }

    @Test
    void testEqualsDifferentClass() {
        Position p = new Position(5, 10);
        String notAPosition = "Not a Position";
        assertNotEquals(p, notAPosition);
    }

    @Test
    void testEqualsSameValues() {
        Position p1 = new Position(5, 10);
        Position p2 = new Position(5, 10);
        assertEquals(p1, p2);
    }

    @Test
    void testEqualsDifferentX() {
        Position p1 = new Position(5, 10);
        Position p2 = new Position(6, 10);
        assertNotEquals(p1, p2);
    }

    @Test
    void testEqualsDifferentY() {
        Position p1 = new Position(5, 10);
        Position p2 = new Position(5, 11);
        assertNotEquals(p1, p2);
    }
}
