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
	TextureRegion rockTexture;
	Sound collisionSound;

	GuitarMan guitarMan;
	int cellSize;
	
	List<VisibleBox> solidBoxes;
	List<List<Rectangle>> collisionObjects;
	
	
	BrutalGame() 
	{
		cellSize = 200;
		
		// Load background 
		Texture texture = new Texture(Gdx.files.internal("skyBackground.jpg"));
		backgroundTexture = new TextureRegion(texture, 0, 0, 2048, 563);

		// Load and position rocks
		Texture texture2 = new Texture(Gdx.files.internal("rock.png"));
		rockTexture = new TextureRegion(texture2, 25, 0, 250, 250);
		solidBoxes = new ArrayList<VisibleBox>();
		collisionObjects = new ArrayList<List<Rectangle>>();

		for (int i=0; i<350; i++)
		{
			collisionObjects.add(new ArrayList<Rectangle>());
		}

		for (int i=0; i<300;i++) 
		{
			int yBox = 0;
			if ((i % 10) == 0) yBox = 50;
			if ((i % 7) == 0) yBox = 70;
			SolidBox box = new SolidBox(i*cellSize, yBox, cellSize, 50);
			solidBoxes.add(box);
			collisionObjects.get(i).add(box.mask);
		}

		guitarMan = new GuitarMan();

		collisionSound = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));
		
		camera = new OrthographicCamera();
		resetGame();
	}
	
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer,BitmapFont font)
	{
		camera.update();

		batch.setProjectionMatrix(camera.combined);
        batch.begin();

		// Draw background
		for (int i = 0; i < 30; i++)
			batch.draw(backgroundTexture, i * 2900, 0, 2900, 800);

		guitarMan.render(batch);

		font.draw(batch, (int) (guitarMan.position.x / 70) + "m", camera.position.x - 10, 30);
		font.draw(batch, Gdx.graphics.getDeltaTime() + "t", camera.position.x + 30, camera.viewportHeight - 30);
        batch.end();

		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		for (VisibleBox b: solidBoxes)
		    b.render(shapeRenderer);

		shapeRenderer.setColor(0, 0.5f, 0, 1);
		shapeRenderer.circle(guitarMan.position.x, guitarMan.position.y, 40);

		shapeRenderer.setColor(0.5f, 0, 0, 1);
		shapeRenderer.rect(10, 100, 80, 80);

		shapeRenderer.setColor(0, 0, 0.5f, 1);
		shapeRenderer.triangle(10, 200, 90, 200, 50, 270);

		shapeRenderer.end();

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(0,0,0,1);
		shapeRenderer.rect(guitarMan.position.x, guitarMan.position.y, guitarMan.position.width, guitarMan.position.height);
		shapeRenderer.end();

		guitarMan.update(collisionObjects);

		// Move camera
		//camera.translate((guitarMan.manVelocity.x - camera.viewportWidth / 80) * Gdx.graphics.getDeltaTime(), 0);
		camera.position.x = guitarMan.position.x + camera.viewportWidth / 2;

		if (guitarMan.position.y + guitarMan.position.height < 0) 
		{
			collisionSound.play();
			resetGame();
		}
		
	}
	
	private void resetGame()
	{
		configureCamera();
		guitarMan.position = new Rectangle(0, 100, 200, 200);
		guitarMan.nextPosition = new Rectangle(0,50,200,200);
		guitarMan.manVelocity = new Vector2(500, 0);
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
	
}
