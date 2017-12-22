package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import android.graphics.drawable.shapes.*;

public class SolidBox extends CollisionBox implements IRenderable
{
	boolean isPlaceMan;

	SolidBox(int x, int y, int width, int heigth)
	{
		super(x, y, width, heigth);
	}
	
	@Override
	public void render(ShapeRenderer shape)
	{
		// TODO: Implement this method
		if (isPlaceMan)
			shape.setColor(0, 0, 0, 1);
		else
			shape.setColor(0.5f, 0, 0, 1);
		shape.rect(mask.x, mask.y, mask.width-2, mask.height);
		//isPlaceMan = false;
	}

	@Override
	public void render(SpriteBatch batch)
	{
		// TODO: Implement this method
	}

	@Override
	public void emitCollision(ICollideable other)
	{
		// TODO: Implement this method
		isPlaceMan=true;
	}
	
}
