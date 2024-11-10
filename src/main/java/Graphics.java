import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Graphics implements GenericGraphics {
    private final TextGraphics textGraphics;

    public Graphics(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
    }

    @Override
    public void enableModifiers(SGR sgr) {
        textGraphics.enableModifiers(sgr);
    }

    @Override
    public void setForegroundColor(TextColor color) {
        textGraphics.setForegroundColor(color);
    }

    @Override
    public void putString(TerminalPosition position, String str) {
        textGraphics.putString(position, str);
    }
}
