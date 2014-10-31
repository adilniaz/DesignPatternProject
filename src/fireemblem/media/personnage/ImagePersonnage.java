package fireemblem.media.personnage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import abstracts_interfaces.CharacterAbstract;
import fireemblem.personnage.ComportementCombattant;
import fireemblem.personnage.ComportementDarkDruide;
import fireemblem.personnage.ComportementLord;
import fireemblem.personnage.ComportementPersonnage;
import fireemblem.personnage.Personnage;
import fireemblem.swing.Image;
import fireemblem.swing.ImageManager;

public class ImagePersonnage extends Image {
    
    private static final ImagePersonnage _ImagePersonnage;
    
    private static final String COMBAT = "combat/";
    private static final String MAP = "map/";
    private static final String MENU = "menu/";
    
    static {
        _ImagePersonnage = new ImagePersonnage();
    }
    
    private String getNameFromComportement (ComportementPersonnage cp) {
        if (cp instanceof ComportementCombattant) {
            return "combattant_ennemie";
        }
        return null;
    }
    
    public static BufferedImage getImageFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImage(perso);
    }
    
    public static BufferedImage getImageMenuFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageMenu(perso);
    }
    
    public static BufferedImage getImageCombatFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageCombat(perso);
    }
    
    public static Map<Integer, ImageManager> getImageCombatAttaqueFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageCombatAttaque(perso);
    }
    
    public static Map<Integer, ImageManager> getImageCombatAttaqueCritiqueFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageCombatAttaqueCritique(perso);
    }
    
    public static Map<Integer, ImageManager> getImageCombatDistantFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageCombatDistant(perso);
    }
    
    public static Map<Integer, ImageManager> getImageCombatDistantCritiqueFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageCombatDistantCritique(perso);
    }
    
    public static Map<Integer, ImageManager> getImageCombatEsquiveFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageCombatEsquive(perso);
    }
    
    public BufferedImage getImage (CharacterAbstract perso) {
        Personnage p = (Personnage) perso;
        File file;
        try {
            if (p.getComportementPersonnage() instanceof ComportementLord) {
                if (p.getName().equals("Eliwood")) {
                    file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\eliwood.png");
                    return getImage(file.toURI().toURL());
                } else if (p.getName().equals("Hector")) {
                    file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\hector.png");
                    return getImage(file.toURI().toURL());
                } else if (p.getName().equals("Lyn")) {
                    file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\lyn.png");
                    return getImage(file.toURI().toURL());
                }
                
            } else if (p.getComportementPersonnage() instanceof ComportementDarkDruide) {
                file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\nergal.png");
                return getImage(file.toURI().toURL());
            } else {
                file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\roy.png");
                return getImage(file.toURI().toURL());
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public BufferedImage getImageMenu (CharacterAbstract perso) {
        Personnage p = (Personnage) perso;
        if (this.aImage(perso.getName(), MENU)) {
            return this.getImage(this.getUrlImage(perso.getName(), MENU));
        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage()), MENU)) {
            return this.getImage(this.getUrlImage(this.getNameFromComportement(p.getComportementPersonnage()), MENU));
        }
        return null;
    }
    
    public BufferedImage getImageCombat (CharacterAbstract perso) {
        Personnage p = (Personnage) perso;
        if (this.aImage(perso.getName()+"1", COMBAT)) {
            return this.getImage(this.getUrlImage(perso.getName()+"1", COMBAT));
        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage())+"1", COMBAT)) {
            return this.getImage(this.getUrlImage(this.getNameFromComportement(p.getComportementPersonnage())+"1", COMBAT));
        }
        return null;
    }
    
    public Map<Integer, ImageManager> getImageCombat (CharacterAbstract perso, String type) {
        Personnage p = (Personnage) perso;
        Map<Integer, ImageManager> images = new HashMap<>();
        int indice = 1;
        String persoName = perso.getName()+type;
        String comportementName = this.getNameFromComportement(p.getComportementPersonnage())+type;
        while (this.aImage(persoName+indice, COMBAT) || this.aImage(comportementName+indice, COMBAT)) {
            ImageManager imageManager = new ImageManager();
            if (this.aImage(persoName+indice, COMBAT)) {
                imageManager.setImage(this.getImage(this.getUrlImage(persoName+indice, COMBAT)));
            } else if (this.aImage(comportementName+indice, COMBAT)) {
                imageManager.setImage(this.getImage(this.getUrlImage(comportementName+indice, COMBAT)));
            }
            imageManager.setDisplayTime(120);
            imageManager.setDeplacement(0);
            images.put(indice, imageManager);
            indice++;
        }
        ImageManager imageManager = new ImageManager();
        if (this.aImage(persoName+"1", COMBAT)) {
            imageManager.setImage(this.getImage(this.getUrlImage(persoName+"1", COMBAT)));
        } else if (this.aImage(comportementName+"1", COMBAT)) {
            imageManager.setImage(this.getImage(this.getUrlImage(comportementName+"1", COMBAT)));
        }
        imageManager.setDisplayTime(120);
        imageManager.setDeplacement(0);
        images.put(indice, imageManager);
        return images;
    }
    
    public Map<Integer, ImageManager> getImageCombatAttaque (CharacterAbstract perso) {
        Map<Integer, ImageManager> images = this.getImageCombat(perso, "");
        return images;
    }
    
    public Map<Integer, ImageManager> getImageCombatAttaqueCritique (CharacterAbstract perso) {
        Map<Integer, ImageManager> images = this.getImageCombat(perso, "_critique");
        return images;
    }
    
    public Map<Integer, ImageManager> getImageCombatDistant (CharacterAbstract perso) {
        Map<Integer, ImageManager> images = this.getImageCombat(perso, "_distant");
        return images;
    }
    
    public Map<Integer, ImageManager> getImageCombatDistantCritique (CharacterAbstract perso) {
        Map<Integer, ImageManager> images = this.getImageCombat(perso, "_distant_critique");
        return images;
    }
    
    public Map<Integer, ImageManager> getImageCombatEsquive (CharacterAbstract perso) {
        Map<Integer, ImageManager> images = this.getImageCombat(perso, "_esquive");
        return images;
    }
    
    public static ImageIcon getImageIconFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageIcon(perso);
    }
    
    public static ImageIcon getImageIconMapFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageIconMap(perso);
    }
    
    public static ImageIcon getImageIconMapFocusFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageIconMapFocus(perso);
    }
    
    public ImageIcon getImageIcon (CharacterAbstract perso) {
        Personnage p = (Personnage) perso;
        File file;
        try {
            if (p.getComportementPersonnage() instanceof ComportementLord) {
                if (p.getName().equals("Eliwood")) {
                    file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\eliwood\\eliwood_map2.gif");
                    return getImageIcon(file.toURI().toURL());
                } else if (p.getName().equals("Hector")) {
                    file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\hector.png");
                    return getImageIcon(file.toURI().toURL());
                } else if (p.getName().equals("Lyn")) {
                    file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\lyn.png");
                    return getImageIcon(file.toURI().toURL());
                }
                
            } else if (p.getComportementPersonnage() instanceof ComportementDarkDruide) {
                file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\nergal.png");
                return getImageIcon(file.toURI().toURL());
            } else if (p.getComportementPersonnage() instanceof ComportementCombattant) {
                file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\combattant\\combattant_map_ennemie.gif");
                return getImageIcon(file.toURI().toURL());
            } else {
                file = new File("C:\\Users\\mike\\prive\\cours\\ESGI\\5AL\\design_pattern\\projet\\images\\roy.png");
                return getImageIcon(file.toURI().toURL());
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ImageIcon getImageIconMap (CharacterAbstract perso) {
        Personnage p = (Personnage) perso;
        if (this.aImage(perso.getName(), MAP)) {
            return this.getImageIcon(this.getUrlImage(perso.getName(), MAP));
        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage()), MAP)) {
            return this.getImageIcon(this.getUrlImage(this.getNameFromComportement(p.getComportementPersonnage()), MAP));
        }
        return null;
    }
    
    public ImageIcon getImageIconMapFocus (CharacterAbstract perso) {
        Personnage p = (Personnage) perso;
        if (this.aImage(perso.getName()+"_focus", MAP)) {
            return this.getImageIcon(this.getUrlImage(perso.getName()+"_focus", MAP));
        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage()), MAP)) {
            return this.getImageIcon(this.getUrlImage(this.getNameFromComportement(p.getComportementPersonnage()), MAP));
        }
        return null;
    }
    
}
