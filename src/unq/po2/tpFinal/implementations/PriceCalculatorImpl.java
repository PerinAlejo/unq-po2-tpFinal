package unq.po2.tpFinal.implementations;

import java.util.List;
import java.util.Optional;

import unq.po2.tpFinal.domain.DateRange;
import unq.po2.tpFinal.domain.Housing;
import unq.po2.tpFinal.domain.PriceForRange;
import unq.po2.tpFinal.interfaces.PriceCalculatorInterface;

public class PriceCalculatorImpl implements PriceCalculatorInterface {
	private List<PriceForRange> priceForRanges;

	public PriceCalculatorImpl(List<PriceForRange> priceForRanges) {
		this.priceForRanges = priceForRanges;
	}

	@Override
	public double getPrice(DateRange range) {
		return this.priceForRanges.stream().mapToDouble(p -> p.getPriceForRange(range)).sum();
	}

	@Override
	public void addPrice(PriceForRange newPrice, Housing housing) {
		Optional<PriceForRange> existing =  priceForRanges.stream().filter(price -> price.getRange().equals(newPrice.getRange())).findFirst();
		if(existing.isPresent()) {
			PriceForRange modified = existing.get();
			priceForRanges.remove(modified);
			if(modified.getPrice() > newPrice.getPrice()) {
				housing.priceDropped(newPrice);				
			}
		} 
		priceForRanges.add(newPrice);
	}
	
}