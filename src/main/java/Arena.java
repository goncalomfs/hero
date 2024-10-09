import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;


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
    public void draw(TextGraphics graphics) {
        hero.draw(graphics);
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(hero.getPosition().getX(), hero.getPosition().getY()), "X");
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
