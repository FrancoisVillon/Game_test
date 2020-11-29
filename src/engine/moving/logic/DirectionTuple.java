package engine.moving.logic;

public class DirectionTuple
{
	public static final DirectionTuple NONE = new DirectionTuple(Direction.NONE, Direction.NONE);
	
	private final Direction dirX;
	private final Direction dirZ;
	
	public DirectionTuple(final Direction dirX, final Direction dirZ)
	{
		this.dirX = dirX;
		this.dirZ = dirZ;
	}

	public Direction getDirX()
	{
		return dirX;
	}

	public Direction getDirZ()
	{
		return dirZ;
	}

}
