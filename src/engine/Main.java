package engine;

import javax.swing.JFrame;

public class Main
{
	public static final String RESOURCES = "resources/";

	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(false);

		//Instantiates the game window.
		GameWindow.getInstance();
	}

}
