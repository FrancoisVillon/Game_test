package engine.moving.logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import engine.GameWindow;

public class GameControlKeyListener implements KeyListener
{
	private final Set<Integer> keysPressed = new HashSet<>();

	@Override
	public void keyPressed(final KeyEvent event)
	{
		final int key = event.getKeyCode();

		this.keysPressed.add(key);		
		try
		{
			GameWindow.getInstance().updatePlayerPosition(key);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void keyReleased(final KeyEvent event)
	{
		this.keysPressed.remove(event.getKeyCode());
		//GameWindow.getInstance().updatePlayerPosition(GameControlKeys.NO_KEY);
	}

	@Override
	public void keyTyped(final KeyEvent event)
	{
	}
	
	/**
	 * Checks if a key is pressed.
	 * @param keyCode The key to check
	 * @return true if the key is pressed, false otherwise
	 */
	public boolean isKeyPressed(final int keyCode)
	{
		return this.keysPressed.contains(keyCode);
	}
	
	public Set<Integer> getAllKeysPressed()
	{
		System.out.println("[");
		for(int key : this.keysPressed)
		{
			this.displayKeyName(key);
		}
		System.out.println("]");
		return this.keysPressed;
	}

	private void displayKeyName(int key)
	{
		switch (key)
		{
			case GameControlKeys.DOWN:
				System.out.println("Down");
				break;
			case GameControlKeys.UP:
				System.out.println("Up");
				break;
			case GameControlKeys.LEFT:
				System.out.println("Left");
				break;
			case GameControlKeys.RIGHT:
				System.out.println("Right");
				break;
			default:
				System.out.println("Other key pressed");
				break;
		}
	}
}
