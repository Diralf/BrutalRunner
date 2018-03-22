package com.eastarea.mygame2;

import com.badlogic.gdx.math.*;

public abstract class CollisionBox implements ICollideable
{
	Rectangle mask;
    
    public CollisionBox()
    {
        mask = new Rectangle(0,0,0,0);
    }

	public CollisionBox(int x, int y, int width, int heigth)
	{
		mask = new Rectangle(x, y, width, heigth);
	}

	@Override
	public Rectangle getMask()
	{
		// TODO: Implement this method
		return mask;
	}
    
    

}
