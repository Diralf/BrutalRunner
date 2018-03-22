package com.eastarea.mygame2.game;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.eastarea.mygame2.*;
import com.eastarea.mygame2.note.*;
import java.io.*;
import java.util.*;
import com.eastarea.mygame2.io.*;

public class GameRun
{
	BrutalGame game;
	
	public Music backMusic;
	GuitarMan guitarMan;
	
	List<IRenderable> visibleBoxes;
	List<Note> notes;
    int countNotes = 0;
	
	CollisionBox startFloor;
	
	Map<ECollisionType, CollisionList> collisionMap;
	
	public int nextNoteNumber = 0;
	
	float lastPosition = 0;
	float deltaPosition= 0;
	public float musicPosition = 0;
	float beginMusicPosition = 0;
	float beginMusicTime = 0;
	boolean isBegin = false;
	
	String musicName = "SevenNationArmy.mp3";
	String notesName = "testext.txt";
	
	public GameRun(BrutalGame game, File songFile) {
		this.game = game;
		String shortFileName = IOFile.getSortName(songFile);
		musicName = shortFileName+".mp3";
		notesName = shortFileName+".txt";
		
		visibleBoxes = new ArrayList<IRenderable>();
		notes = new ArrayList<Note>();
		
		int levelLength = 500;
		collisionMap = new HashMap<ECollisionType, CollisionList>();             
		collisionMap.put(ECollisionType.SOLID, new CollisionList(levelLength));
		collisionMap.put(ECollisionType.LIQUID, new CollisionList(levelLength));
		
		startFloor = new SolidBox(0,10,2000,40);
		collisionMap.get(ECollisionType.SOLID).add(0, startFloor);
		visibleBoxes.add((IRenderable)startFloor);
		
		Note testNoye = new Note(0.1f);
		visibleBoxes.add((IRenderable)testNoye.item.position);
		
		guitarMan = new GuitarMan(game);
		
		backMusic = Gdx.audio.newMusic(Gdx.files.internal(musicName));
		
		makeMapByNotes();
		resetGame();
	}
	
	public void update() 
	{
		if (isBegin) 
		{
			musicPosition = backMusic.getPosition();
			deltaPosition = backMusic.getPosition() - lastPosition;
			if (deltaPosition - 0.01 > Gdx.graphics.getDeltaTime()) 
			{
				backMusic.pause();
				backMusic.play();
				musicPosition = lastPosition + Gdx.graphics.getDeltaTime();
			}
			lastPosition = backMusic.getPosition();
			updateMap(50);
		} else {
			beginMusicTime += Gdx.graphics.getDeltaTime();
			if (beginMusicTime > 1)
			{
				start();
			}
		}

		//guitarMan.nextPosition.x =50+ backMusic.getPosition() * guitarMan.manVelocity.x;
		guitarMan.update(collisionMap, 0);

		// Move camera

		game.camera.position.x = guitarMan.position.x + game.camera.viewportWidth / 2 - 100;

		if (guitarMan.position.y < 0) 
		{
			guitarMan.manVelocity.y =1000;
		}
	}
	
	
	public void renderBatch(SpriteBatch batch, BitmapFont font)
	{
		batch.begin();

		batch.disableBlending();
		// Draw background
		//for (int i = 0; i < 30; i++)
			//batch.draw(game.backgroundTexture, i * 800 + (guitarMan.position.x/2), 0, 800, 800);

		batch.enableBlending();

		for (IRenderable b: visibleBoxes)
		    b.render(batch);

		//guitarMan.render(batch);

		font.setColor(0,0,0,1);
		font.draw(batch, (int) (guitarMan.position.x / 70) + "m", game.camera.position.x - 10, 30);
		font.draw(batch, (int)(backMusic.getPosition()/60)+":"+backMusic.getPosition()%60, game.camera.position.x + 30, game.camera.viewportHeight - 30);
		font.draw(batch, nextNoteNumber+"", game.camera.position.x - 170, game.camera.viewportHeight - 30);
		
		font.draw(batch, Gdx.graphics.getDeltaTime()+"", game.camera.position.x - 250, game.camera.viewportHeight - 30);
		font.draw(batch, deltaPosition+"", game.camera.position.x - 250, game.camera.viewportHeight - 40);
		
		font.draw(batch, guitarMan.position.x+"", game.camera.position.x - 250, game.camera.viewportHeight - 60);
		font.draw(batch, beginMusicPosition+"", game.camera.position.x - 250, game.camera.viewportHeight - 70);
        font.draw(batch, beginMusicTime+"", game.camera.position.x - 250, game.camera.viewportHeight - 90);
		font.draw(batch, guitarMan.manVelocity.x+"", game.camera.position.x - 250, game.camera.viewportHeight - 110);
		batch.end();
	}
	
