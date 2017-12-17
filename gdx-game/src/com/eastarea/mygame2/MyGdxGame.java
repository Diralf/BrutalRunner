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
	OrthographicCamera camera;
	TextureRegion backgroundTexture;
	TextureRegion rockTexture;
	Sound collisionSound;
	BitmapFont font;
    SpriteBatch batch;
	GuitarMan guitarMan;
	int cellSize;

	ShapeRenderer shapeRenderer;
	
	List<SolidBox> solidBoxes;
	List<List<Rectangle>> collisionObjects;

    @Override
    public void create()
	{
		cellSize = 200;
		shapeRenderer = new ShapeRenderer();
		// Load background 
		Texture texture = new Texture(Gdx.files.internal("skyBackground.jpg"));
		backgroundTexture = new TextureRegion(texture, 0, 0, 2048, 563);

		// Load and position rocks
		Texture texture2 = new Texture(Gdx.files.internal("rock.png"));
		rockTexture = new TextureRegion(texture2, 25, 0, 250, 250);
		solidBoxes = new ArrayList<SolidBox>();
		collisionObjects = new ArrayList<List<Rectangle>>();
		
		for (int i=0; i<300; i++)
		{
			collisionObjects.add(new ArrayList<Rectangle>());
		}
		
		for (int i=0; i<30;i++) 
		{
			int yBox = 0;
			if ((i % 10) == 0) yBox = 50;
			SolidBox box = new SolidBox(i*cellSize, yBox, cellSize, 50);
			solidBoxes.add(box);
			collisionObjects.get(i).add(box.mask);
		}

		guitarMan = new GuitarMan();
		
		font = new BitmapFont();

		collisionSound = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));

        batch = new SpriteBatch();
		camera = new OrthographicCamera();
		resetGame();
    }

    @Override
    public void render()
	{
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
        batch.begin();

		// Draw background
		for (int i = 0; i < 30; i++)
			batch.draw(backgroundTexture, i * 2900, 0, 2900, 800);

		// Draw rocks
		
			
		

		guitarMan.render(batch);
		
		font.draw(batch, (int) (guitarMan.position.x / 70) + "m", camera.position.x - 10, 30);
        batch.end();
		
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		for (SolidBox b: solidBoxes)
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

//		int indMan =(int) guitarMan.position.x /cellSize;
//		// Detect collision
//		for (int i = indMan - 1; i < indMan + 3; i++)
//		{
//			if (i<0) continue;
//			for (Rectangle r : collisionObjects.get(i))
//			{
//				if (guitarMan.isCollision(r))
//				{
//					//collisionSound.play();
//					//resetGame();
//					guitarMan.handleCollision(r);
//					break;
//				}
//			}
//		}
//		for (Rectangle r : rockPositions)
//		{
//			if (r.x > guitarMan.manPosition.x - 300 && guitarMan.isCollision(r))
//			{
//				collisionSound.play();
//				resetGame();
//				break;
//			}
//		}
		
    }

	private void resetGame()
	{
		configureCamera();
		guitarMan.position = new Rectangle(0, 50, 200, 200);
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

	@Override
    public void dispose()
	{
        batch.dispose();
    }

    @Override
    public void resize(int width, int height)
	{
		resetGame();
    }

    @Override
    public void pause()
	{
    }

    @Override
    public void resume()
	{
    }
}
