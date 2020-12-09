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

	private final Image background;

	public GamePanel() throws IOException
	{
		this.background = ImageIO.read(BACKGROUND);
	}

	@Override
	public void paint(final Graphics graphics)
	{
		if(graphics != null)
		{
			super.paint(graphics);
			
			this.drawBackground(graphics);
			this.drawPlayers(graphics);
			this.drawElements(graphics);
		}
	}
	
	@Override
	public void repaint()
	{
		final Graphics graphics = this.getGraphics();
		if(graphics != null)
		{
			super.paint(graphics);
			
			this.drawBackground(graphics);
			this.drawPlayers(graphics);
			this.drawElements(graphics);
		}
	}

	private void drawElements(Graphics graphics)
	{
		List<AutoMovingPhysicalElement> movs = GameWindow.getInstance().getMovingElements();
		
		for(AutoMovingPhysicalElement mov : movs)
		{
			mov.paintElement(graphics);
		}
		
	}

	private void drawBackground(final Graphics graphics)
	{
		graphics.drawImage(background, 0, 0, background.getWidth(null), background.getHeight(null), null);
	}

	private void drawPlayers(final Graphics graphics)
	{
		List<Player> players = GameWindow.getInstance().getPlayers();
		
		for(Player player : players)
		{
			player.paintElement(graphics);
		}
	}
}
