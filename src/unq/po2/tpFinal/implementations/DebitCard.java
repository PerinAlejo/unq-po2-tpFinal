package unq.po2.tpFinal.implementations;

import unq.po2.tpFinal.interfaces.PaymentMethod;

public class DebitCard implements PaymentMethod {

	@Override
	public void applyCharge(double amount) {
		// Aplicar un cargo a la tarjeta de débito
	}

	@Override
	public void receivePayment(double amount) {
		// la tarjeta de débito recibe un pago
	}

}
