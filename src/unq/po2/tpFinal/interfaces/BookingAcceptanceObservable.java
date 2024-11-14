package unq.po2.tpFinal.interfaces;

public interface BookingAcceptanceObservable {

	void addObserver(BookingAcceptedObserver observer);

	void removeObserver(BookingAcceptedObserver observer);

}