package fireemblem.swing;

import fireemblem.Position;

public class Camera {
	
	private int width;
	private int height;
	
	public Camera () {
		this.width = 5;
		this.height = 5;
	}
	
	public int getHeight () {
		return this.height;
	}
	
	public int getWidth () {
		return this.width;
	}
	
	public Panel getCameraView (Position centre, PanelCentre panelCentre) {
		Panel panel = new Panel();
		int minX = centre.getPositionX() - this.width;
		int minY = centre.getPositionY() - this.height;
		int maxX = centre.getPositionX() + this.width;
		int maxY = centre.getPositionY() + this.height;
		PanelCentre newPanelCentre = new PanelCentreParcelle(2*this.width, 2*this.height, 1250, 650);
		for (int i = 0 ; i < 2*this.width ; i++) {
			for (int j = 0 ; j < 2*this.height ; j++) {
				newPanelCentre.ajouterParcelle(panelCentre.getParcelle(minX+i, minY+j), i, j);
				System.out.println("i = " + i + " ; j = " + j + " ; minX = " + minX + " ; minY = " + minY);
				System.out.println("parcelle : " + panelCentre.getParcelle(minX+i, minY+j).getComponents().length);
				System.out.println("parcelle : " + panelCentre.getParcelle(minX+i, minY+j).getComponent(0));
				/*newPanelCentre.ajouterViewBackground(panelCentre.getViewBackground(minX+i, minY+j), i, j);
				for (int k = 0 ; k < panelCentre.getNivMax() ; k++) {
					newPanelCentre.ajouterViewContent(panelCentre.getViewComponent(k, minX+i, minY+j), i, j, k);
				}*/
				System.out.println("parcelle : " + panelCentre.getParcelle(i, j).getComponents().length);
			}
		}
		panel.changerCentre(newPanelCentre, maxX - minX, maxY-minY, 1250, 650);
		return panel;
	}

}
