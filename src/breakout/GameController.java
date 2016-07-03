package breakout;

import javax.swing.JOptionPane;

public class GameController {
	static int overallScore = 0;

	public static void main(String args[]) {
		Level m = new Level();
		while (true) {
			if (endGameFail()) {
				if (playAgain() == JOptionPane.NO_OPTION)
					System.exit(0);
				m.resetAfterFail();
				m.beginLevel();
			} else if (endGameSuccess()) {
				endLevel();
				m.resetAfterWin();
				m.beginLevel();
			}
		}
	}

	public static boolean endGameFail() {
		if (Ball.gameOver)
			return true;
		return false;
	}

	public static boolean endGameSuccess() {
		if (overallScore % Level.NUMBER_OF_BRICKS == 0 && overallScore > 0)
			return true;
		return false;
	}

	public static int playAgain() {
		Sound.GAMEOVER.play();
		return JOptionPane.showConfirmDialog(null, "Twój wynik to: " + overallScore + "\nCzy chcesz zagraæ ponownie",
				"Koniec gry", JOptionPane.YES_NO_OPTION);
	}

	public static void endLevel() {
		Sound.WIN.play();
		JOptionPane.showMessageDialog(null, "Gratulacje! \nPrzejdz do kolejnego poziomu", "Sukces",
				JOptionPane.OK_CANCEL_OPTION);
	}

}
