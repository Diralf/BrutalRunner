package com.eastarea.mygame2;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.eastarea.mygame2.game.*;
import com.eastarea.mygame2.io.*;
import java.io.*;
import com.badlogic.gdx.files.*;

public class BrutalGame implements IStagable
{
	
	//public GameRun grun;
    public GameSession session;

	//public OrthographicCamera camera;
	public GameCamera camera;
    public GameBackground background;
	
	public Sound collisionSound;
    
	public FileHandle musicFile;
    
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
		camera = new GameCamera();

		// Load and position rocks
	
		collisionSound = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));
		musicFile = Gdx.files.internal(musicName);
        

		
		
        session = new GameSession(this);
		buttons = new GameUIButtons(this);
        
        
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
