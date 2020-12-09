package engine.game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.GameWindow;
import engine.InvalidPositionException;
import engine.Main;

/**
 * All the elements that will be displayed in the game map.
 */
public class GraphicalElement// extends JComponent
{
	protected static final File DEFAULT_TEXTURE = new File(Main.RESOURCES + "default.png");
	protected static final int HITBOX_WIDTH = 16;
	protected static final int HITBOX_HEIGHT = 16;
	
	private final Image texture;

	/**
	 * The hitbox is centered around posX and posZ.
	 */
	private final Rectangle graphicalBox;

	private int posX;
	private int posZ;

	public GraphicalElement(final File texture, final Rectangle graphicalBox) throws IOException
	{
		this.texture = ImageIO.read(texture);
		this.graphicalBox = graphicalBox;
	}

	public GraphicalElement() throws IOException
	{
		this(DEFAULT_TEXTURE, new Rectangle(HITBOX_WIDTH, HITBOX_HEIGHT));
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
			this.graphicalBox.setLocation(new Point(x - graphicalBox.width / 2, z - graphicalBox.height / 2));
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

	public Rectangle getGraphicalBox()
	{
		return this.graphicalBox;
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

	public void paintElement(final Graphics graphics)
	{
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.drawImage(this.texture, this.graphicalBox.x, this.graphicalBox.y, this.graphicalBox.width,
				this.graphicalBox.height, null);
	}
	
	/**
	 * Adds this element to the game.
	 */
	public void add()
	{
		GameWindow.getInstance().getGraphicalElements().add(this);
	}
}
