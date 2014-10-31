package fireemblem.swing;

import java.awt.Container;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Fenetre extends JFrame {
    
    private KeyEventDispatcher dispatcher;
    
    public Fenetre () {
        this("fenetre");
    }
    
    public Fenetre (String title) {
        this(title, 1250, 650);
    }
    
    public Fenetre (String title, int width, int height) {
        this.setTitle(title);
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public Fenetre (String title, BufferedImage image) {
        this(title);
        this.setIconImage(image);
    }
    
    public void ajouterPanel (Container panel) {
        this.setContentPane(panel);
        this.validate();
    }
    
    @Override
    public void addKeyListener (KeyListener key) {
        while (this.getKeyListeners().length > 0) {
            this.removeKeyListener(this.getKeyListeners()[0]);
        }
        super.addKeyListener(key);
    }
    
    public void addKeyBoardManager (KeyEventDispatcher dispatcher) { 
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        if (this.dispatcher != null) {
            manager.removeKeyEventDispatcher(this.dispatcher);
        }
        this.dispatcher = dispatcher;
        manager.addKeyEventDispatcher(dispatcher);
    }
    
    public void close () {
        this.processWindowEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING) );
    }
    
}
