package implementations.chapters;

public enum Chapters {
    
    blazing_sword, sword_of_seal, path_of_radiance;
    
    public static Chapters getByName (String nom) {
    	switch (nom) {
	   		case "blazing sword":
	   			return blazing_sword;
	   		default :
	   			return blazing_sword;
	    }
    }
    
}