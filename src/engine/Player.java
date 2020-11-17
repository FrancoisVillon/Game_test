package engine;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.moving.logic.Direction;

public class Player
{
	private static final File PLAYER_TEXTURE = new File(Main.RESOURCES + "icon.png");
	private static final int HITBOX_WIDTH = 16;
	private static final int HITBOX_HEIGHT = 16;

	private final Image texture;

	/**
	 * The hitbox is centered around posX and posZ.
	 */
	private final Rectangle hitbox = new Rectangle(HITBOX_WIDTH, HITBOX_HEIGHT);

	private int posX;
	private int posZ;
	private int updatePositionSpeed = 2;

	public Player() throws IOException
	{
		this.texture = ImageIO.read(PLAYER_TEXTURE);
	}

	/**
	 * Sets the player's position.
	 * 
	 * @param x X coordinate
	 * @param z Z coordinate
	 * @throws InvalidPositionException if the position is invalid
	 */
	public void setPosition(final int x, final int z) throws InvalidPositionException
	{
		if (!this.isPositionValid(x, z))
		{
			throw new InvalidPositionException(x, z);
		}

		else
		{
			this.posX = x;
			this.posZ = z;
			this.hitbox.setLocation(new Point(x - hitbox.width / 2, z - hitbox.height / 2));
		}
	}

	/**
	 * Check if the player is in the window.
	 * 
	 * @param x X coordinate
	 * @param z Z coordinate
	 * @return True if the player is in the window
	 */
	private boolean isPositionValid(final int x, final int z)
	{
		return x - HITBOX_WIDTH / 2 >= 0 && x + HITBOX_WIDTH / 2 <= GameWindow.WIDTH && z - HITBOX_HEIGHT / 2 >= 0
				&& z + HITBOX_HEIGHT / 2 <= GameWindow.HEIGHT;
	}

	public Image getTexture()
	{
		return this.texture;
	}

	public Rectangle getHitbox()
	{
		return this.hitbox;
	}

	/**
	 * @return the posX
	 */
	public int getPosX()
	{
		return posX;
	}

	/**
	 * @return the posZ
	 */
	public int getPosZ()
	{
		return posZ;
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

	public void draw(final Graphics graphics)
	{
		graphics.drawImage(this.texture, this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height, null);
	}

	public void move(final Direction dirX, final Direction dirZ)
	{
		if (dirX != Direction.NONE || dirZ != Direction.NONE)
		{
			final int newX = this.posX + dirX.getSens() * this.updatePositionSpeed;
			final int newZ = this.posZ + dirZ.getSens() * this.updatePositionSpeed;

			try
			{
				this.setPosition(newX, newZ);
				GameWindow.getInstance().getGamePanel().repaint();
			}
			catch (InvalidPositionException e)
			{

			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
