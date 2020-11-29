package engine.game;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import engine.InvalidPositionException;
import engine.moving.logic.Direction;

public abstract class MovingPhysicalElement extends PhysicalElement
{
	private int updatePositionSpeed = 2;
	private long ticksExisted;
	
	public MovingPhysicalElement(File texture, Rectangle graphicalBox, Rectangle hitbox) throws IOException
	{
		super(texture, graphicalBox, hitbox);
	}
	
	public MovingPhysicalElement(final File texture, final Rectangle graphicalBox) throws IOException
	{
		super(texture, graphicalBox, graphicalBox);
	}
	
	public MovingPhysicalElement() throws IOException
	{
		this(DEFAULT_TEXTURE, DEFAULT_HITBOX);
	}
	
	/**
	 * @return the updatePositionSpeed
	 */
	public int getUpdatePositionSpeed()
	{
		return updatePositionSpeed;
	}

	/**
	 * @param updatePositionSpeed the updatePositionSpeed to set
	 */
	public void setUpdatePositionSpeed(int updatePositionSpeed)
	{
		this.updatePositionSpeed = updatePositionSpeed;
	}
	
	public void move(final Direction dirX, final Direction dirZ)
	{
		if (dirX != Direction.NONE || dirZ != Direction.NONE)
		{
			final int newX = this.getPosX() + dirX.getSens() * this.updatePositionSpeed;
			final int newZ = this.getPosZ() + dirZ.getSens() * this.updatePositionSpeed;

			try
			{
				this.setPosition(newX, newZ);
//				GameWindow.getInstance().getGamePanel().repaint();
			}
			catch (InvalidPositionException e)
			{

			}
		}
	}

	public long getTicksExisted()
	{
		return ticksExisted;
	}

	public void incrementTick()
	{
		this.ticksExisted++;
	}
}
