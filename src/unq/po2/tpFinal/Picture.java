package unq.po2.tpFinal;

public class Picture {
	private String name;
	private String resourceUrl;
	public Picture(String name, String resourceUrl) {	
		this.name = name;
		this.resourceUrl = resourceUrl;
	}
	public String getName() {
		return name;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
}
