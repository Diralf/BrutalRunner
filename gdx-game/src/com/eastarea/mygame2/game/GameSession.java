package com.eastarea.mygame2.game;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.eastarea.mygame2.*;
import com.eastarea.mygame2.character.*;
import com.eastarea.mygame2.note.*;
import java.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;

public class GameSession
{
    BrutalGame game;
    Music music;
    
    public GameLevel level;
    GuitarCube guitarCube;
    
    CollisionBox startFloor;
	
	int cameraOffset;
	
	int beginMusicPosition = 300;
	float musicPosition = 0;
	public int nextNoteNumber = 0;
    float timer = 0;
    
    

    public GameSession(BrutalGame game)
    {
        this.game = game;
        level = new GameLevel(1000);
		cameraOffset = (int)(game.camera.viewportWidth / 2) + 300;
        
        guitarCube = new GuitarCube(game);
        
        music = game.backMusic;
		
        startFloor = new SolidBox(0,10,10000,40);
        level.add(0, startFloor);
		
		makeMapByNotes(game.notesName, startFloor);
        
       // music.setVolume(0);
        
        resetGame();
    }

    public void resetGame()
    {
        if (music.isPlaying())
            music.stop();
        start();
    }
    
    public void start() {
        timer = 0;
        guitarCube.stop();
    }
    
    public void update()
    {
        game.camera.position.x = guitarCube.position.x + cameraOffset;
		
		game.camera.update();
        
        if (!music.isPlaying()) 
        {
            if (timer > 3)
            {
                music.play();
                guitarCube.run();
            } else {
                timer += Gdx.graphics.getDeltaTime();
            }
        } else {
            guitarCube.update(level);

            musicPosition = music.getPosition();
            updateMap(50);
        }
        
    }
    
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font)
    {
        batch.setProjectionMatrix(game.camera.combined);
        shapeRenderer.setProjectionMatrix(game.camera.combined);
        
        game.background.render(batch);
        
        batch.begin();
        font.setColor(0,0,0,1);
        font.draw(batch, (int) (timer) + "s", game.camera.position.x - 10, 300);
		font.draw(batch, (int) (nextNoteNumber) + "", game.camera.position.x - 10, 330);
        batch.end();
       
        level.render(batch, shapeRenderer, nextNoteNumber);
        
        guitarCube.render(shapeRenderer);
    }

    public void dispose()
    {
        // TODO: Implement this method
        music.stop();
        music.dispose();
    }

    public void resize(int width, int height)
    {
        // TODO: Implement this method
    }

    public void pause()
    {
        // TODO: Implement this method
        music.pause();
    }

    public void resume()
    {
        // TODO: Implement this method
        start();
    }
	
	public void updateMap(int range) {
		float resX = guitarCube.position.x /*+ guitarCube.position.width*/ - beginMusicPosition;
		if (musicPosition ==0) return;
		resX /= musicPosition;

		int si =0;
		int ei = level.countNotes;

        if (nextNoteNumber > 3) si = nextNoteNumber -3;
        if (nextNoteNumber + range < level.countNotes) ei = nextNoteNumber + range;

		for (int i=si; i<ei; i++) {
			Note prevNote;
			if (i>0) {
				prevNote = level.notes.get(i-1);
			} else {
				prevNote = new Note(0, startFloor, null);
			}
			level.notes.get(i).updateOX(prevNote, resX, beginMusicPosition);
			if ( guitarCube.position.x + guitarCube.position.width > level.notes.get(i).floor.getMask().x) {
				nextNoteNumber = i;
			}
		}
	}

	public void makeMapByNotes(String notesName, CollisionBox firstBox)
	{
		List<Note> vals = NoteIO.readExtArray(notesName);
		int num =0;
		level.countNotes = vals.size();

		for (Note val: vals) {
			Note prevNote;
			if (num>0) {
				prevNote = level.notes.get(num-1);
			} else {
				prevNote = new Note(0, firstBox, null);
			}
			val.updateOX(prevNote, guitarCube.velocity.x, beginMusicPosition);
			
			level.add(num, val);
		
			num++;
		}
	}
}
