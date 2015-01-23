package implementations.character;

import abstracts_interfaces.factories.characters.CharactersType;

public enum FireEmblemCharacterType implements CharactersType {
    
    chevalier, combattant, general, lord_eliwood, lord_equus, lord_happia, lord_hector, 
    lord_lyn, lord_spatha, marchand;
    
    
    public static FireEmblemCharacterType getTypeByName (String name) {
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
