package unq.po2.tpFinal.implementations;

import unq.po2.tpFinal.interfaces.PaymentMethod;

public class CreditCard implements PaymentMethod {

	@Override
	public void applyCharge(double amount) {
		// hacer un cargo a la tarjeta de credito
	}

	@Override
	public void receivePayment(double amount) {
		// acreditar un monto a la tarjet de credito
	}

}
