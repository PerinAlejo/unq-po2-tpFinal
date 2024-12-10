package unq.po2.tpFinal.interfaces;

import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.PriceForRange;

public interface PriceCalculatorInterface {
	double getPrice(DateRange range);
	void addPrice(PriceForRange newPrice, Housing housing);
}
