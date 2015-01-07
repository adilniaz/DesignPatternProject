package implementations.console;

import implementations.controller.Combat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class CombatConsoleView extends ConsoleView {
	
	private final Combat combat;
	
	public CombatConsoleView (Combat combat) {
		this.combat = combat;
		this.combat.addListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(CombatConsoleView.this.combat.COMBAT)) {
                    combat((int[])evt.getOldValue(), (int[])evt.getNewValue());
                } else if (evt.getPropertyName().equals(CombatConsoleView.this.combat.MODIFY_PV_PERSO1)) {
                    modifyPvPerso1((int)evt.getOldValue());
                } else if (evt.getPropertyName().equals(CombatConsoleView.this.combat.MODIFY_PV_PERSO2)) {
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
