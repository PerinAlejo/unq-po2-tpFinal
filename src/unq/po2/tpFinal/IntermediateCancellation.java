package unq.po2.tpFinal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class IntermediateCancellation extends CancellationPolicy {
	
	public IntermediateCancellation(Housing housing) {
		super(housing);
	}

	@Override
	public double getCancellationFee(DateRange range) {
		long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), range.getStart());
		
		 if (diasRestantes >= 20) {
	            return 0;
	        } else if (diasRestantes < 20 && diasRestantes >= 10) {
	            return this.getHousing().getPrice(range) * 0.5; //Se calcula el 50% del total.
	        } else {
	        	return this.getHousing().getPrice(range); 
	        }
	}

}
