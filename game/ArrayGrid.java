package game;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Vacuum;
import sprites.Wall;

public class ArrayGrid<T> implements Grid<T> {
	
	private int numRows;
	private int numColumns;
	private T[][] grid;
	
	
	@SuppressWarnings("unchecked")
	public ArrayGrid(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		grid = (T[][]) new Object[numRows][numColumns];
		
	}

	@Override
	public void setCell(int row, int column, T item) {
		grid[row][column] = item;
	}

	@Override
	public T getCell(int row, int column) {

		return grid[row][column];
	}

	@Override
	public int getNumRows() {

		return numRows;
	}

	@Override
	public int getNumColumns() {
		
		return numColumns;
	}
	
	@Override
	public String toString() {
		String gridText = "";
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				gridText += this.grid[i][j];
			}
			gridText += "\n";
		}
		return gridText;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object other) {
		boolean equals = true;
		if (!(this.getClass() == other.getClass())) {
			equals = false;
		} else if (!(this.numRows == ((ArrayGrid) other).getNumRows() 
				&& this.numColumns == ((ArrayGrid) other).getNumColumns())) {
			equals = false;
		} else {
			
			for (int i=0; i < this.numRows; i++) {
	        	for (int j=0; j < this.numColumns; j++) {
//	        		if (grid[i][j].equals(other[i][j]) {
//	        			
//	        		}
	        	}
	        }
			
		}
		
		return equals;
	}

}
