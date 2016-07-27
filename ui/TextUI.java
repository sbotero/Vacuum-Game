package ui;

import java.util.Scanner;


import game.VacuumGame;

public class TextUI implements UI {
	
	private VacuumGame game;
	public TextUI(VacuumGame game){
		this.game = game;

	}
	
	@Override
	public void launchGame() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(game.getGrid());
			
		
		while (!(game.gameOver())) {
			game.move(sc.next().charAt(0));
			System.out.println(game.getGrid());
			
		}
		
		displayWinner();
		sc.close();
	}

	@Override
	public void displayWinner() {
		if (game.getWinner() == 1) {
			System.out.println("PLAYER 1 WINS!!!!");
		} else {
			System.out.println("PLAYER 2 WINS!!!!");
		}
	}

}
