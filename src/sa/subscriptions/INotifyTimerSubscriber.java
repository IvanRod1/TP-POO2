package sa.subscriptions;

import java.time.LocalDate;

import sa.booking.Reserve;

public interface INotifyTimerSubscriber {

		public void update(Reserve r,  LocalDate date);
}
