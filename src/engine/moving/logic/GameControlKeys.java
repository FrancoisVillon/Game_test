package engine.moving.logic;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class GameControlKeys
{
	public static final int UP = KeyEvent.VK_UP;
	public static final int DOWN = KeyEvent.VK_DOWN;
	public static final int LEFT = KeyEvent.VK_LEFT;
	public static final int RIGHT = KeyEvent.VK_RIGHT;
	
	public static final int SHOT = 0;
	public static final int ACTION = 0;
	public static final int RELOAD = 0;
	
	public static final int NO_KEY = -1;
	
	private static final Set<Integer> DIRECTION_KEYS = new HashSet<>(4);
	private static final Set<Integer> DIRECTION_KEYS_X = new HashSet<>(2);
	private static final Set<Integer> DIRECTION_KEYS_Z = new HashSet<>(2);
	private static final Set<Integer> ACTION_KEYS = new HashSet<>(3);
	
	static
	{
		DIRECTION_KEYS.add(UP);
		DIRECTION_KEYS.add(DOWN);
		DIRECTION_KEYS.add(LEFT);
		DIRECTION_KEYS.add(RIGHT);

		DIRECTION_KEYS_Z.add(UP);
		DIRECTION_KEYS_Z.add(DOWN);
		
		DIRECTION_KEYS_X.add(LEFT);
		DIRECTION_KEYS_X.add(RIGHT);
		
		ACTION_KEYS.add(SHOT);
		ACTION_KEYS.add(ACTION);
		ACTION_KEYS.add(RELOAD);
	}
	
	public static boolean isDirectionKey(final int key)
	{
		return DIRECTION_KEYS.contains(key);
	}
	
	public static boolean isZAxisDirectionKey(final int key)
	{
		return DIRECTION_KEYS_Z.contains(key);
	}
	
	public static boolean isXAxisDirectionKey(final int key)
	{
		return DIRECTION_KEYS_X.contains(key);
	}
	
	public static boolean isActionKey(final int key)
	{
		return ACTION_KEYS.contains(key);
	}
	
	/**
	 * Checks if two keys are X direction keys
	 * @param keys The keys to check
	 * @return true if two keys are X direction keys
	 */
	public static boolean areSameXDirectionKeys(final Set<Integer> keys)
	{
		int nbXKey = 0;
		
		for(int key : keys)
		{
			if(isXAxisDirectionKey(key))
			{
				nbXKey++;
			}
		}
		
		return nbXKey > 1;
	}
	
	/**
	 * Checks if two keys are Z direction keys
	 * @param keys The keys to check
	 * @return true if two keys are Z direction keys
	 */
	public static boolean areSameZDirectionKeys(final Set<Integer> keys)
	{
		int nbZKey = 0;
		
		for(int key : keys)
		{
			if(isZAxisDirectionKey(key))
			{
				nbZKey++;
			}
		}
		
		return nbZKey > 1;
	}
}
