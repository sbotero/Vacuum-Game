package sprites;

public abstract class Sprite {

	protected char symbol;
	protected int row, column;
	
	public Sprite(char symbol, int row, int column) {
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}
	
	public char getSymbol() {
		return symbol;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
	public String toString() {
		return "" + getSymbol();
	}

}
