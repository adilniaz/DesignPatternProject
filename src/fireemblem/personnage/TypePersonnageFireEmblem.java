package fireemblem.personnage;

import abstracts_interfaces.factories.characters.CharactersType;

public enum TypePersonnageFireEmblem implements CharactersType {
    
    archer, archimage, assassin, berserker, bretteur, cavalier, cav_faucon, cav_nomade, cav_pegase, cav_wyvern, chat, chevalier, chevalier_noir, 
    clerc, combattant, corbeau, dark_druide, dragon_rouge, dragon_blanc, druide, eveque, faucon, general,  guerrier, great_lord_ephraim, great_lord_eirika, 
    halbardier, heros, king_daein, lion, lord_eirika, lord_eliwood, lord_ephraim, lord_hector, lord_lyn, lord_roy, lord_equus, lord_happia, lord_spatha, 
    lord_wyvern, mage, marchand, master_lord, mercenaire, monk, myrmidon, nomade, paladin, pirate, pretre, princesse_crimea, ranger, sage, 
    shaman, sniper, soldat, swordmaster, tigre, troubadour, voleur, walkyrie;
    
    
    public static TypePersonnageFireEmblem getTypeByName (String name) {
    	switch (name) {
    		case "lord":
    			return lord_eliwood;
    		case "Combattant":
    			return combattant;
    		default :
    			return lord_eliwood;
    	}
    }
    
    
}
