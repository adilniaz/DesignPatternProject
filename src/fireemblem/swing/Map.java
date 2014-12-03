package fireemblem.swing;

import java.awt.Color;
import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import fireemblem.Position;
import fireemblem.media.map.ImageMap;
import fireemblem.media.personnage.ImagePersonnage;
import fireemblem.personnage.Personnage;
import fireemblem.plateauJeu.Case;
import fireemblem.plateauJeu.PlateauJeu;

public class Map {
	
	private PanelCentre panelCentre;
    private Camera camera;
    private Position centerPosition;
    private PlateauJeu plateauJeu;
    
    public Map (PlateauJeu plateauJeu) {
    	this.plateauJeu = plateauJeu;
    	this.camera = new Camera();
    	this.centerPosition = new Position(10, 10);
    }
    
    public void load () {
    	int width = this.camera.getParcelleWidth();
    	int height = this.camera.getParcelleHeight();
    	System.out.println("map = width : " + width + " ; height : "  +height);
    	this.panelCentre = new PanelCentreParcelle(20, 20, (20 * width), (20 * height));
    	for (ZoneAbstract zone : this.plateauJeu.getZones()) {
            Case c = (Case) zone;
            PanelImage panelImage = new PanelImage(ImageMap.getImageFromZone(zone), width, height);
            this.panelCentre.ajouterViewBackground(new ViewComponent(panelImage), c.getPosition().getPositionX(), c.getPosition().getPositionY());
        }
        for (CharacterAbstract perso : this.plateauJeu.getPersonnages()) {
            Personnage p = (Personnage) perso;
            this.panelCentre.ajouterViewContent(new ViewComponent(new PanelDrawing(Color.BLUE, PanelDrawing.drawingType.circle, width, height)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            this.panelCentre.ajouterViewContent(new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), width, height)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);
        }
        for (CharacterAbstract perso : this.plateauJeu.getEnnemies()) {
            Personnage p = (Personnage) perso;
            this.panelCentre.ajouterViewContent(new ViewComponent(new PanelDrawing(Color.RED, PanelDrawing.drawingType.circle, width, height)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 1);
            this.panelCentre.ajouterViewContent(new ViewComponent(new PanelImage(ImagePersonnage.getImageIconMapFromPersonnage(perso), width, height)), p.getPosition().getPositionX(), p.getPosition().getPositionY(), 2);
        }
    }
    
    public Panel display () {
    	return this.camera.getCameraView(this.centerPosition, this.panelCentre);
    }

}
