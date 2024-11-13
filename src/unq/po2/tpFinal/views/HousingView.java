package unq.po2.tpFinal.views;

import java.util.List;
import java.util.stream.Collectors;

import unq.po2.tpFinal.domain.CategoryScore;
import unq.po2.tpFinal.domain.Housing;

public class HousingView {
	private Housing housing;

	public HousingView(Housing housing) {
		this.housing = housing;
	}

	public List<CommentView> comments() {
		return this.housing.getRankings().stream().map(ranking -> CommentView.fromRanking(ranking)).toList();
	}

	public List<CategoryAverageView> categoryAverages() {
		return this.housing.getRankings().stream().flatMap(ranking -> ranking.getScores().stream())
				.collect(Collectors.groupingBy(CategoryScore::getCategory)).entrySet().stream()
				.map(categoryGroup -> new CategoryAverageView(
						categoryGroup.getValue().stream().mapToDouble(CategoryScore::getScore).sum(),
						categoryGroup.getKey()))
				.toList();
	}

	public OwnerView ownerDetails() {
		return new OwnerView(housing.getOwner(), housing);
	}
}
