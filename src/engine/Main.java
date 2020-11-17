package engine;

import java.io.IOException;

import javax.swing.JFrame;

public class Main
{
	public static final String RESOURCES = "resources/";

	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(false);

		try
		{
			GameWindow.getInstance();
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

}
