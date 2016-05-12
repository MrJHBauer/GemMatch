package gemMatch;

import processing.core.PApplet;
import processing.core.PImage;

public class Gem {

	PApplet parent;
	
	int x;
	
	int y;
	
	PImage texture;
	
	GemProperties properties;

	boolean isAlive = true;
	
	public Gem(int x, int y, GemProperties properties, PApplet parent) {
		this.x = x;
		this.y = y;
		this.properties = properties;
		this.parent = parent;
		
		texture = parent.loadImage(properties.GetURL());
		
	}
	
	public boolean isMouseOver() {	
		if(parent.mouseX < x + texture.width && parent.mouseX > x &&
				parent.mouseY < y + texture.height && parent.mouseY > y) {
			return true;
		}
		return false;
	}
	
	public void draw() {
		if(isAlive) {
			parent.image(texture, x, y);
		}		
	}
	
	
}
