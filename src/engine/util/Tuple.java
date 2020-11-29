package engine.util;

public class Tuple<S1, S2>
{
	private final S1 elemnt1;
	private final S2 elemnt2;
	
	public Tuple(final S1 element1, final S2 element2)
	{
		this.elemnt1 = element1;
		this.elemnt2 = element2;
	}

	public S1 getFirstElement()
	{
		return elemnt1;
	}

	public S2 getSecondElement()
	{
		return elemnt2;
	}
}
