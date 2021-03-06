package implementations.media.character;

import implementations.behaviour.CharacterBehaviour;
import implementations.behaviour.KnightBehaviour;
import implementations.behaviour.LordHappiaBehaviour;
import implementations.behaviour.WarriorBehaviour;
import implementations.character.Character;
import implementations.character.Character.Etat;
import implementations.views.Image;
import implementations.views.ImageManager;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import abstracts_interfaces.CharacterAbstract;

public class CharacterImage extends Image {
    
    private static final CharacterImage _ImagePersonnage;
    
    private static final String COMBAT = "combat/";
    private static final String MAP = "map/";
    private static final String MENU = "menu/";
    private static final String DEFAULT_IMAGE = "default";
    
    static {
        _ImagePersonnage = new CharacterImage();
    }
    
    private String getNameFromComportement (CharacterBehaviour cp) {
        if (cp instanceof WarriorBehaviour) {
            return "combattant_ennemie";
        } else if (cp instanceof KnightBehaviour) {
            return "chevalier";
        } else if (cp instanceof LordHappiaBehaviour) {
            return "lord_happia";
        }
        return "";
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
    
    public BufferedImage getImageMenu (CharacterAbstract perso) {
        Character p = (Character) perso;
        if (this.aImage(perso.getName(), MENU)) {
            return this.getImage(this.getInputStreamImage(perso.getName(), MENU));
        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage()), MENU)) {
            return this.getImage(this.getInputStreamImage(this.getNameFromComportement(p.getComportementPersonnage()), MENU));
        }
        return this.getImage(DEFAULT_IMAGE, MENU);
    }
    
    public BufferedImage getImageCombat (CharacterAbstract perso) {
        Character p = (Character) perso;
        if (this.aImage(p.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage())+"_"+p.getArme().getTypeArme().name()+"1", COMBAT)) {
        	return this.getImage(this.getInputStreamImage(p.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage())+"_"+p.getArme().getTypeArme().name()+"1", COMBAT));
        } else if (this.aImage(perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage())+"1", COMBAT)) {
        	return this.getImage(this.getInputStreamImage(perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage())+"1", COMBAT));
        } else if (this.aImage(perso.getName()+"1", COMBAT)) {
        	return this.getImage(this.getInputStreamImage(perso.getName()+"1", COMBAT));
        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage())+"1", COMBAT)) {
        	return this.getImage(this.getInputStreamImage(this.getNameFromComportement(p.getComportementPersonnage())+"1", COMBAT));
        }
        return this.getImage(DEFAULT_IMAGE, COMBAT);
    }
    
    public Map<Integer, ImageManager> getImageCombat (CharacterAbstract perso, String type) {
        Character p = (Character) perso;
        Map<Integer, ImageManager> images = new HashMap<>();
        int indice = 1;
        String persoName = "";
        if (this.aImage(perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage())+"_"+p.getArme().getTypeArme().name()+type+indice, COMBAT)) {
        	persoName = perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage())+"_"+p.getArme().getTypeArme().name()+type;
        } else if (this.aImage(perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage())+type+indice, COMBAT)) {
        	persoName = perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage())+type;
        } else if (this.aImage(perso.getName()+type+indice, COMBAT)) {
        	persoName = perso.getName()+type;
        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage())+type+indice, COMBAT)) {
        	persoName = this.getNameFromComportement(p.getComportementPersonnage())+type;
        }
        while (this.aImage(persoName+indice, COMBAT)) {
            ImageManager imageManager = new ImageManager();
            imageManager.setImage(this.getImage(this.getInputStreamImage(persoName+indice, COMBAT)));
            imageManager.setDisplayTime(120);
            imageManager.setDeplacement(0);
            images.put(indice, imageManager);
            indice++;
        }
        if (!persoName.equals("")) {
	        ImageManager imageManager = new ImageManager();
	        imageManager.setImage(this.getImage(this.getInputStreamImage(persoName+"1", COMBAT)));
	        imageManager.setDisplayTime(120);
	        imageManager.setDeplacement(0);
	        images.put(indice, imageManager);
        }
        if (images.isEmpty() && !"".equals(type) && !"_esquive".equals(type)) {
        	images = this.getImageCombat(perso, "");
        }
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
    
    public static ImageIcon getImageIconMapFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageIconMap(perso);
    }
    
    public static ImageIcon getImageIconMapFocusFromPersonnage (CharacterAbstract perso) {
        return _ImagePersonnage.getImageIconMapFocus(perso);
    }
    
    public ImageIcon getImageIconMap (CharacterAbstract perso) {
        Character p = (Character) perso;
        if (p.getEtat() == Etat.attendre) {
        	if (this.aImage(perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage()) + "_attente", MAP)) {
	            return this.getImageIcon(this.getInputStreamImage(perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage()) + "_attente", MAP));
	        } else if (this.aImage(perso.getName() + "_attente", MAP)) {
	            return this.getImageIcon(this.getInputStreamImage(perso.getName() + "_attente", MAP));
	        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage()) + "_attente", MAP)) {
	            return this.getImageIcon(this.getInputStreamImage(this.getNameFromComportement(p.getComportementPersonnage()) + "_attente", MAP));
	        }
        } else {
	        if (this.aImage(perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage()), MAP)) {
	            return this.getImageIcon(this.getInputStreamImage(perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage()), MAP));
	        } else if (this.aImage(perso.getName(), MAP)) {
	            return this.getImageIcon(this.getInputStreamImage(perso.getName(), MAP));
	        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage()), MAP)) {
	            return this.getImageIcon(this.getInputStreamImage(this.getNameFromComportement(p.getComportementPersonnage()), MAP));
	        }
        }
        return this.getImageIcon(this.getInputStreamImage(DEFAULT_IMAGE, MAP));
    }
    
    public ImageIcon getImageIconMapFocus (CharacterAbstract perso) {
        Character p = (Character) perso;
        if (p.getEtat() == Etat.attendre) {
        	return this.getImageIconMap(p);
        } else {
	        if (this.aImage(perso.getName()+"_" + this.getNameFromComportement(p.getComportementPersonnage()) + "_focus", MAP)) {
	            return this.getImageIcon(this.getInputStreamImage(perso.getName()+"_"+this.getNameFromComportement(p.getComportementPersonnage())+"_focus", MAP));
	        } else if (this.aImage(perso.getName()+"_focus", MAP)) {
	            return this.getImageIcon(this.getInputStreamImage(perso.getName()+"_focus", MAP));
	        } else if (this.aImage(this.getNameFromComportement(p.getComportementPersonnage()), MAP)) {
	            return this.getImageIcon(this.getInputStreamImage(this.getNameFromComportement(p.getComportementPersonnage()), MAP));
	        }
        }
        return this.getImageIcon(this.getInputStreamImage(DEFAULT_IMAGE, MAP));
    }
    
}
