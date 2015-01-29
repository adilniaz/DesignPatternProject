package implementations.views;

import javax.swing.JComponent;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class MyPopup {
	
	private Popup popup;
	private int x;
	private int y;
	private Window window;
	private JComponent component;
	private final PopupFactory popupFactory;
	
	public MyPopup (int x, int y, Window window) {
		this.x = x;
		this.y = y;
		this.window = window;
		this.popupFactory = PopupFactory.getSharedInstance();
	}
	
	public void addComponent (JComponent component) {
		this.hide();
		this.component = component;
		this.popup = this.popupFactory.getPopup(this.window, this.component, this.window.getX() + this.x, this.window.getY() + this.y);
		this.popup.show();
	}
	
	public void show () {
		this.hide();
		this.popup = this.popupFactory.getPopup(this.window, this.component, this.window.getX() + this.x, this.window.getY() + this.y);
		this.popup.show();
	}
	
	public void hide () {
		if (this.popup != null) {
			this.popup.hide();
			this.popup = null;
		}
	}

}
