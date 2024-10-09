import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    private Hero hero = new Hero(new Position(10,10));
    public boolean canHeroMove(Position position) {
        return ((position.getX()>0 && position.getX()<width) && (position.getY()>0) && (position.getY()<height));
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void draw(Screen screen) {
        screen.setCharacter(hero.getPosition().getX(), hero.getPosition().getY(), TextCharacter.fromCharacter('X')[0]);
    }
    public void processKey(KeyStroke key) throws IOException {
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
