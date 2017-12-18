package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import android.graphics.drawable.shapes.*;

public class SolidBox extends VisibleBox
{

	SolidBox(int x, int y, int width, int heigth)
	{
		super(x, y, width, heigth);
	}
	
	@Override
	public void render(ShapeRenderer shape)
	{
		// TODO: Implement this method
		shape.setColor(0.5f, 0, 0, 1);
		shape.rect(mask.x, mask.y, mask.width, mask.height);
	}

	@Override
	public void render(SpriteBatch batch)
	{
		// TODO: Implement this method
	}

	
	
}
