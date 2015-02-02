package implementations.media.map;

import implementations.gameplatform.CaseChateau;
import implementations.gameplatform.CaseFort;
import implementations.gameplatform.CaseMontagne;
import implementations.gameplatform.CasePlaine;
import implementations.views.Image;

import java.awt.image.BufferedImage;

import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;

public class MapImage extends Image {
    
    private static final MapImage _ImageMap;
    
    static {
        _ImageMap = new MapImage();
    }
    
    public static BufferedImage getImageFromZone (ZoneAbstract zone) {
        return _ImageMap.getImage(zone);
    }
    
    public BufferedImage getImage (ZoneAbstract zone) {
    	 
        if (zone instanceof CaseChateau) {
        	if (this.aImage("chateau")) {
                return this.getImage(this.getInputStreamImage("chateau"));
            }
        } else if (zone instanceof CaseFort) {
            if (this.aImage("fort")) {
                return this.getImage(this.getInputStreamImage("fort"));
            }
        } else if (zone instanceof CaseMontagne) {
            if (this.aImage("montagne")) {
                return this.getImage(this.getInputStreamImage("montagne"));
            }
        } else if (zone instanceof CasePlaine) {
        	if (this.aImage("plaine")) {
                return this.getImage(this.getInputStreamImage("plaine"));
            }
        }
        return null;
    }
    
}
