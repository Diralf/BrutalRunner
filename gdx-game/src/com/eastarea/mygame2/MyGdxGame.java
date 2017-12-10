package com.eastarea.mygame2;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.math.*;
import java.util.*;

public class MyGdxGame implements ApplicationListener
{
	OrthographicCamera camera;
	TextureRegion backgroundTexture;
	TextureRegion rockTexture;
	Sound collisionSound;
	BitmapFont font;
    SpriteBatch batch;
	GuitarMan guitarMan;


	List<Rectangle> rockPositions;
	List<List<Rectangle>> collisionObjects;

    @Override
    public void create()
	{
		// Load background 
		Texture texture = new Texture(Gdx.files.internal("skyBackground.jpg"));
		backgroundTexture = new TextureRegion(texture, 0, 0, 2048, 563);

		// Load and position rocks
		Texture texture2 = new Texture(Gdx.files.internal("rock.png"));
		rockTexture = new TextureRegion(texture2, 25, 0, 250, 250);
		rockPositions = new ArrayList<Rectangle>();
		collisionObjects = new ArrayList<List<Rectangle>>();
		
		for (int i=0; i<100; i++)
		{
			collisionObjects.add(new ArrayList<Rectangle>());
		}
		
		int x = 1800;
		for (int i = 0; i < 60; i++)
		{
			Rectangle rect =new Rectangle(x, 0, 100, 100);
			rockPositions.add(rect);
			collisionObjects.get((int)Math.floor(x/1800)).add(rect);
			x += 600 + new Random().nextInt(600);
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
		for (Rectangle r : rockPositions)
			batch.draw(rockTexture, r.x, r.y, r.width, r.height);

		guitarMan.render(batch);
		
		font.draw(batch, (int) (guitarMan.manPosition.x / 70) + "m", camera.position.x - 10, 30);
        batch.end();

		guitarMan.update();

		// Move camera
		camera.translate((guitarMan.manVelocity.x - camera.viewportWidth / 80) * Gdx.graphics.getDeltaTime(), 0);

		int indMan =(int) guitarMan.manPosition.x /1800;
		// Detect collision
		for (int i = indMan - 1; i < indMan + 2; i++)
		{
			if (i<0) continue;
			for (Rectangle r : collisionObjects.get(i))
			{
				if (guitarMan.isCollision(r))
				{
					collisionSound.play();
					resetGame();
					break;
				}
			}
		}
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
		guitarMan.manPosition = new Rectangle(0, 0, 200, 200);
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
