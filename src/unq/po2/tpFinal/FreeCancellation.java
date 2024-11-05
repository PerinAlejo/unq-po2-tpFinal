package unq.po2.tpFinal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FreeCancellation extends CancellationPolicy {

	public FreeCancellation(Housing housing) {
		super(housing);
	}

	@Override
	public double getCancellationFee(DateRange range) {
		
		long diasRestantes = ChronoUnit.DAYS.between(LocalDate.now(), range.getStart());
		
		
		 if (diasRestantes >= 10) {
	            return 0;
	        } else if (diasRestantes > 0) {
	        	DateRange newRange = new DateRange(range.getStart(), range.getStart().plusDays(2));
	            return this.getHousing().getPrice(newRange); //Se calcula el precio para dos dias de estadia
	        } else {
	        	return this.getHousing().getPrice(range); //Que pasa aca???
	        }
	}

}
