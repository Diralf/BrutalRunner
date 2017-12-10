package com.eastarea.mygame2;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class GuitarMan 
{
	Animation walkAnimation;
	int countFrames;
	
	Rectangle manPosition;
	Vector2 manVelocity;
	
	float time;
	
	boolean onGround;
	
	GuitarMan()
	{
		// Create run animation
        Texture walkSheet = new Texture(Gdx.files.internal("runAnimation.png"));
		int FRAME_COLS = 6;
		int FRAME_ROWS = 5;
		countFrames = FRAME_COLS * FRAME_ROWS;
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
		onGround = true;
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++)
		{
            for (int j = 0; j < FRAME_COLS; j++)
			{
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.025f, walkFrames);
		
	}
	
	public void render(SpriteBatch batch)
	{
		// Draw man
        batch.draw(walkAnimation.getKeyFrame(time, true), manPosition.x, manPosition.y, manPosition.width, manPosition.height);
		time += Gdx.graphics.getDeltaTime();
		if (time > countFrames) time-=countFrames;
	}
	
	public void update()
	{
		manPosition.x += manVelocity.x * Gdx.graphics.getDeltaTime();
		manPosition.y += manVelocity.y * Gdx.graphics.getDeltaTime();
		manVelocity.y -= 1000 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isTouched() && onGround)
		{
			manVelocity.y = 500;
			onGround = false;
		}
		if (manPosition.y < 0) 
		{
			manPosition.y = 0;
			manVelocity.y = 0;
			onGround =true;
		}
	}
	
	public boolean isCollision(Rectangle r)
	{
		return manPosition.overlaps(r) && manPosition.getCenter(new Vector2()).dst(r.getCenter(new Vector2())) < 120 ;
	}
}
