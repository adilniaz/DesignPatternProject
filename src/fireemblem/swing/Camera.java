package fireemblem.swing;

import fireemblem.Position;

public class Camera {
	
	private int width;
	private int height;
	private int ligne;
	private int colone;
	
	public Camera () {
		this.width = 1250;
		this.height = 650;
		this.ligne = 10;
		this.colone = 10;
	}
	
	public int getHeight () {
		return this.height;
	}
	
	public int getWidth () {
		return this.width;
	}
	
	public int getLigne () {
		return this.ligne;
	}
	
	public int getColone () {
		return this.colone;
	}
	
	public int getParcelleWidth() {
		return this.width / this.colone;
	}
	
	public int getParcelleHeight() {
		return this.height / this.ligne;
	}
	
	public void refresh (Panel panel, Position position, int niveau, Position centre, 
			ViewComponent components[][][], ViewComponent viewComponent) {
		int xRayon = this.colone / 2;
		int yRayon = this.ligne / 2;
		int minX = centre.getPositionX() - xRayon;
		int minY = centre.getPositionY() - yRayon;
		components[position.getPositionX()][position.getPositionY()][niveau] = viewComponent;
		for (int i = 0 ; i < this.colone ; i++) {
			for (int j = 0 ; j < this.ligne ; j++) {
				if (position.equals(new Position(minX+i, minY+j))) {
					panel.getPanelCentre().ajouterViewContent(viewComponent, i, j, niveau);
				}
			}
		}
	}
	
	public Panel getCameraView (Position centre, ViewComponent components[][][]) {
		Panel panel = new Panel();
		int xRayon = this.colone / 2;
		int yRayon = this.ligne / 2;
		int minX = centre.getPositionX() - xRayon;
		int minY = centre.getPositionY() - yRayon;
		int maxX = centre.getPositionX() + xRayon;
		int maxY = centre.getPositionY() + yRayon;
		PanelCentre newPanelCentre = new PanelCentreParcelle(this.colone, this.ligne, this.width, this.height);
		for (int i = 0 ; i < this.colone ; i++) {
			for (int j = 0 ; j < this.ligne ; j++) {
				for (int k = 0 ; k < 3 ; k++) {
					if (components[minX+i][minY+j][k] != null) {
						if (k == 0) {
							newPanelCentre.ajouterViewBackground(components[minX+i][minY+j][k], i, j);
						} else {
							newPanelCentre.ajouterViewContent(components[minX+i][minY+j][k], i, j, k);
						}
					}
				}
			}
		}
		panel.changerCentre(newPanelCentre, maxX - minX, maxY-minY, this.width, this.height);
		return panel;
	}

}
