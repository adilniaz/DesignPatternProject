package fireemblem.media.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            File file;
            file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\plaine.png");
            try {
                return this.getImage(file.toURI().toURL());
            } catch (MalformedURLException ex) {
                Logger.getLogger(ImageMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (zone instanceof CaseFort) {
            File file;
            file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\fort.png");
            try {
                return this.getImage(file.toURI().toURL());
            } catch (MalformedURLException ex) {
                Logger.getLogger(ImageMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
}
