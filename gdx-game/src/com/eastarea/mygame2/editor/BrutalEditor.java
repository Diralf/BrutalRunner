package com.eastarea.mygame2.editor;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.*;

public class BrutalEditor implements IStagable
{

	OrthographicCamera camera;
	TextureRegion backgroundTexture;
	float[] notes;
	float position;
	int noteNumber;
	float cooldown = 0.2f;

	Music music;
	
	ButtonExample button;
	
	public BrutalEditor()
	{
		Texture texture = new Texture(Gdx.files.internal("skyBackground.jpg"));
		backgroundTexture = new TextureRegion(texture, 0, 0, 2048, 563);
		music = Gdx.audio.newMusic(Gdx.files.internal("SevenNationArmy.mp3"));
		
		notes = new float[1000];
		
		camera = new OrthographicCamera();
		if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth())
			camera.setToOrtho(false, 800, 800 * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
		else
			camera.setToOrtho(false, 800 * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), 800);
		
		button = new ButtonExample();
		button.create();
	}
	
	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font)
	{
		// TODO: Implement this method
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
        batch.begin();

		// Draw background
		for (int i = 0; i < 30; i++)
			batch.draw(backgroundTexture, i * 2900, 0, 2900, 800);

		//font.draw(batch, (int) (manPosition.x / 70) + "m", camera.position.x - 10, 30);
		for (int i=0; i< noteNumber; i++)
			font.draw(batch, notes[i]+"", camera.position.x - 10, 30+noteNumber*15-i*15);

		if (position < 3) {
			font.draw(batch, 3-position+"", camera.position.x - 10, 30-15);
		}
        batch.end();


		if (Gdx.input.isTouched())
		{
			if (noteNumber>0 && music.getPosition() - notes[noteNumber-1] < cooldown) 
			{

			} else {
				notes[noteNumber] = music.getPosition();
				noteNumber++;
			}
		}

		if (position> 3 && !music.isPlaying()) {
			music.play();
		}

		position += Gdx.graphics.getDeltaTime();
		button.render();
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}
	
}
