package engine;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import engine.game.AutoMovingPhysicalElement;
import engine.game.Player;

public class GamePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final File BACKGROUND = new File(Main.RESOURCES + "background.png");

	private Player player;

	private final Image background;

	public GamePanel() throws IOException
	{
		this.background = ImageIO.read(BACKGROUND);
	}

	@Override
	public void paint(final Graphics graphics)
	{
		super.paint(graphics);
		
		if(graphics != null)
		{
			this.drawBackground(graphics);
			this.drawPlayer(graphics);
			this.drawElements(graphics);
		}
	}

	private void drawElements(Graphics graphics)
	{
		List<AutoMovingPhysicalElement> movs = GameWindow.getInstance().getMovingElements();
		
		for(AutoMovingPhysicalElement mov : movs)
		{
			mov.draw(graphics);
		}
		
	}

	private void drawBackground(final Graphics graphics)
	{
		graphics.drawImage(background, 0, 0, background.getWidth(null), background.getHeight(null), null);
	}

	private void drawPlayer(final Graphics graphics)
	{
		if(this.player != null)
		{
			this.player.draw(graphics);
		}
	}

	public void initPlayer() throws IOException
	{
		this.player = new Player();

		try
		{
			this.player.setPosition(GameWindow.WIDTH / 2, GameWindow.HEIGHT / 2);
		}
		catch (InvalidPositionException e)
		{
			System.err.println(e.getMessage());
		}
	}

	public Player getPlayer()
	{
		return this.player;
	}
}
