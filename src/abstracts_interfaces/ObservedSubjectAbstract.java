package abstracts_interfaces;

import java.util.ArrayList;

public abstract class ObservedSubjectAbstract {
	
	private ArrayList<ObserverAbstract> observerList = new ArrayList<ObserverAbstract>();

    public void attach(ObserverAbstract observer)
    {
        observerList.add(observer);
    }

    public void detach(ObserverAbstract observer)
    {
        observerList.remove(observer);
    }
    
    public void notifyObservers()
    {
    	for (ObserverAbstract o : observerList) {
			o.update();
		}
    }
	
}
