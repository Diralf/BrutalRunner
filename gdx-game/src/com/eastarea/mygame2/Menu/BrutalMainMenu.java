package com.eastarea.mygame2.Menu;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.eastarea.mygame2.editor.*;

public class BrutalMainMenu implements IStagable
{
	
	ButtonsMainMenu buttons;
	
	public BrutalMainMenu()
	{
		buttons = new ButtonsMainMenu();
	}

	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font)
	{
		buttons.render();
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
