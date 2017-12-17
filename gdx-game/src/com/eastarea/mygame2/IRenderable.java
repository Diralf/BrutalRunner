package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;

public interface IRenderable
{
	public void render(ShapeRenderer shape);
	public void render(SpriteBatch batch);
}
