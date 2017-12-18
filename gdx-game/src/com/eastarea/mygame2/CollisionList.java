package com.eastarea.mygame2;
import java.util.*;
import com.badlogic.gdx.math.*;

public class CollisionList
{
	List<List<Rectangle>> list;
	int length;
	
	CollisionList(int length)
	{
		list = new ArrayList<List<Rectangle>>();
		this.length = length;
		
		for (int i=0; i<length; i++)
		{
			list.add(new ArrayList<Rectangle>());
		}
	}
	
	public void add(int i, Rectangle rect)
	{
		list.get(i).add(rect);
	}
}
