package engine;

import java.io.IOException;

import javax.swing.JFrame;

import engine.game.AutoMovingPhysicalElement;
import engine.game.Player;
import engine.moving.logic.Direction;
import engine.moving.logic.MovingPattern;

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
		
		Player player;

		MovingPattern pattern = MovingPattern.Builder.init()
				.addHorizontal(20L, Direction.LEFT)
				.addVertical(20L, Direction.DOWN)
				.addMovement(20L, Direction.RIGHT, Direction.UP)
				.build();
		
		AutoMovingPhysicalElement mov;
		
		MovingPattern pattern2 = MovingPattern.Builder.init()
				.addVertical(20L, Direction.DOWN)
				.addHorizontal(10L, Direction.LEFT)
				.addMovement(20L, Direction.RIGHT, Direction.UP)
				.addHorizontal(10L, Direction.LEFT)
				.build();
		
		AutoMovingPhysicalElement mov2;
		
		try
		{
			player = new Player();
			player.add();
			
			mov = new AutoMovingPhysicalElement();
			mov.setPattern(pattern);
			mov.add();
			
			mov2 = new AutoMovingPhysicalElement();
			mov2.setPattern(pattern2);
			mov2.setUpdatePositionSpeed(4);
			mov2.add();
			
			player.setPosition(GameWindow.WIDTH / 2, GameWindow.HEIGHT / 2);
			mov.setPosition(GameWindow.WIDTH / 4, GameWindow.HEIGHT / 2);
			mov2.setPosition(3*GameWindow.WIDTH / 4, GameWindow.HEIGHT / 2);
		}
		catch (IOException | InvalidPositionException e)
		{
			System.err.println(e.getMessage());
		}

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
