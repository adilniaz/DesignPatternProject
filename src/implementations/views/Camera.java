package implementations.views;

import implementations.Position;

public class Camera {
	
	private int width;
	private int height;
	private int ligne;
	private int colone;
	private Position centerPoint;
	
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
	
	public void setCenterPoint (Position center) {
		this.centerPoint = center;
	}
	
	public boolean move (Position oldPos, Position newPos, int width, int height) {
		int xRayon = this.colone / 2;
		int yRayon = this.ligne / 2;
		int minX = this.centerPoint.getPositionX() - xRayon;
		int minY = this.centerPoint.getPositionY() - yRayon;
		int maxX = this.centerPoint.getPositionX() + xRayon;
		int maxY = this.centerPoint.getPositionY() + yRayon;
		if (newPos.getPositionX() == minX+1 && minX > 0) {
			this.centerPoint.setPositionX(this.centerPoint.getPositionX() -1);
			return true;
		} else if (newPos.getPositionX() == maxX-1 && maxX < width-1) {
			this.centerPoint.setPositionX(this.centerPoint.getPositionX() +1);
			return true;
		} else if (newPos.getPositionY() == minY+1 && minY > 0) {
			this.centerPoint.setPositionY(this.centerPoint.getPositionY() -1);
			return true;
		} else if (newPos.getPositionY() == maxY-1 && maxY < height-1) {
			this.centerPoint.setPositionY(this.centerPoint.getPositionY() +1);
			return true;
		}
		return false;
	}
	
	public void refresh (Panel panel, Position position, int niveau, Position centre, 
			ComponentView components[][][], ComponentView viewComponent) {
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
	
	public Panel getCameraView (Position centre, ComponentView components[][][]) {
		Panel panel = new Panel();
		int xRayon = this.colone / 2;
		int yRayon = this.ligne / 2;
		int minX = this.centerPoint.getPositionX() - xRayon;
		int minY = this.centerPoint.getPositionY() - yRayon;
		int maxX = this.centerPoint.getPositionX() + xRayon;
		int maxY = this.centerPoint.getPositionY() + yRayon;
		PanelCentre newPanelCentre = new PanelCentreParcelle(this.colone, this.ligne, this.width, this.height);
		/*System.out.println("centre : " + this.centerPoint);
		System.out.println("minX = " + minX + " ; minY = " + minY);*/
		for (int i = 0 ; i < this.colone ; i++) {
			for (int j = 0 ; j < this.ligne ; j++) {
				for (int k = 0 ; k < 3 ; k++) {
					/*System.out.println("i = " + i + " ; j = " + j + " ; k = " + k);*/
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
