package implementations.object;

public enum WeaponType {
    
    arc, epee, hache, lance;
    
    public boolean isGood (WeaponType weaponType) {
    	switch (this) {
    		case epee:
    			switch (weaponType) {
	        		case hache :
	        			return true;
	        		default :
	        			return false;
	        	}
    		case hache:
    			switch (weaponType) {
	        		case lance :
	        			return true;
	        		default :
	        			return false;
	        	}
    		case lance:
    			switch (weaponType) {
	        		case epee :
	        			return true;
	        		default :
	        			return false;
	        	}
    		default :
    			return false;
    	}
    }
    
    public boolean isBad (WeaponType weaponType) {
    	switch (this) {
    		case epee:
    			switch (weaponType) {
	        		case lance :
	        			return true;
	        		default :
	        			return false;
	        	}
    		case hache:
    			switch (weaponType) {
	        		case epee :
	        			return true;
	        		default :
	        			return false;
	        	}
    		case lance:
    			switch (weaponType) {
	        		case hache :
	        			return true;
	        		default :
	        			return false;
	        	}
    		default :
    			return false;
    	}
    }
    
}
