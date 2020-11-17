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
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this.keyListener);
		frame.setContentPane(this.gamePanel);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	/**
	 * Instantiates only one GameWindow (Singleton design pattern).
	 * 
	 * @return The instance
	 * @throws IOException
	 */
	public static GameWindow getInstance() throws IOException
	{
		GameWindow result = instance;

		if (result == null)
		{
			synchronized (mutex)
			{
				result = instance;

				if (result == null)
				{
					instance = result = new GameWindow();
				}
			}
		}
		return result;
	}

	public GamePanel getGamePanel()
	{
		return this.gamePanel;
	}

	public boolean isKeyPressed(final int keyCode)
	{
		return this.keyListener.isKeyPressed(keyCode);
	}

	public void updatePlayerPosition(final int key)
	{
		Player player = this.gamePanel.getPlayer();
		this.keyController.updateLastKeyPressed(key);

		DirectionTuple dirs = Direction.fromKeysPressed(this.keyController.getLastAxisXKeyPressed(),
				this.keyController.getLastAxisZKeyPressed(), this.keyListener.getAllKeysPressed());

		player.move(dirs.getDirX(), dirs.getDirZ());
	}
}
