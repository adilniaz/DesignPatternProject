package implementations.media.map;

import implementations.gameplatform.CaseFort;
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
