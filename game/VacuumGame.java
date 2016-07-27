package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

    // a random number generator to move the DustBalls
    private Random random;

    // the grid
    private Grid<Sprite> grid;

    // the first player
    private Vacuum vacuum1;

    /// the second player
    private Vacuum vacuum2;

	// the dirt (both static dirt and mobile dust balls)
    private List<Dirt> dirts;

    // the dumpsters
    private List<Dumpster> dumpsters;

    /**
     * Creates a new VacuumGame that corresponds to the given input text file.
     * Assumes that the input file has one or more lines of equal lengths, and
     * that each character in it (other than newline) is a character that 
     * represents one of the sprites in this game.
     * @param layoutFileName path to the input grid file
     */
    public VacuumGame(String layoutFileName) throws IOException {
        this.dirts = new ArrayList<Dirt>();
        this.dumpsters = new ArrayList<Dumpster>(); // Jen: may not need this
        this.random = new Random();

        // open the file, read the contents, and determine 
        // dimensions of the grid
        int[] dimensions = getDimensions(layoutFileName);
        this.grid = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);

        // open the file again, read the contents, and store them in grid
        Scanner sc = new Scanner(new File(layoutFileName));

	// INITIALIZE THE GRID HERE
        
        
        //Loops through scanned copy of file and great array on grid according
        // to object in text with appropriate dimensions
        for (int i=0; i < dimensions[0]; i++) {
        	String line = sc.nextLine();
        	for (int j=0; j < dimensions[1]; j++) {
        		switch (line.charAt(j)) {
        		// Create appropriate sprite object for each character in file
        		// (and add to lists accordingly if necessary)
        		// Use constants for values
        		case Constants.CLEAN:
        			grid.setCell(i, j, new CleanHallway(Constants.CLEAN,
        					i, j));
        			break;
        		case Constants.DIRT:
        			Dirt dirt = new Dirt(Constants.DIRT, i, j,
        					Constants.DIRT_SCORE);
        			grid.setCell(i, j, dirt);
        			dirts.add(dirt);
        			break;
        		case Constants.DUMPSTER:
        			grid.setCell(i, j, new Dumpster(Constants.DUMPSTER, i, j));
        			break;
        		case Constants.DUST_BALL:
        			DustBall dustBall = new DustBall(Constants.DUST_BALL, i, j,
        					Constants.DUST_BALL_SCORE);
        			grid.setCell(i, j, dustBall);
        			dirts.add(dustBall);
        			break;
        		case Constants.WALL:
        			grid.setCell(i, j, new Wall(Constants.WALL, i, j));
        			break;
        		case Constants.P1:
        			vacuum1 = new Vacuum(Constants.P1, i, j,
        					Constants.CAPACITY);
        			grid.setCell(i, j, vacuum1);
        			vacuum1.setUnder(new CleanHallway(Constants.CLEAN, i, j));
        			break;
        		case Constants.P2:
        			vacuum2 = new Vacuum(Constants.P2, i, j,
        					Constants.CAPACITY);
        			grid.setCell(i, j, vacuum2);
        			vacuum2.setUnder(new CleanHallway(Constants.CLEAN, i, j));
        			break;
        		}
        		
        	}
        }

        sc.close();
    }


    /**
     * Returns the dimensions of the grid in the file named layoutFileName.
     * @param layoutFileName path of the input grid file
     * @return an array [numRows, numCols], where numRows is the number
     * of rows and numCols is the number of columns in the grid that
     * corresponds to the given input grid file
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {       

        Scanner sc = new Scanner(new File(layoutFileName));

        // find the number of columns
        String nextLine = sc.nextLine();
        int numCols = nextLine.length();

        int numRows = 1;

        // find the number of rows
        while (sc.hasNext()) {
            numRows++;
            nextLine = sc.nextLine();
        }

        sc.close();
        return new int[]{numRows, numCols};
    }
    
    /**
     * Returns true if vacuum is able to move to the next
     * sprite indicated by nextMove.  Initiates the
     * move methods for vacuum sprite and dustball sprite.
     * 
     * @param nextMove a character that indicates the next move of the vacuum
     * @return true if vacuum is able to move
     */
    public boolean move(char nextMove) {
    	// move to indicate whether or not vacuum is
    	// successful in moving and moveDustBall to indicate if
    	// correct key pressed to move DustBalls even, if move is false
    	boolean move = false;
    	boolean moveDustBall = false;
    	switch (nextMove) {
    	// Check every move to see if the move is valid
    	// If it is, move the vacuum accordingly
    	case Constants.P1_DOWN:
    		if (validVacuumMove(nextMove, vacuum1)){
    			moveVacuum(vacuum1, vacuum1.getRow()
    					+ Constants.DOWN, vacuum1.getColumn());
    			move = true;
    		}
    		moveDustBall = true;
    		break;
    	case Constants.P1_LEFT:
    		if (validVacuumMove(nextMove, vacuum1)){
    			moveVacuum(vacuum1, vacuum1.getRow(),
    					vacuum1.getColumn() + Constants.LEFT);
    			move = true;
    		}
    		moveDustBall = true;
    		break;
    	case Constants.P1_RIGHT:
    		if (validVacuumMove(nextMove, vacuum1)){
    			moveVacuum(vacuum1, vacuum1.getRow(),
    					vacuum1.getColumn() + Constants.RIGHT);
    			move = true;
    		}
    		moveDustBall = true;
    		break;
    	case Constants.P1_UP:
    		if (validVacuumMove(nextMove, vacuum1)){
    			moveVacuum(vacuum1, vacuum1.getRow()
    					+ Constants.UP, vacuum1.getColumn());
    			move = true;
    		}
    		moveDustBall = true;
    		break;
    	case Constants.P2_DOWN:
    		if (validVacuumMove(nextMove, vacuum2)){
    			moveVacuum(vacuum2, vacuum2.getRow()
    					+ Constants.DOWN, vacuum2.getColumn());
    			move = true;
    		}
    		moveDustBall = true;
    		break;
    	case Constants.P2_LEFT:
    		if (validVacuumMove(nextMove, vacuum2)){
    			moveVacuum(vacuum2, vacuum2.getRow(),
    					vacuum2.getColumn() + Constants.LEFT);
    			move = true;
    		}
    		moveDustBall = true;
    		break;
    	case Constants.P2_RIGHT:
    		if (validVacuumMove(nextMove, vacuum2)){
    			moveVacuum(vacuum2, vacuum2.getRow(),
    					vacuum2.getColumn() + Constants.RIGHT);
    			move = true;
    		}
    		moveDustBall = true;
    		break;
    	case Constants.P2_UP:
    		if (validVacuumMove(nextMove, vacuum2)){
    			moveVacuum(vacuum2, vacuum2.getRow()
    					+ Constants.UP, vacuum2.getColumn());
    			move = true;
    		}
    		moveDustBall = true;
    		break;
    	}
    	// If a valid key is pressed, DustBalls must move
    	// Find every DustBall in list and make sure the move is valid
    	if (moveDustBall){
        	for (int i = 0; i < dirts.size(); i++) {
        		if (dirts.get(i).getSymbol() == Constants.DUST_BALL) {
        			forceValidDustBallMove((DustBall)dirts.get(i), random.nextInt(8));
        		}
        	}
    	}

    	return move;
    }
    
    /**
     * Moves the sprite Vacuum to new coordinate row and column and 
     * depending on where the sprite Vacuum lands on, the appropriate
     * behavior is expressed.
     * 
     * @param vacuum a Vacuum sprite that needs to be moved
     * @param row an int that represents the new row for Vacuum
     * @param column an int that represents the new column for Vacuum
     */
    private void moveVacuum(Vacuum vacuum, int row, int column) {
    	
    	Sprite next = grid.getCell(row, column);
    	
		switch (next.getSymbol()) {
		// Set new cell to vacuum -> set old cell to what was under the vacuum
		// -> save internal coordinates of vacuum -> set Dumpster under it
		// -> empty vacuum
		case Constants.DUMPSTER:
			grid.setCell(row, column, vacuum);
			grid.setCell(vacuum.getRow(), vacuum.getColumn(), vacuum.getUnder());
			vacuum.moveTo(row, column);
			vacuum.setUnder(next);
			vacuum.empty();
			break;
		// Set new cell to vacuum -> set old cell to what was under the vacuum
		// -> save internal coordinates of vacuum -> set CleanHallway under it
		case Constants.CLEAN:
			grid.setCell(row, column, vacuum);
			grid.setCell(vacuum.getRow(), vacuum.getColumn(), vacuum.getUnder());
			vacuum.moveTo(row, column);
			vacuum.setUnder(next);
			break;
		// Set new cell to vacuum -> set old cell to what was under the vacuum
		// -> save internal coordinates of vacuum -> set Dirt under it
		// -> if the vacuum can clean, clean will clean -> delete dirt from
		// list -> if cant clean, do nothing
		case Constants.DIRT:
			grid.setCell(row, column, vacuum);
			grid.setCell(vacuum.getRow(), vacuum.getColumn(), vacuum.getUnder());
			vacuum.moveTo(row, column);
			vacuum.setUnder(next);
			if (vacuum.clean(((Dirt) next).getValue())) {
				dirts.remove(next);
			}
			break;
		// Set new cell to vacuum -> set old cell to what was under the vacuum
		// -> save internal coordinates of vacuum -> set DustBall under it
		// -> if the vacuum can clean, clean will clean -> delete dirt from
		// list -> if cant clean, do nothing
		case Constants.DUST_BALL:
			grid.setCell(row, column, vacuum);
			grid.setCell(vacuum.getRow(), vacuum.getColumn(), vacuum.getUnder());
			vacuum.moveTo(row, column);
			vacuum.setUnder(next);
			if (vacuum.clean(((DustBall) next).getValue())) {
				dirts.remove(next);
			}
		}
    }
    
    /**
     * Moves DustBall and forces a valid move for DustBall if it 
     * can't move to original direction
     * 
     * @param dustBall a DustBall that needs to be moved
     * @param direction an int from 0 to 7 that represents a direction
     */
    private void forceValidDustBallMove(DustBall dustBall, int direction) {
    	//get row and column for current location of dustball
    	int row = dustBall.getRow();
    	int column = dustBall.getColumn();
    	
    	// direction 0 to 7 to make more random
    	// check if direction is valid, if not force a valid move
    	switch (direction) {
    	case 0:
    	case 6:
    		row += Constants.UP;
    		if (validDustBallMove(dustBall, "UP")) {
    			moveDustBall(dustBall, row, column);
    			
    		} else {
    			forceValidDustBallMove(dustBall, random.nextInt(8));
    		}
    		break;
    	case 1:
    	case 7:
    		row += Constants.DOWN;
    		if (validDustBallMove(dustBall, "DOWN")) {
    			moveDustBall(dustBall, row, column);
    		} else {
    			forceValidDustBallMove(dustBall, random.nextInt(8));
    		}
    		break;
    	case 2:
    	case 4:
    		column += Constants.LEFT;
    		if (validDustBallMove(dustBall, "LEFT")) {
    			moveDustBall(dustBall, row, column);
    		} else {
    			forceValidDustBallMove(dustBall, random.nextInt(8));
    		}
    		break;
    	case 3:
    	case 5:
    		column += Constants.RIGHT;
    		if (validDustBallMove(dustBall, "RIGHT")) {
    			moveDustBall(dustBall, row, column);
    		} else {
    			forceValidDustBallMove(dustBall, random.nextInt(8));
    		}
    		break;
    		}
    		
    	
    	
    	
    }
    
    /**
     * 
     * @param dustBall a DustBall that needs to be moved
     * @param row an int representing new row
     * @param column an int representing new column
     */
    
    private void moveDustBall(DustBall dustBall, int row, int column){
    	
    	Sprite next = grid.getCell(row, column);
		Dirt dirt = new Dirt(Constants.DIRT,dustBall.getRow(),
				dustBall.getColumn(), Constants.DIRT_SCORE);
		Sprite currentPosition = grid.getCell(dustBall.getRow(),
				dustBall.getColumn());

    	
    	switch (next.getSymbol()) {
		case Constants.DIRT:
			grid.setCell(row, column, dustBall);
			if (!(currentPosition.getSymbol() 
					== Constants.P1
					|| currentPosition.getSymbol() == Constants.P2)){
				grid.setCell(dustBall.getRow(), dustBall.getColumn(), dirt);
			} else {
				switch (currentPosition.getSymbol()) {
				case Constants.P1:
					vacuum1.setUnder(dirt);
					break;
				case Constants.P2:
					vacuum2.setUnder(dirt);
					break;
				}
			}
			dirts.remove(next);
	    	dustBall.moveTo(row, column);
	    	dirts.add(dirt);
			break;
		case Constants.CLEAN:
			grid.setCell(row, column, dustBall);
			if (!(currentPosition.getSymbol() 
					== Constants.P1
					|| currentPosition.getSymbol() == Constants.P2)){
			grid.setCell(dustBall.getRow(), dustBall.getColumn(), dirt);
			} else {
				switch (currentPosition.getSymbol()) {
				case Constants.P1:
					vacuum1.setUnder(dirt);
					break;
				case Constants.P2:
					vacuum2.setUnder(dirt);
					break;
				}
			}
	    	dustBall.moveTo(row, column);
	    	dirts.add(dirt);
			break;
		case Constants.DUST_BALL:
			grid.setCell(row, column, dustBall);
			grid.setCell(dustBall.getRow(), dustBall.getColumn(), dirt);
			dirts.remove(next);
	    	dustBall.moveTo(row, column);
	    	dirts.add(dirt);
			break;
    	}

    	
    }
    
    
    
    public Grid<Sprite> getGrid() {
    	return grid;
    }
    
    public Vacuum getVacuumOne() {
		return vacuum1;
	}


	public Vacuum getVacuumTwo() {
		return vacuum2;
	}
	
	public int getNumRows(){
		return grid.getNumRows();
	}
	
	public int getNumColumns() {
		return grid.getNumColumns();
	}
	
	public Sprite getSprite(int i, int j) {
		return grid.getCell(i, j);
	}
	
	public boolean gameOver() {
		return dirts.isEmpty();
	}
	
	public int getWinner() {
		if (vacuum1.getScore() > vacuum2.getScore()) {
			return 1;
		} else {
			return 2;
		}
	}
	
	private boolean validDustBallMove(DustBall dustBall, String direction) {
		boolean valid = true;
		int row = dustBall.getRow();
		int column = dustBall.getColumn();
		Sprite moveToCell;
		
		switch (direction) {
		case "UP":
			moveToCell = grid.getCell(row + Constants.UP,
					column);
			if (moveToCell.getSymbol() == Constants.WALL 
					|| moveToCell.getSymbol() == Constants.DUMPSTER) {
				valid = false;
			} 
			break;
		case "DOWN":
			moveToCell = grid.getCell(row + Constants.DOWN,
					column);
			if (moveToCell.getSymbol() == Constants.WALL 
					|| moveToCell.getSymbol() == Constants.DUMPSTER) {
				valid = false;
			} 
			break;
		case "LEFT":
			moveToCell = grid.getCell(row,
					column  + Constants.LEFT);
			if (moveToCell.getSymbol() == Constants.WALL 
					|| moveToCell.getSymbol() == Constants.DUMPSTER) {
				valid = false;
			} 
			break;
		case "RIGHT":
			moveToCell = grid.getCell(row,
					column  + Constants.RIGHT);
			if (moveToCell.getSymbol() == Constants.WALL 
					|| moveToCell.getSymbol() == Constants.DUMPSTER) {
				valid = false;
			} 
			break;
		}
		return valid;
	}
	
	private boolean validVacuumMove(char nextMove, Sprite sprite) {
		
		boolean valid = true;
		int row = sprite.getRow();
		int column = sprite.getColumn();
		Sprite moveToCell;
		
		switch (nextMove) {
		case Constants.P1_DOWN:
		case Constants.P2_DOWN:
			moveToCell = grid.getCell(row + Constants.DOWN,
					column);
			if (moveToCell.getSymbol() == Constants.WALL) {
				valid = false;
				
			} else if (moveToCell.getSymbol() == Constants.P1
					|| moveToCell.getSymbol() == Constants.P2) {
				valid = false;
			}
			break;
		case Constants.P1_LEFT:
		case Constants.P2_LEFT:
			moveToCell = grid.getCell(row,
					column  + Constants.LEFT);
			if (moveToCell.getSymbol() == Constants.WALL) {
				valid = false;
				
			} else if (moveToCell.getSymbol() == Constants.P1
					|| moveToCell.getSymbol() == Constants.P2) {
				valid = false;
			} 
			break;
		case Constants.P1_RIGHT:
		case Constants.P2_RIGHT:
			moveToCell = grid.getCell(row,
					column  + Constants.RIGHT);
			if (moveToCell.getSymbol() == Constants.WALL) {
				valid = false;
				
			} else if (moveToCell.getSymbol() == Constants.P1
					|| moveToCell.getSymbol() == Constants.P2) {
				valid = false;
			} 
			break;
		case Constants.P1_UP:
		case Constants.P2_UP:
			moveToCell = grid.getCell(row + Constants.UP,
					column);
			if (moveToCell.getSymbol() == Constants.WALL) {
				valid = false;
				
			} else if (moveToCell.getSymbol() == Constants.P1
					|| moveToCell.getSymbol() == Constants.P2) {
				valid = false;
			}
			break;
						
			
		}
		
		return valid;
	}
	
}
