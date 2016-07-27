package sprites;

public class DustBall extends Dirt implements Moveable {

	public DustBall(char dirt, int row, int column, int value) {
		super(dirt, row, column, value);
	}

	@Override
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}

}
