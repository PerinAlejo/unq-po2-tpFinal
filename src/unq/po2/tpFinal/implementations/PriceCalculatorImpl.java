package unq.po2.tpFinal.implementations;
import java.util.List;

import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.PriceCalculatorInterface;
import unq.po2.tpFinal.domain.PriceForRange;

public class PriceCalculatorImpl implements PriceCalculatorInterface {
    private List<PriceForRange> priceForRanges;

    public PriceCalculatorImpl(List<PriceForRange> priceForRanges) {
        this.priceForRanges = priceForRanges;
    }

    @Override
    public double getPrice(DateRange range) {
    	return this.priceForRanges.stream()
    	.mapToDouble(p -> p.getPriceForRange(range))
    	.sum();
    }
}