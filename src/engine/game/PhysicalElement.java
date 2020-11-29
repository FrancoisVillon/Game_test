package engine.game;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import engine.GameWindow;

public class PhysicalElement extends GraphicalElement
{
	private final Rectangle hitbox;
	
	public PhysicalElement(final File texture, final Rectangle graphicalBox, final Rectangle hitbox) throws IOException
	{
		super(texture, graphicalBox);
		this.hitbox = hitbox;
	}
	
	public PhysicalElement(final File texture, final Rectangle graphicalBox) throws IOException
	{
		super(texture, graphicalBox);
		this.hitbox = graphicalBox;
	}
	
	public PhysicalElement() throws IOException
	{
		this.hitbox = this.getGraphicalBox();
	}

	public Rectangle getHitbox()
	{
		return hitbox;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add()
	{
		GameWindow.getInstance().getImmovableElements().add(this);
	}
}
