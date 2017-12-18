package com.eastarea.mygame2;
import com.badlogic.gdx.math.*;

public abstract class VisibleBox implements IRenderable
{
	Rectangle mask;

	VisibleBox(int x, int y, int width, int heigth)
	{
		mask = new Rectangle(x, y, width, heigth);
	}
}

