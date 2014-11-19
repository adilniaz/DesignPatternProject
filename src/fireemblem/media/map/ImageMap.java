package fireemblem.media.map;

import java.awt.image.BufferedImage;

import fireemblem.plateauJeu.CaseFort;
import fireemblem.plateauJeu.CasePlaine;
import fireemblem.swing.Image;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class ImageMap extends Image {
    
    private static final ImageMap _ImageMap;
    
    static {
        _ImageMap = new ImageMap();
    }
    
    public static BufferedImage getImageFromZone (ZoneAbstract zone) {
        return _ImageMap.getImage(zone);
    }
    
    public BufferedImage getImage (ZoneAbstract zone) {
    	 
        if (zone instanceof CasePlaine) {
        	if (this.aImage("plaine")) {
                return this.getImage(this.getUrlImage("plaine"));
            }
        } else if (zone instanceof CaseFort) {
            if (this.aImage("fort")) {
                return this.getImage(this.getUrlImage("fort"));
            }
        }
        return null;
    }
    
}
