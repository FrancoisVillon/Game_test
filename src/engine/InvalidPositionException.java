package engine;

/**
 * This class is used to show an error when the position given to an element is invalid.
 * @author user0
 *
 */
public class InvalidPositionException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public InvalidPositionException(int posX, int posZ)
	{
		super(String.format("The position [%s, %s] is invalid", posX, posZ));
	}
}
