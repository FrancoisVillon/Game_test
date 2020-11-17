package engine.moving.logic;

public class KeyController
{
	private int lastAxisXKeyPressed = GameControlKeys.NO_KEY;
	private int lastAxisZKeyPressed = GameControlKeys.NO_KEY;
	private int lastActionKeyPressed = GameControlKeys.NO_KEY;

	public int getLastAxisXKeyPressed()
	{
		return lastAxisXKeyPressed;
	}

	public int getLastAxisZKeyPressed()
	{
		return lastAxisZKeyPressed;
	}

	public int getLastActionKeyPressed()
	{
		return lastActionKeyPressed;
	}
	
	public void updateLastKeyPressed(final int lastKeyPressed)
	{
		if(GameControlKeys.isXAxisDirectionKey(lastKeyPressed))
		{
			this.lastAxisXKeyPressed = lastKeyPressed;
		}
		
		else if(GameControlKeys.isZAxisDirectionKey(lastKeyPressed))
		{
			this.lastAxisZKeyPressed = lastKeyPressed;
		}
		
		else if(GameControlKeys.isActionKey(lastKeyPressed))
		{
			this.lastActionKeyPressed = lastKeyPressed;
		}
	}
}
