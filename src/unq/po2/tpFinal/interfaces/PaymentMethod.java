package unq.po2.tpFinal.interfaces;


public interface PaymentMethod{
	void applyCharge(double amount);
	void receivePayment(double amount);
}