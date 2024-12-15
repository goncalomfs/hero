import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Game {
    private Screen screen;
    private Arena arena;
    private Graphics graphics;

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(80, 40);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            this.graphics = new Graphics(screen.newTextGraphics());
            this.arena = new Arena(80, 40);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            initializeBackground();
        } catch (IOException e) {
            System.err.println("Error initializing game: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initializeBackground() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#008631"));
        graphics.putString(new TerminalPosition(0, 0), " ");
    }

    public void draw() throws IOException {
        screen.clear();
        arena.draw(graphics);
        screen.refresh();
    }

    public void processKey(KeyStroke key) {
        arena.processKey(key);
    }

    public void run() {
        try {
            while (true) {
                draw();
                KeyStroke key = screen.readInput();

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    break;
                }
                if (key.getKeyType() == KeyType.EOF) {
                    break;
                }

                processKey(key);
            }
        } catch (IOException e) {
            System.err.println("Error during game execution: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeScreen();
        }
    }

    private void closeScreen() {
        try {
            if (screen != null) {
                screen.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing the screen: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
