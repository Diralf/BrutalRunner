package com.eastarea.mygame2;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.badlogic.gdx.graphics.glutils.*;

public class MyGdxGame implements ApplicationListener
{
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;
	
	static BrutalGame game;

    @Override
    public void create()
	{
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		
		game = new BrutalGame();
    }

    @Override
    public void render()
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		game.render(batch, shapeRenderer,font);
    }

	@Override
    public void dispose()
	{
        batch.dispose();
		game.dispose();
    }

    @Override
    public void resize(int width, int height)
	{
		game.resize(width,height);
    }

    @Override
    public void pause()
	{
		game.pause();
    }

    @Override
    public void resume()
	{
		game.resume();
    }
}
