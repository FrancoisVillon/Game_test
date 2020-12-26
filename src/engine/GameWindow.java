package engine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import engine.game.AutoMovingPhysicalElement;
import engine.game.GraphicalElement;
import engine.game.PhysicalElement;
import engine.game.Player;
import engine.moving.logic.Direction;
import engine.moving.logic.DirectionTuple;
import engine.moving.logic.GameControlKeyListener;
import engine.moving.logic.KeyController;

public class GameWindow extends JFrame
{
	public static final int WIDTH = 700;// 1400;
	public static final int HEIGHT = 400;// 800;

	private static final int EXTERNAL_WIDTH = 6;
	private static final int EXTERNAL_HEIGHT = 29;

	private static final long serialVersionUID = 1L;
	private static final File ICON = new File(Main.RESOURCES + "icon.png");

	private static GameWindow instance;
	private static Object mutex = new Object();

	private final GameControlKeyListener keyListener;
	private final KeyController keyController;
	private final GamePanel gamePanel;
	
	private final List<GraphicalElement> graphicalElements = new ArrayList<>();
	private final List<PhysicalElement> immovableElements = new ArrayList<>();
	private final List<AutoMovingPhysicalElement> movingElements = new ArrayList<>();
	private final List<Player> players = new ArrayList<>();

	private GameWindow() throws IOException
	{
		this.keyListener = new GameControlKeyListener();
		this.gamePanel = new GamePanel();
		this.keyController = new KeyController();

		JFrame frame = new JFrame("FS4");
		frame.setIconImage(ImageIO.read(ICON));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH + EXTERNAL_WIDTH, HEIGHT + EXTERNAL_HEIGHT);
		frame.addKeyListener(this.keyListener);
		frame.setContentPane(this.gamePanel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/**
	 * Instantiates only one GameWindow (Singleton design pattern).
	 * 
	 * @return The instance
	 * @throws IOException
	 */
	public static GameWindow getInstance()
	{
		GameWindow result = instance;

		if (result == null)
		{
			synchronized (mutex)
			{
				result = instance;

				if (result == null)
				{
					try
					{
						instance = result = new GameWindow();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	public GamePanel getGamePanel()
	{
		return this.gamePanel;
	}

	public GameControlKeyListener getKeyListener()
	{
		return this.keyListener;
	}

	public KeyController getKeyController()
	{
		return this.keyController;
	}

	/**
	 * Updates the player from the last keyboard key pressed.
	 * @param key The last key pressed
	 */
	public void updatePlayerFromKeyboard(final int key)
	{
		this.keyController.updateLastKeyPressed(key);
	}

	public List<GraphicalElement> getGraphicalElements()
	{
		return graphicalElements;
	}

	public List<PhysicalElement> getImmovableElements()
	{
		return immovableElements;
	}

	public List<AutoMovingPhysicalElement> getMovingElements()
	{
		return movingElements;
	}
	
	public List<Player> getPlayers()
	{
		return players;
	}
	
	/**
	 * Executed each ticks while the game is running.
	 */
	public void tick()
	{
		this.movePlayers();		
		this.moveAutomovableElements();

		this.tickElements();
		this.getGamePanel().repaint();
	}
	
	private void tickElements()
	{
		for(AutoMovingPhysicalElement automov : this.movingElements)
		{
			automov.incrementTick();
		}
		
		for(Player player : this.players)
		{
			player.incrementTick();
		}
		
	}

	/**
	 * Moves all auto movable elements each game tick.
	 */
	private void moveAutomovableElements()
	{
		for(AutoMovingPhysicalElement automov : this.movingElements)
		{
			DirectionTuple dirs = automov.getPattern().getMovementAt(automov.getTicksExisted());
			automov.move(dirs.getDirX(), dirs.getDirZ());
		}
	}

	/**
	 * Moves players each game tick.
	 */
	private void movePlayers()
	{
		if (!this.getKeyListener().getAllKeysPressed().isEmpty())
		{
			DirectionTuple dirs = Direction.fromKeysPressed(this.getKeyController().getLastAxisXKeyPressed(),
					this.getKeyController().getLastAxisZKeyPressed(), this.getKeyListener().getAllKeysPressed());

			for(Player player : this.getPlayers())
			{
				player.move(dirs.getDirX(), dirs.getDirZ());
			}
		}
	}
}
