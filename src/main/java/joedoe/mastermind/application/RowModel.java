package joedoe.mastermind.application;

import joedoe.mastermind.types.Color;
import joedoe.mastermind.types.Row;

public class RowModel {

	private Color[] colors = new Color[4];
	private int colorPointer = 0;
	
	public void reset() {
		colorPointer = 0;
		colors = new Color[4];
	}
	
	public Row getRow() {
		return new Row(colors);
	}
	
	public Color getColor(int index) {
		return colors[index];
	}
	
	public void addColor(Color color) {
		colors[colorPointer] = color;
		colorPointer++;
	}
	
	public boolean rowFullyDefined() {
		return colorPointer == 4;
	}
	
}
