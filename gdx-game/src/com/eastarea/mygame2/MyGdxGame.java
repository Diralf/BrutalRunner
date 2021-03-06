package com.eastarea.mygame2;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.eastarea.mygame2.editor.*;
import android.content.*;
import com.eastarea.mygame2.Menu.*;

public class MyGdxGame implements ApplicationListener
{
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;
	
	static IStagable currentStage;
	
	//static BrutalGame game;
	//static BrutalEditor editor;
	//static BrutalMainMenu mainMenu;

	public static Context context;
	
	MyGdxGame (Context cont){
		this.context = cont;
	}

    @Override
    public void create()
	{
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		
		/*
		game = new BrutalGame();
		editor = new BrutalEditor();
		mainMenu = new BrutalMainMenu();
		currentStage = mainMenu;
		*/
		changeStage(new BrutalMainMenu());
    }

    @Override
    public void render()
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		currentStage.render(batch, shapeRenderer,font);
    }

	@Override
    public void dispose()
	{
        batch.dispose();
		currentStage.dispose();
    }

    @Override
    public void resize(int width, int height)
	{
		currentStage.resize(width,height);
    }

    @Override
    public void pause()
	{
		currentStage.pause();
    }

    @Override
    public void resume()
	{
		currentStage.resume();
    }
	
	static public void changeStage(IStagable newStage)
	{
		if (currentStage != null) currentStage.dispose();
		currentStage = newStage;
	}
}
