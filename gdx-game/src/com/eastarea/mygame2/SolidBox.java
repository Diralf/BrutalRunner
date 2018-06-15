package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import android.graphics.drawable.shapes.*;

public abstract class SolidBox extends CollisionBox implements IRenderable
{

	public SolidBox(int x, int y, int width, int heigth)
	{
		super(x, y, width, heigth);
	}

    @Override
    public ECollisionType getType()
    {
        return ECollisionType.SOLID;
    }
}
