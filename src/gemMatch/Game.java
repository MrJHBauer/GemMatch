package gemMatch;

import processing.core.PApplet;
import processing.core.PImage;

public class Game extends PApplet {

	PImage background;
	
	int nRow = 8;
	int nCol = 8;
	Gem[][] gems = new Gem[nRow][nCol];
	
	Gem selectedGemA = null;
	Gem selectedGemB = null;
	int rA, cA, rB, cB = 0;	
	
	int score = 0;
	
	public void settings() {
		size(640, 480);
	}
	
	public void setup() {
		background = loadImage("data/purple.png");
		
		for(int row = 0; row < nRow; row++) {
			for(int col = 0; col < nCol; col++) {
				gems[row][col] = new Gem(
						width / 2 - (nCol * 32) / 2 + col * 32,
						height / 2 - (nRow * 32) / 2 + row * 32,
						GemProperties.values()[(int)random(0, GemProperties.values().length)],
						this);
			}
		}		
	}
	
	public void identifyCombos() {
		for(int row = 0; row < nRow; row++) {
			for(int col = 0; col < nCol; col++) {
				if(row <= nRow - 3 && gems[row][col].isAlive) {
					Gem currentGem = gems[row][col];
					if(currentGem.properties == gems[row + 1][col].properties && 
							currentGem.properties == gems[row + 2][col].properties) {
						for(int i = 0; i <= (nRow - row) - 1; i++) {
							if(currentGem.properties == gems[row + i][col].properties) {
								gems[row + i][col].isAlive = false;
								score += 10;
							} else {
								break;
							}
						}
					}
				}
				if(col <= nCol - 3 && gems[row][col].isAlive) {
					Gem currentGem = gems[row][col];
					if(currentGem.properties == gems[row][col + 1].properties && 
							currentGem.properties == gems[row][col + 2].properties) {
						for(int i = 0; i <= (nCol - col) - 1; i++) {
							if(currentGem.properties == gems[row][col + i].properties) {
								gems[row][col + i].isAlive = false;
								score += 10;
							} else {
								break;
							}
						}
					}
				}
			}
		}
	}
	
	public void update() {
		if(selectedGemB != null) {
			swapGems();	
			identifyCombos();
			addNewGems();
		}
	}
	
	public void addNewGems() {
		for(int row = 0; row < nRow; row++) {
			for(int col = 0; col < nCol; col++) {
				if(!gems[row][col].isAlive) {
					gems[row][col] = new Gem(
							width / 2 - (nCol * 32) / 2 + col * 32,
							height / 2 - (nRow * 32) / 2 + row * 32,
							GemProperties.values()[(int)random(0, GemProperties.values().length)],
							this);
							
				}
			}
		}
	}
	
	public void swapGems() {
		gems[rA][cA] = new Gem(
				width / 2 - (nCol * 32) / 2 + cA * 32,
				height / 2 - (nRow * 32) / 2 + rA * 32,
				selectedGemB.properties,
				this);
		gems[rB][cB] = new Gem(
				width / 2 - (nCol * 32) / 2 + cB * 32,
				height / 2 - (nRow * 32) / 2 + rB * 32,
				selectedGemA.properties,
				this);
		selectedGemA = selectedGemB = null;
	}
	
	public void mouseReleased() {
		for(int row = 0; row < nRow; row++) {
			for(int col = 0; col < nCol; col++) {
				if(gems[row][col].isMouseOver()) {
					if(selectedGemB == null) {
						if(selectedGemA != null) {
							rB = row;
							cB = col;
							if(isNeighbours()) {							
								selectedGemB = gems[row][col];								
							}
						} else {
							selectedGemA = gems[row][col];
							rA = row;
							cA = col;
						}
					}
				}
			}
		}
	}
	
	public boolean isNeighbours() {
		int rowDelta = Math.abs(rA - rB);
		int colDelta = Math.abs(cA - cB);
		
		if(rowDelta <= 1 && colDelta <= 1) {
			return rowDelta < 1 ? true : colDelta < 1 ? true : false;
		}
		return false;
	}
	
	public void draw() {
		update();
		
		for(int i = 0; i < Math.ceil((double) width / background.width); i++) {
			for(int j = 0; j < Math.ceil((double) height / background.height); j++) {
				image(background, i * background.width, j * background.height);
			}
 		}
		
		for(int row = 0; row < nRow; row++) {
			for(int col = 0; col < nCol; col++) {
				gems[row][col].draw();
			}
		}
		
		text("score: " + score, 50, 50);
		
	}
	
	public static void main(String[] args) {
		PApplet.main("gemMatch.Game");
	}

}
