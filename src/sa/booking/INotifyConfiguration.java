package sa.booking;

import sa.observer.interfaces.*;

public interface INotifyConfiguration {
	
	public void registerObserver(INotifyObserver o);
	public void unregisterObserver(INotifyObserver o);
	public void updateObservers();
}
