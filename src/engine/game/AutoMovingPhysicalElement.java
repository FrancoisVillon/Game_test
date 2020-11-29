package engine.game;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import engine.GameWindow;
import engine.moving.logic.MovingPattern;

public class AutoMovingPhysicalElement extends MovingPhysicalElement
{
	/**
	 * The moving pattern of this element.
	 */
	private MovingPattern pattern;
	
	public AutoMovingPhysicalElement(File texture, Rectangle graphicalBox, Rectangle hitbox) throws IOException
	{
		super(texture, graphicalBox, hitbox);
	}
	
	public AutoMovingPhysicalElement(final File texture, final Rectangle graphicalBox) throws IOException
	{
		super(texture, graphicalBox, graphicalBox);
	}
	
	public AutoMovingPhysicalElement() throws IOException
	{
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add()
	{
		GameWindow.getInstance().getMovingElements().add(this);
	}

	public MovingPattern getPattern()
	{
		return pattern;
	}

	public void setPattern(MovingPattern pattern)
	{
		this.pattern = pattern;
	}
	
	
}
