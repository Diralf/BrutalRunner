package com.eastarea.mygame2.trackList;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;

public class TrackListScene implements IStagable
{

	TrackListUI ui;
	
	public TrackListScene()
	{
		ui = new TrackListUI();
	}
	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font)
	{
		ui.render();
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
	
		// TODO: Implement this method
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}
	
	
}

