package engine.moving.logic;

public class DirectionTuple
{
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
