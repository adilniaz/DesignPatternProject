package implementations.keyevent;

import java.awt.event.KeyEvent;

public class ButtonCode {
    
    public final static int HAUT = 38;
    public final static int BAS = 40;
    public final static int GAUCHE = 37;
    public final static int DROITE = 39;
    public final static int ACTION = 65;
    public final static int ANNULATION = 90;
    public static int START = 10;
    public static int SELECT = 32;
    public static int BT3 = 81;
    public static int BT4 = 83;
    public static int F1 = 112;
    public static int F2 = 113;
    public static int F3 = 114;
    public static int F4 = 115;
    public static int F5 = 116;
    public static int F6 = 117;
    public static int F7 = 118;
    public static int F8 = 119;
    public static int F9 = 120;
    public static int F10 = 121;
    public static int F11 = 122;
    public static int F12 = 123;
    public static int E = 69;
    public final static int R = 82;
    
    public static String getKeyCharByCode (int code) {
        return KeyEvent.getKeyText(code);
    }
    
}
