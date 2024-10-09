import java.io.IOException;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Game {
    private Screen screen;
    private Arena arena = new Arena(80,24);
    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            this.screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null); // We don't need a cursor
            screen.startScreen(); // Screens must be started
            screen.doResizeIfNecessary(); // Resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen);
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
        arena.processKey(key);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}