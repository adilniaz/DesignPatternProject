package abstracts_interfaces;

import java.util.ArrayList;

public abstract class SujetObserveAbstrait {
	
	private ArrayList<ObserverAbstract> observateurList = new ArrayList<ObserverAbstract>();

    public void Attach(ObserverAbstract observer)
    {
        observateurList.add(observer);
    }

    public void Detach(ObserverAbstract observer)
    {
        observateurList.remove(observer);
    }
    
    public void Notify()
    {
    	for (ObserverAbstract o : observateurList) {
			o.Update();
		}
    }
	
}
