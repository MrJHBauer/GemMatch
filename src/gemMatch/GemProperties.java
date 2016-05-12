package gemMatch;

public enum GemProperties {
	BLUE("blue"),
	GREEN("green"),
	GREY("grey"),
	PURPLE("purple"),
	RED("red"),
	YELLOW("yellow");
	
	private String COLOUR;
	
	GemProperties(String COLOUR) {
		this.COLOUR = COLOUR;
	}
	
	public String GetURL() {	
		return "data/gems/element_" + COLOUR + "_square.png";
	}
	
}
