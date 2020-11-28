package engine;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

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

		this.gamePanel.initPlayer();
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

	/**
	 * Executed each ticks while the game is running.
	 */
	public void tick()
	{
		if (!this.getKeyListener().getAllKeysPressed().isEmpty())
		{
			DirectionTuple dirs = Direction.fromKeysPressed(this.getKeyController().getLastAxisXKeyPressed(),
					this.getKeyController().getLastAxisZKeyPressed(), this.getKeyListener().getAllKeysPressed());

			this.gamePanel.getPlayer().move(dirs.getDirX(), dirs.getDirZ());
		}
	}

}
