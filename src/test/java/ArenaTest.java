import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArenaTest {
    private Arena arena;

    @BeforeEach
    public void setUp() {
        arena = new Arena(80, 24);
    }


    @Test
    public void testInitialHeroPosition() {
        Assertions.assertEquals(10, arena.getHeroPosition().getX());
        Assertions.assertEquals(10, arena.getHeroPosition().getY());
    }

    @Test
    public void testWallCreation() {
        List<Wall> walls = arena.getWalls();
        Assertions.assertEquals(2 * (arena.getWidth() + arena.getHeight() - 2), walls.size());
        for(Wall wall : walls) {
            Assertions.assertTrue(
                    wall.getPosition().getX()==0 ||
                            wall.getPosition().getY()==0 ||
                            wall.getPosition().getX()==arena.getWidth()-1 ||
                            wall.getPosition().getY()==arena.getHeight()-1);
        }
    }

    @Test
    public void testCoinCollection() {
        arena.getCoins().add(new Coin(new Position(20,20)));
        arena.retrieveCoins();
        Assertions.assertFalse(arena.getCoins().contains(new Coin(new Position(20,20))));
    }

    @Test
    public void testCoinsInitialization() {
        List<Coin> coins = arena.getCoins();
        for (Coin coin : coins) {
            Assertions.assertTrue(coin.getPosition().getX() > 0 && coin.getPosition().getX() < arena.getWidth() - 1);
            Assertions.assertTrue(coin.getPosition().getY() > 0 && coin.getPosition().getY() < arena.getHeight() - 1);
            Assertions.assertNotEquals(coin.getPosition(), arena.getHeroPosition());
        }
    }


    @Test
    public void testMonsterMovement() {
        List<Monster> monsters = arena.getMonsters();
        for (Monster monster : monsters) {
            Position initialPosition = monster.getPosition();
            Position newPosition = monster.nextPosition();
            if (arena.canMonsterMove(newPosition)) {
                Assertions.assertNotEquals(initialPosition, newPosition);
            }
        }
    }

    @Test
    public void testHeroMovement() {
        Position initialPosition = arena.getHeroPosition();
        arena.processKey(new KeyStroke(KeyType.ArrowUp));
        if (arena.canHeroMove(new Position(initialPosition.getX(), initialPosition.getY()-1))) {
            Assertions.assertEquals(initialPosition.getX(), arena.getHeroPosition().getX());
            Assertions.assertEquals(initialPosition.getY()-1, arena.getHeroPosition().getY());
        } else {
            Assertions.assertEquals(initialPosition.getX(), arena.getHeroPosition().getX());
            Assertions.assertEquals(initialPosition.getY(), arena.getHeroPosition().getY());
        }
    }

    @Test
    public void testProcessKeyHeroMovementRight() {
        Position initialPosition = arena.getHeroPosition();
        arena.processKey(new KeyStroke(KeyType.ArrowRight));
        Position expectedPosition = new Position(initialPosition.getX()+1, initialPosition.getY());
        if (arena.canHeroMove(expectedPosition)) {
            Assertions.assertEquals(expectedPosition.getX(), arena.getHeroPosition().getX());
            Assertions.assertEquals(expectedPosition.getY(), arena.getHeroPosition().getY());
        } else {
            Assertions.assertEquals(initialPosition.getX(), arena.getHeroPosition().getX());
            Assertions.assertEquals(initialPosition.getY(), arena.getHeroPosition().getY());
        }
    }

    @Test
    public void testProcessKeyHeroMovementDown() {
        Position initialPosition = arena.getHeroPosition();
        arena.processKey(new KeyStroke(KeyType.ArrowDown));
        Position expectedPosition = new Position(initialPosition.getX(), initialPosition.getY() + 1);
        if (arena.canHeroMove(expectedPosition)) {
            Assertions.assertEquals(expectedPosition.getX(), arena.getHeroPosition().getX());
            Assertions.assertEquals(expectedPosition.getY(), arena.getHeroPosition().getY());
        } else {
            Assertions.assertEquals(initialPosition.getX(), arena.getHeroPosition().getX());
            Assertions.assertEquals(initialPosition.getY(), arena.getHeroPosition().getY());
        }
    }

    @Test
    public void testHeroCannotMoveIntoWall() {
        for(int i=0;i<10;i++) {
            arena.processKey(new KeyStroke(KeyType.ArrowUp));
        }
        Position initialPosition = arena.getHeroPosition();
        arena.processKey(new KeyStroke(KeyType.ArrowUp));
        Assertions.assertEquals(initialPosition, arena.getHeroPosition());
    }
}