package engine;

import javax.swing.JFrame;

public class Main
{
	public static final String RESOURCES = "resources/";
	
	/**
	 * Duration of a tick in milliseconds.
	 */
	private static final long TICK_TIME = 50;

	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(false);

		// Instantiates the game window.
		GameWindow gameWindow = GameWindow.getInstance();

		daemon(gameWindow);
	}

	/**
	 * Infinite loop that runs the game.
	 */
	private static void daemon(GameWindow gameWindow)
	{
		long time = System.currentTimeMillis();

		while (true)
		{
			if (System.currentTimeMillis() - time > TICK_TIME)
			{
				gameWindow.tick();
				time = System.currentTimeMillis();
			}
		}
	}
}
