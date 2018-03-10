package com.eastarea.mygame2.game;
import com.badlogic.gdx.audio.*;
import com.eastarea.mygame2.*;
import java.util.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.eastarea.mygame2.io.*;

public class GameSession
{
	BrutalGame game;
	
	public Music backMusic;
	GuitarMan guitarMan;
	
	List<IRenderable> visibleBoxes;
	List<CollisionBox> noteBoxes;
	List<CollisionBox> floorBoxes;
	List<Float> notes;
	
	Map<ECollisionType, CollisionList> collisionMap;
	
	public int nextNoteNumber = 0;
	
	public GameSession(BrutalGame game) {
		this.game =game;
		visibleBoxes = new ArrayList<IRenderable>();
		noteBoxes = new ArrayList<CollisionBox>();
		floorBoxes = new ArrayList<CollisionBox>();
		notes = new ArrayList<Float>();
		
		int levelLength = 3500;
		collisionMap = new HashMap<ECollisionType, CollisionList>();             
		collisionMap.put(ECollisionType.SOLID, new CollisionList(levelLength));
		collisionMap.put(ECollisionType.LIQUID, new CollisionList(levelLength));
		
		makeMapByNotes();
		
		guitarMan = new GuitarMan(game);
		
		backMusic = Gdx.audio.newMusic(Gdx.files.internal("SevenNationArmy.mp3"));
		
		resetGame();
	}
	
	public void update() 
	{
		updateMap();
		
		//guitarMan.nextPosition.x =50+ backMusic.getPosition() * guitarMan.manVelocity.x;
		guitarMan.update(collisionMap, game.cellSize);

		// Move camera

		game.camera.position.x = guitarMan.position.x + game.camera.viewportWidth / 2 - 100;

		if (guitarMan.position.y < 0) 
		{
			guitarMan.manVelocity.y =2000;
		}
		
	}
	
	
	public void renderBatch(SpriteBatch batch, BitmapFont font)
	{
		
		
		batch.begin();

		batch.disableBlending();
		// Draw background
		for (int i = 0; i < 30; i++)
			batch.draw(game.backgroundTexture, i * 2900 + (guitarMan.position.x/2), 0, 2900, 800);

		batch.enableBlending();

		for (IRenderable b: visibleBoxes)
		    b.render(batch);

		guitarMan.render(batch);

		font.setColor(0,0,0,1);
		font.draw(batch, (int) (guitarMan.position.x / 70) + "m", game.camera.position.x - 10, 30);
		font.draw(batch, (int)(backMusic.getPosition()/60)+":"+backMusic.getPosition()%60, game.camera.position.x + 30, game.camera.viewportHeight - 30);
		font.draw(batch, nextNoteNumber+"", game.camera.position.x - 170, game.camera.viewportHeight - 30);
	
        batch.end();
	}
	
	public void renderShapes(ShapeRenderer shapeRenderer)
	{
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		for (IRenderable b: visibleBoxes)
		    b.render(shapeRenderer);
		shapeRenderer.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(0,0,0,1);
		shapeRenderer.rect(guitarMan.position.x, guitarMan.position.y, guitarMan.position.width, guitarMan.position.height);
		shapeRenderer.end();
	}
	
    public void dispose()
	{
		backMusic.stop();
        backMusic.dispose();
    }
	
    public void pause()
	{
		backMusic.pause();
    }
	
    public void resume()
	{
		backMusic.play();
    }
	
	public void resetGame()
	{
		game.configureCamera();
		guitarMan.position = new Rectangle(0, 51, 100, 200);
		guitarMan.nextPosition = new Rectangle(0,51,100,200);
		guitarMan.manVelocity = new Vector2(500, 0);
		nextNoteNumber = 0;
		if (backMusic.isPlaying()) backMusic.stop();
		backMusic.play();
		if (backMusic.isPlaying()) {
			updateMap();
		}
	}
	
	public void updateMap() {
		float resX = guitarMan.position.x + guitarMan.position.width;
		if (backMusic.getPosition() ==0) return;
		resX /= backMusic.getPosition();
		//System.out.println(resX);
		//System.out.print(" ");
		nextNoteNumber =0;
		for (int i=0; i<noteBoxes.size(); i++) {

			//resX *= notes.get(i);
			//System.out.println(resX);
			float pos = resX * notes.get(i);
			float prev = 0;
			noteBoxes.get(i).getMask().x= pos;
			if (i>0) {
				CollisionBox pBox = floorBoxes.get(i-1);
				prev = pBox.getMask().x + pBox.getMask().width;
			}
		    CollisionBox cBox = floorBoxes.get(i);
			cBox.getMask().x = prev;
			cBox.getMask().width = pos - prev - 2;

			if ( guitarMan.position.x > pos) {
				nextNoteNumber = i;
			}
			//noteBoxes.get(i).getMask().x = ((guitarMan.position.x-beginMusicPosition)*backMusic.getPosition())/notes.get(i)+beginMusicPosition;
		}
	}

	public void makeMapByNotes()
	{
		List<String> vals = IOFile.readFileSDArray("testext.txt");
		int cellSize = game.cellSize;

		System.out.println(vals.toString());

		ECollisionType type = ECollisionType.LIQUID;
	    float prevPosition =0;
		int num =0;

		for (String val: vals) {
			float note = Float.parseFloat(val);
			CollisionBox box;
			box = new LiquidBox((int)(note), 50, cellSize/2, cellSize/2);
			visibleBoxes.add((IRenderable)box);
			noteBoxes.add(box);
			notes.add(note);
			collisionMap.get(type).add(num, box);

			box = new SolidBox((int)prevPosition, 0, (int)(prevPosition+note), 50);
			visibleBoxes.add((IRenderable)box);
			floorBoxes.add(box);
			collisionMap.get(ECollisionType.SOLID).add(num, box);
			prevPosition = note;
			num++;

		}
	}
	
	
	public int[] convertBinary(int num){
		int binary[] = new int[40];
		int index = 0;
		while(num > 0){
			binary[index++] = num%2;
			num = num/2;
		}
		return binary;
	}

	public void makeMap(int[] m, ECollisionType type)
	{
		int cellSize = game.cellSize;
		for (int i = 0; i<3000; i++)
		{
			int pos[] = convertBinary(m[i% m.length]);
			for (int j=0; j < pos.length; j++)
			{
				if (pos[j]==0) continue;
				CollisionBox box;
				if (type == ECollisionType.SOLID)
					box = new SolidBox(i*cellSize, j*50, cellSize, 50);
				else
					box = new LiquidBox(i*cellSize+ cellSize/4, j*50, cellSize/2, cellSize/2);
				visibleBoxes.add((IRenderable)box);
				collisionMap.get(type).add(i, box);
			}
		}
	}
}
