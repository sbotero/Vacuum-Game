package sprites;

public class Dirt extends Sprite {

	protected int value;
	
	/**
	 * A dirt object
	 * 
	 * @param symbol 
	 * @param row
	 * @param column
	 * @param value
	 */
	public Dirt(char dirt, int row, int column, int value) {
		super(dirt, row, column);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
