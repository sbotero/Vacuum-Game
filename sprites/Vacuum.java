package sprites;
import game.Constants;

public class Vacuum extends Sprite implements Moveable {
	
	private int score, capacity, fullness;
	private Sprite under;
	
	public Vacuum(char symbol, int row, int column, int capacity) {
		super(symbol, row, column);
		this.capacity = capacity;
		score = Constants.INIT_SCORE;
	}
	
	public boolean clean(int score){
		boolean canClean = !(fullness == capacity);
		boolean isDirty = (under.symbol == Constants.DIRT
				|| under.symbol == Constants.DUST_BALL);
		
		if (canClean && isDirty) {
			fullness += Constants.FULLNESS_INC;
			this.score += score;
			under = new CleanHallway(Constants.CLEAN, row, column);
		}
		
		return canClean;
	}
	
	public void empty() {
		fullness = 0;
	}
	
	public Sprite getUnder() {
		return under;
	}

	public void setUnder(Sprite under) {
		this.under = under;
	}

	public int getScore() {
		return score;
	}

	@Override
	public void moveTo(int row, int column) {
		this.row = row;
		this.column = column;
	}

}
