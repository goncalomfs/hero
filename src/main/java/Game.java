import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Hero hero;
    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
            this.hero = new Hero(10, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void draw() throws IOException{
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }
    public void run() {
        while (true) {
            try {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    screen.close();
                    return;
                }
                if (key.getKeyType() == KeyType.EOF) {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void processKey(KeyStroke key) throws IOException {
        System.out.println(key);
        switch (key.getKeyType()) {
            case ArrowUp:
                this.hero.moveUp();
                break;
            case ArrowDown:
                this.hero.moveDown();
                break;
            case ArrowLeft:
                this.hero.moveLeft();
                break;
            case ArrowRight:
                this.hero.moveRight();
                break;
            default:
                break;
        }
    }
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}