	public void renderShapes(ShapeRenderer shapeRenderer)
	{
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for (IRenderable b: visibleBoxes)
		    b.render(shapeRenderer);
        shapeRenderer.setColor(0,0,0,1);
        guitarMan.render(shapeRenderer);
		shapeRenderer.end();

//		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//		shapeRenderer.setColor(0,0,0,1);
//		shapeRenderer.rect(guitarMan.position.x, guitarMan.position.y, guitarMan.position.width, guitarMan.position.height);
//		shapeRenderer.end();
	}
	
    public void dispose()
	{
		backMusic.stop();
        backMusic.dispose();
    }
	
    public void pause()
	{
		if (isBegin)
		{
			backMusic.pause();
		}
		
    }
	
    public void resume()
	{
		if (isBegin) {
			backMusic.play();
		}
		
    }
	
	public void start()
	{
		beginMusicPosition = guitarMan.position.x;
		
		nextNoteNumber = 0;
		if (backMusic.isPlaying()) backMusic.stop();
		backMusic.play();
	    
		isBegin = true;
	}
	
	public void resetGame()
	{
		game.configureCamera();
		guitarMan.position = new Rectangle(0, 51, 100, 200);
		guitarMan.nextPosition = new Rectangle(0,51,100,200);
		guitarMan.manVelocity = new Vector2(500, 0);
		beginMusicTime = 0;
		startFloor.getMask().width = beginMusicPosition;
		if (backMusic.isPlaying()) backMusic.stop();
		backMusic.dispose();
		backMusic = Gdx.audio.newMusic(Gdx.files.internal(musicName));
		nextNoteNumber = 0;
		isBegin = false;
		beginMusicPosition = 0;
		startFloor.getMask().width = 2000;
	}
	
	public void updateMap(int range) {
		float resX = guitarMan.position.x + guitarMan.position.width - beginMusicPosition;
		if (musicPosition ==0) return;
		resX /= musicPosition;
		
		int si =0;
		int ei = countNotes;
        
        if (nextNoteNumber > 3) si = nextNoteNumber -3;
        if (nextNoteNumber + range < countNotes) ei = nextNoteNumber + range;
		
		for (int i=si; i<ei; i++) {
			Note prevNote;
			if (i>0) {
				prevNote = notes.get(i-1);
			} else {
				prevNote = new Note(0, startFloor, null);
			}
			notes.get(i).updateOX(prevNote, resX, (int) beginMusicPosition);
			if ( guitarMan.position.x + guitarMan.position.width > notes.get(i).floor.getMask().x) {
				nextNoteNumber = i;
			}
		}
	}

	public void makeMapByNotes()
	{
		List<Note> vals = NoteIO.readExtArray(notesName);

		ECollisionType type = ECollisionType.LIQUID;
		int num =0;

		for (Note val: vals) {
			Note prevNote;
			if (num>0) {
				prevNote = notes.get(num-1);
			} else {
				prevNote = new Note(0, startFloor, null);
			}
			val.updateOX(prevNote, guitarMan.manVelocity.x, (int) beginMusicPosition);
			//visibleBoxes.add((IRenderable)val.item.position);

			notes.add(val);
			//collisionMap.get(type).add(num, val.item.position);

			visibleBoxes.add((IRenderable)val.floor);
			collisionMap.get(ECollisionType.SOLID).add(num, val.floor);
			num++;

		}
	}
	
	
//	public int[] convertBinary(int num){
//		int binary[] = new int[40];
//		int index = 0;
//		while(num > 0){
//			binary[index++] = num%2;
//			num = num/2;
//		}
//		return binary;
//	}

//	public void makeMap(int[] m, ECollisionType type)
//	{
//		int cellSize = game.cellSize;
//		for (int i = 0; i<3000; i++)
//		{
//			int pos[] = convertBinary(m[i% m.length]);
//			for (int j=0; j < pos.length; j++)
//			{
//				if (pos[j]==0) continue;
//				CollisionBox box;
//				if (type == ECollisionType.SOLID)
//					box = new SolidBox(i*cellSize, j*50, cellSize, 50);
//				else
//					box = new LiquidBox(i*cellSize+ cellSize/4, j*50, cellSize/2, cellSize/2);
//				visibleBoxes.add((IRenderable)box);
//				collisionMap.get(type).add(i, box);
//			}
//		}
//	}
}
