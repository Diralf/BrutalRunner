package com.eastarea.mygame2;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.eastarea.mygame2.game.*;
import com.eastarea.mygame2.io.*;
import java.io.*;

public class BrutalGame implements IStagable
{
	
	//public GameRun grun;й
    public GameSession session;

	public OrthographicCamera camera;
    public GameBackground background;
	
	public Sound collisionSound;
    
    public Music backMusic;
    
    public String musicName;
	public String notesName;
	
	GameUIButtons buttons;
	
	public BrutalGame(File songFile) 
	{
        String shortFileName = IOFile.getSortName(songFile);
        musicName = shortFileName+".mp3";
        notesName = shortFileName+".txt";
		
		// Load background 
        background = new GameBackground(this);

		// Load and position rocks
	
		collisionSound = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));
        backMusic = Gdx.audio.newMusic(Gdx.files.internal(musicName));

		camera = new OrthographicCamera();
		
        session = new GameSession(this);
		buttons = new GameUIButtons(this);
        configureCamera();
        
        
	}
	
	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer,BitmapFont font)
	{
        // update
        session.update();
       
        // render
        session.render(batch, shapeRenderer, font);
        
		buttons.render();
	}
	
	public void configureCamera()
	{
		if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth())
			camera.setToOrtho(false, 800, 800 * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
		else
			camera.setToOrtho(false, 800 * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), 800);
	}
	
	@Override
    public void dispose()
	{
        session.dispose();
    }

	@Override
    public void resize(int width, int height)
	{
		//resetGame();
    }
	
	@Override
    public void pause()
	{
        session.pause();
    }

	@Override
    public void resume()
	{
        session.resume();
    }
	
}
