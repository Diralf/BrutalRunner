package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.audio.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.glutils.*;

public class BrutalGame
{
	OrthographicCamera camera;
	TextureRegion backgroundTexture;
	
	Sound collisionSound;
	Music backMusic;
	StartMusicTrigger startMusicTrigger;
	int startMusicPosition=0;

	GuitarMan guitarMan;
	int cellSize=123;
	
	List<IRenderable> visibleBoxes;
	
	Map<ECollisionType, CollisionList> collisionMap;
	
	int levelMap[] = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	int bonusMap[] = {2,0,0,0,2,2,2,2,0,2,0,0,0,2,0,0};//{2,0,0,2,2,0,2,0,2,0,2,0,0,2,0,0};
	BrutalGame() 
	{
		
		// Load background 
		Texture texture = new Texture(Gdx.files.internal("skyBackground.jpg"));
		backgroundTexture = new TextureRegion(texture, 0, 0, 2048, 563);

		// Load and position rocks
		
		visibleBoxes = new ArrayList<IRenderable>();
		
		
		int levelLength = 350;
		collisionMap = new HashMap<ECollisionType, CollisionList>();
		collisionMap.put(ECollisionType.SOLID, new CollisionList(levelLength));
		collisionMap.put(ECollisionType.LIQUID, new CollisionList(levelLength));

//		for (int i=0; i<levelLength - 10;i++) 
//		{
//			int yBox = 0;
//			if ((i % 10) == 0) yBox = 50;
//			if ((i % 7) == 0) yBox = 70;
//			SolidBox box = new SolidBox(i*cellSize, yBox, cellSize, 50);
//			visibleBoxes.add(box);
//			collisionMap.get(ECollisionType.SOLID).add(i, box);
//		}
		
		makeMap(levelMap, ECollisionType.SOLID);
		makeMap(bonusMap, ECollisionType.LIQUID);
		
		
		LiquidBox lbox = new LiquidBox(1600, 150, 100, 100);
		visibleBoxes.add(lbox);
		collisionMap.get(ECollisionType.LIQUID).add(8, lbox);

		guitarMan = new GuitarMan();

		collisionSound = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));
		backMusic = Gdx.audio.newMusic(Gdx.files.internal("SevenNationArmy.mp3"));
		startMusicTrigger = new StartMusicTrigger(cellSize * startMusicPosition, 50, cellSize, cellSize);
		collisionMap.get(ECollisionType.LIQUID).add(startMusicPosition, startMusicTrigger);
		
		camera = new OrthographicCamera();
		resetGame();
	}
	
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer,BitmapFont font)
	{
		camera.update();

		batch.setProjectionMatrix(camera.combined);
        batch.begin();

		batch.disableBlending();
		// Draw background
		for (int i = 0; i < 30; i++)
			batch.draw(backgroundTexture, i * 2900 + (guitarMan.position.x/2), 0, 2900, 800);

		batch.enableBlending();
		
		for (IRenderable b: visibleBoxes)
		    b.render(batch);
			
		guitarMan.render(batch);

		font.setColor(0,0,0,1);
		font.draw(batch, (int) (guitarMan.position.x / 70) + "m", camera.position.x - 10, 30);
		font.draw(batch, (int)(backMusic.getPosition()/60)+":"+backMusic.getPosition()%60, camera.position.x + 30, camera.viewportHeight - 30);

        batch.end();

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		for (IRenderable b: visibleBoxes)
		    b.render(shapeRenderer);

//		shapeRenderer.setColor(0, 0.5f, 0, 1);
//		shapeRenderer.circle(guitarMan.position.x, guitarMan.position.y, 40);
//
//		shapeRenderer.setColor(0.5f, 0, 0, 1);
//		shapeRenderer.rect(10, 100, 80, 80);
//
//		shapeRenderer.setColor(0, 0, 0.5f, 1);
//		shapeRenderer.triangle(10, 200, 90, 200, 50, 270);
//
		shapeRenderer.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(0,0,0,1);
		shapeRenderer.rect(guitarMan.position.x, guitarMan.position.y, guitarMan.position.width, guitarMan.position.height);
		shapeRenderer.end();

		guitarMan.update(collisionMap, cellSize);

		// Move camera
		//camera.translate((guitarMan.manVelocity.x - camera.viewportWidth / 80) * Gdx.graphics.getDeltaTime(), 0);
		camera.position.x = guitarMan.position.x + camera.viewportWidth / 2 - 100;
		//camera.position.y = guitarMan.position.y;

		if (guitarMan.position.y + guitarMan.position.height < 0) 
		{
			collisionSound.play();
			resetGame();
		}
		
	}
	
	private void resetGame()
	{
		configureCamera();
		guitarMan.position = new Rectangle(0, 150, 100, 200);
		guitarMan.nextPosition = new Rectangle(0,150,100,200);
		guitarMan.manVelocity = new Vector2(500, 0);
		if (backMusic.isPlaying()) backMusic.stop();
		//backMusic.play();
	}
	
	private void configureCamera()
	{
		if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth())
			camera.setToOrtho(false, 800, 800 * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
		else
			camera.setToOrtho(false, 800 * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), 800);
	}
	
    public void dispose()
	{
        backMusic.dispose();
    }

    public void resize(int width, int height)
	{
		resetGame();
    }

    public void pause()
	{
    }

    public void resume()
	{
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
		for (int i = 0; i<300; i++)
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
