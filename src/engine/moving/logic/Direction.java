package engine.moving.logic;

import java.util.Set;

public enum Direction
{
	UP(Axis.Z, -1), DOWN(Axis.Z, 1), LEFT(Axis.X, -1), RIGHT(Axis.X, 1), NONE(Axis.NONE, 0);

	private final Axis axis;
	private final int sens;

	private Direction(final Axis axis, final int sens)
	{
		this.axis = axis;
		this.sens = sens;
	}

	public Axis getAxis()
	{
		return this.axis;
	}

	public int getSens()
	{
		return sens;
	}

	public static Direction fromKeyPressed(final int key)
	{
		switch (key)
		{
		case GameControlKeys.DOWN:
			return DOWN;
		case GameControlKeys.LEFT:
			return LEFT;
		case GameControlKeys.RIGHT:
			return RIGHT;
		case GameControlKeys.UP:
			return UP;
		default:
			return NONE;
		}

	}
	
	public static DirectionTuple fromKeysPressed(final int lastXKey, final int lastZKey, final Set<Integer> keys)
	{
		int keyX = GameControlKeys.NO_KEY;
		int keyZ = GameControlKeys.NO_KEY;
		
		if(GameControlKeys.areSameXDirectionKeys(keys))
		{
			keyX = lastXKey;
		}
		else
		{
			for(int key : keys)
			{
				if(GameControlKeys.isXAxisDirectionKey(key))
				{
					keyX = key;
					//break;
				}
			}
		}
		
		if(GameControlKeys.areSameZDirectionKeys(keys))
		{
			keyZ = lastZKey;
		}
		else
		{
			for(int key : keys)
			{
				if(GameControlKeys.isZAxisDirectionKey(key))
				{
					keyZ = key;
					//break;
				}
			}
		}
		
		return new DirectionTuple(fromKeyPressed(keyX), fromKeyPressed(keyZ));
	}
}
