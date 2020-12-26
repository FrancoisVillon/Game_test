package engine.moving.logic;

import java.util.ArrayList;
import java.util.List;

import engine.util.Tuple;

public class MovingPattern
{
	private final List<Tuple<Long, DirectionTuple>> movementList;
	private final long fullPaternTime;
	
	private MovingPattern(final List<Tuple<Long, DirectionTuple>> movementList)
	{
		this.movementList = movementList;
		this.fullPaternTime = this.movementList.get(this.movementList.size() - 1).getFirstElement();
	}
	
	/**
	 * Gets the movement at a precise time.
	 * @param time The time
	 * @return A DirectionTuple corresponding to the time
	 */
	public DirectionTuple getMovementAt(long time)
	{
		if(!this.movementList.isEmpty())
		{
			time %= this.fullPaternTime;
			
			for(Tuple<Long, DirectionTuple> movement : this.movementList)
			{
				if(time < movement.getFirstElement())
				{
					return movement.getSecondElement();
				}
			}
		}
		
		return DirectionTuple.NONE;
	}
	
	public static class Builder
	{
		private final List<Tuple<Long, DirectionTuple>> movementList = new ArrayList<>();
		private long totalTime = 0;
		
		private Builder() {}
		
		public static Builder init()
		{
			return new Builder();
		}
		
		public Builder addMovement(final long time, final Direction axisX, final Direction axisZ)
		{
			this.movementList.add(new Tuple<Long, DirectionTuple>(this.totalTime + time, new DirectionTuple(axisX, axisZ)));
			this.totalTime += time;
			return this;
		}
		
		public Builder addHorizontal(final long time, final Direction axisX)
		{
			this.movementList.add(new Tuple<Long, DirectionTuple>(this.totalTime + time, new DirectionTuple(axisX, Direction.NONE)));
			this.totalTime += time;
			return this;
		}
		
		public Builder addVertical(final long time, final Direction axisZ)
		{
			this.movementList.add(new Tuple<Long, DirectionTuple>(this.totalTime + time, new DirectionTuple(Direction.NONE, axisZ)));
			this.totalTime += time;
			return this;
		}
		
		public MovingPattern build()
		{
			return new MovingPattern(this.movementList);
		}
	}
}
