package fireemblem.console;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import fireemblem.controlleur.Combat;

public class VueConsole_combat extends VueConsole {
	
	private final Combat combat;
	
	public VueConsole_combat (Combat combat) {
		this.combat = combat;
		this.combat.ajouterEcouteur(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(VueConsole_combat.this.combat.COMBAT)) {
                    combat((int[])evt.getOldValue(), (int[])evt.getNewValue());
                } else if (evt.getPropertyName().equals(VueConsole_combat.this.combat.MODIFY_PV_PERSO1)) {
                    modifyPvPerso1((int)evt.getOldValue());
                } else if (evt.getPropertyName().equals(VueConsole_combat.this.combat.MODIFY_PV_PERSO2)) {
                    modifyPvPerso2((int)evt.getOldValue());
                }
            }
        });
	}
    
    private void combat (int statPerso1[], int statPerso2[]) {
        
    }
    
    private void modifyPvPerso1 (int pv) {
    	System.out.println(this.combat.getPerso1() + " : PV = " + pv);
    }
    
    private void modifyPvPerso2 (int pv) {
    	System.out.println(this.combat.getPerso1() + " : PV = " + pv);
    }

}