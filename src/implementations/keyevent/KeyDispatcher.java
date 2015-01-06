package implementations.keyevent;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDispatcher implements KeyEventDispatcher {
    
    private final KeyListener keyListener;
    
    public KeyDispatcher (KeyListener keyListener) {
        this.keyListener = keyListener;
    }
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            this.keyListener.keyPressed(e);
        } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            this.keyListener.keyReleased(e);
        } else if (e.getID() == KeyEvent.KEY_TYPED) {
            this.keyListener.keyTyped(e);
        }
        return false;
    }
}
