package sa.booking;
import sa.observer.interfaces.INotifyObserver;

//import sa.subscriptions.INotifyObserver;

public interface INotifyConfiguration {
	
	public void registerObserver(INotifyObserver o);
	public void unregisterObserver(INotifyObserver o);
	public void updateObservers();
}
