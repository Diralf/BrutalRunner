package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;

public interface IStagable
{
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer,BitmapFont font);
	
    public void dispose();
	
    public void resize(int width, int height);

    public void pause();

    public void resume();
}
