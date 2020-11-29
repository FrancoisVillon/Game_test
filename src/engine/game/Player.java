package engine.game;

import java.io.File;
import java.io.IOException;

import engine.GameWindow;
import engine.InvalidPositionException;
import engine.Main;

public class Player extends MovingPhysicalElement
{
	private static final File PLAYER_TEXTURE = new File(Main.RESOURCES + "icon.png");

	public Player() throws IOException
	{
		super(PLAYER_TEXTURE, DEFAULT_HITBOX);
	}
	
	public void setPosition(final int x, final int z) throws InvalidPositionException
	{
		super.setPosition(x, z);
		System.out.println(String.format("Player pos %s, %s", x, z));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add()
	{
		GameWindow.getInstance().getPlayers().add(this);
	}
}
