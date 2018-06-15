package com.eastarea.mygame2.game;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.eastarea.mygame2.*;
import com.eastarea.mygame2.character.*;
import com.eastarea.mygame2.note.*;
import java.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.*;

public class GameSession
{
    BrutalGame game;
    public Music music;
	OrthographicCamera camera;
	
    public GameLevel level;
    GuitarCube guitarCube;
    
    CollisionBox startFloor;
	
	//int cameraOffset;
	
	int beginMusicPosition = 300;
	float musicPosition = 0;
	public int nextNoteNumber = 0;
    float timer = 0;
    
    public GameSession(BrutalGame game)
    {
        this.game = game;
        level = new GameLevel(1000);
		camera = game.camera.camera;
		//cameraOffset = (int)(game.camera.viewportWidth / 2) + 300;
        
        guitarCube = new GuitarCube(game);
        
        music = Gdx.audio.newMusic(game.musicFile);
        startFloor = new JumpBox(0,10,10000,40);
        level.add(0, startFloor);
		
		makeMapByNotes(game.notesName, startFloor);
        
        resetGame();
    }

    public void resetGame()
    {
		guitarCube.reset();
		musicPosition = 0;
		nextNoteNumber = 0;
		startFloor = new FloorBox(0,10,10000,40);
        if (music.isPlaying())
            music.stop();
		music.dispose();
		music = Gdx.audio.newMusic(game.musicFile);
        start();
    }
    
    public void start() {
        timer = 0;
        guitarCube.stop();
    }
    
    public void update()
    {
        game.camera.update(guitarCube);
        
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
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        
        game.background.render(batch);
        
        batch.begin();
        font.setColor(0,0,0,1);
        font.draw(batch, (int) (timer) + "s", camera.position.x - 10, 300);
		font.draw(batch, (int) (nextNoteNumber) + "", camera.position.x - 10, 330);
        font.draw(batch, (int) (guitarCube.velocity.y) + "ms", camera.position.x - 10, 360);
        font.draw(batch,  (guitarCube.onGround) + "", camera.position.x - 10, 390);
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
				prevNote = new Note(0, new FloorBox(0, 0, 50, 50), null);
			}
			level.notes.get(i).updateOX(prevNote, resX, beginMusicPosition);
			if ( guitarCube.position.x + guitarCube.position.width > level.notes.get(i).floor.getMask().x) {
				nextNoteNumber = i;
			}
		}
	}

	public void makeMapByNotes(String notesName, CollisionBox firstBox)
	{
		List<Note> vals = NoteIO.readAssetArray(notesName);
		int num =0;
		level.countNotes = vals.size();

		for (Note val: vals) {
			Note prevNote;
			if (num>0) {
				prevNote = level.notes.get(num-1);
			} else {
				prevNote = new Note(0, new FloorBox(0, 0, 50, 50), null);
			}
			val.updateOX(prevNote, guitarCube.velocity.x, beginMusicPosition);
			
			level.add(num, val);
		
			num++;
		}
	}
}
