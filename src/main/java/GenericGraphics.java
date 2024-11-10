import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;

public interface GenericGraphics {
        void enableModifiers(SGR sgr);
        void setForegroundColor(TextColor color);
        void putString(TerminalPosition position, String str);
}
