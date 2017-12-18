package com.eastarea.mygame2;
import java.util.*;
import com.badlogic.gdx.math.*;

public class CollisionList
{
	List<List<ICollideable>> list;
	int length;
	
	CollisionList(int length)
	{
		list = new ArrayList<List<ICollideable>>();
		this.length = length;
		
		for (int i=0; i<length; i++)
		{
			list.add(new ArrayList<ICollideable>());
		}
	}
	
	public void add(int i, ICollideable rect)
	{
		list.get(i).add(rect);
	}
}
