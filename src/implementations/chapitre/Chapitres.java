package implementations.chapitre;

public enum Chapitres {
    
    blazing_sword, sword_of_seal, path_of_radiance;
    
    public static Chapitres getByName (String nom) {
    	switch (nom) {
	   		case "blazing sword":
	   			return blazing_sword;
	   		default :
	   			return blazing_sword;
	    }
    }
    
}