package unq.po2.tpFinal.domain;


public interface PaymentMethod{
	void applyCharge(double amount);
	void receivePayment(double amount);
}