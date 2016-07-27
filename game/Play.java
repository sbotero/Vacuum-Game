package game;

import java.io.IOException;

import ui.GUI;
import ui.TextUI;
import ui.UI;

public class Play {

	public static void main(String[] args) throws IOException {
    
		VacuumGame game = new VacuumGame(Constants.FILENAME);
		//System.out.println(game.getMaze().toString());
		UI gameUI;

		if (Constants.UI_TYPE.equals("text")) {
			gameUI = new TextUI(game);
		} else {
			gameUI = new GUI(game);
		}

		gameUI.launchGame(); 
		gameUI.displayWinner();
	}
}
