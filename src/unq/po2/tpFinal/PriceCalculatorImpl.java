package unq.po2.tpFinal;
import java.util.List;

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