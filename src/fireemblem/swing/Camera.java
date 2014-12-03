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
	
	public Panel getCameraView (Position centre, PanelCentre panelCentre) {
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
				Parcelle parcelle = panelCentre.getParcelle(minX+i, minY+j);
				//parcelle.resize(this.width / this.colone, this.height / this.ligne);
				newPanelCentre.ajouterParcelle(parcelle, i, j);
				System.out.println("i = " + i + " ; j = " + j + " ; minX = " + minX + " ; minY = " + minY);
				System.out.println("parcelle : " + panelCentre.getParcelle(minX+i, minY+j).getComponents().length);
				System.out.println("parcelle : " + panelCentre.getParcelle(minX+i, minY+j).getComponent(0));
				System.out.println("parcelle : " + panelCentre.getParcelle(i, j).getComponents().length);
			}
		}
		panel.changerCentre(newPanelCentre, maxX - minX, maxY-minY, this.width, this.height);
		return panel;
	}
	
	/*public Panel getCameraView (Position centre, PanelCentre panelCentre) {
		Panel panel = new Panel();
		int minX = centre.getPositionX() - this.width;
		int minY = centre.getPositionY() - this.height;
		int maxX = centre.getPositionX() + this.width;
		int maxY = centre.getPositionY() + this.height;
		PanelCentre newPanelCentre = new PanelCentreParcelle(2*this.width, 2*this.height, 1250, 650);
		for (int i = 0 ; i < 2*this.width ; i++) {
			for (int j = 0 ; j < 2*this.height ; j++) {
				Parcelle parcelle = panelCentre.getParcelle(minX+i, minY+j);
				parcelle.resize(1250 / (2*this.width), 650 / (2*this.height));
				newPanelCentre.ajouterParcelle(parcelle, i, j);
				System.out.println("i = " + i + " ; j = " + j + " ; minX = " + minX + " ; minY = " + minY);
				System.out.println("parcelle : " + panelCentre.getParcelle(minX+i, minY+j).getComponents().length);
				System.out.println("parcelle : " + panelCentre.getParcelle(minX+i, minY+j).getComponent(0));
				/*newPanelCentre.ajouterViewBackground(panelCentre.getViewBackground(minX+i, minY+j), i, j);
				for (int k = 0 ; k < panelCentre.getNivMax() ; k++) {
					newPanelCentre.ajouterViewContent(panelCentre.getViewComponent(k, minX+i, minY+j), i, j, k);
				}
				System.out.println("parcelle : " + panelCentre.getParcelle(i, j).getComponents().length);
			}
		}
		panel.changerCentre(newPanelCentre, maxX - minX, maxY-minY, 1250, 650);
		return panel;
	}*/
	
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
					//System.out.println("components["+(minX+i)+"]["+(minY+j)+"]["+(k)+"] : " + components[minX+i][minY+j][k]);
					if (components[minX+i][minY+j][k] != null) {
						if (k == 0) {
							newPanelCentre.ajouterViewBackground(components[minX+i][minY+j][k], i, j);
							//newPanelCentre.getParcelle(i, j).getBackgroundComponent();
							//System.out.println("parcelle : " + newPanelCentre.getParcelle(i, j).getComponent(0));
						} else {
							newPanelCentre.ajouterViewContent(components[minX+i][minY+j][k], i, j, k);
						}
						//newPanelCentre.getParcelle(i, j).resize(1250 / (2*this.width), 650 / (2*this.height));
						//System.out.println("parcelle : " + newPanelCentre.getParcelle(i, j).getComponents().length);
					}
				}
			}
		}
		panel.changerCentre(newPanelCentre, maxX - minX, maxY-minY, this.width, this.height);
		return panel;
	}

}
