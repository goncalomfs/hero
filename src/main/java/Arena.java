import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.function.Predicate.not;

public class Arena {
    private int width;
    private int height;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private Hero hero = new Hero(new Position(10,10));
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Coin tempCoin = new Coin(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            while (inElement(tempCoin, coins) && (tempCoin.getPosition().equals(hero.getPosition()))){
                tempCoin = new Coin(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            }
            coins.add(tempCoin);
        }
        return coins;
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Monster tempMonster = new Monster(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            while (inElement(tempMonster, monsters) && (tempMonster.getPosition().equals(hero.getPosition()))) {
                tempMonster = new Monster(new Position(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
            }
            monsters.add(tempMonster);
        }
        return monsters;
    }

    public boolean inElement(Element element, List<? extends Element> elements) {
        for (Element el : elements) {
            if (element.getPosition().equals(el.getPosition())) {
                return true;
            }
        }
        return false;
    }

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public boolean canHeroMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
        retrieveCoins();
        for (Monster monster : monsters) {
            monster.move(width,height);
        }
        verifyMonsterCollisions();
    }

    public void retrieveCoins() {
        coins.removeIf(coin -> coin.getPosition().equals(hero.getPosition()));
    }

    public void verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (hero.getPosition().equals(monster.getPosition())) {
                System.out.print("You Lost!");
                System.exit(0);
            }
        }
    }

    public void draw(TextGraphics graphics) {
        hero.draw(graphics);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), "X");
        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for (Coin coin : coins) {
            coin.draw(graphics);
        }
        for (Monster monster : monsters) {
            monster.draw(graphics);
        }
    }

    public void processKey(KeyStroke key)  {
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            default:
                break;
        }
    }
}
