package com.eastarea.mygame2;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.glutils.*;
import java.util.*;
import android.graphics.drawable.shapes.*;
import android.widget.ExpandableListView.*;

public class GuitarMan implements IRenderable
{

	

	Animation walkAnimation;
	int countFrames;
	
	Rectangle position;
	Rectangle nextPosition;
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
	
	@Override
	public void render(SpriteBatch batch)
	{
		// Draw man
        batch.draw(walkAnimation.getKeyFrame(time, true), position.x, position.y, position.width, position.height);
		time += Gdx.graphics.getDeltaTime();
		if (time > countFrames) time-=countFrames;
	}
	
	@Override
	public void render(ShapeRenderer shape)
	{
		// TODO: Implement this method
	}
	
	public void update(List<List<Rectangle>> collisionObjects)
	{
		nextPosition.x = position.x + manVelocity.x * Gdx.graphics.getDeltaTime();
		nextPosition.y = position.y;
		
		Rectangle meetRect =checkCollision(nextPosition, collisionObjects, 200);
		if (meetRect != null)
		{
			nextPosition.x = position.x;
		}
		
		nextPosition.y = position.y + manVelocity.y * Gdx.graphics.getDeltaTime();
		meetRect =checkCollision(nextPosition, collisionObjects, 200);
		if (meetRect != null)
		{
			if (position.y > meetRect.y + meetRect.height)
			{
				nextPosition.y = meetRect.y + meetRect.height;
			} else {
				nextPosition.y = position.y;
			}
			onGround =true;
		} else {
			onGround = false;
		}
		
		position.x = nextPosition.x ;
		position.y = nextPosition.y;
		if (!onGround)
			manVelocity.y -= 1500 * Gdx.graphics.getDeltaTime();
		else
			manVelocity.y= 0;
			
		if (Gdx.input.isTouched() && onGround)
		{
			manVelocity.y = 500;
			onGround = false;
		}
		
	}
	
	public Rectangle checkCollision(Rectangle first, List<List<Rectangle>> collisionObjects, int cellSize)
	{
		int indMan =(int) first.x /cellSize;
		// Detect collision
		for (int i = indMan - 1; i < indMan + 3; i++)
		{
			if (i<0) continue;
			for (Rectangle other : collisionObjects.get(i))
			{
				if (first.overlaps(other))
				{
					return other;// make check for all rect
				}
			}
		}
		return null;
	}
	
	public boolean isCollision(Rectangle r)
	{
		return position.overlaps(r) && position.getCenter(new Vector2()).dst(r.getCenter(new Vector2())) < 120 ;
	}
	
	public void handleCollision(Rectangle other)
	{
		
//		if (position.y < (other.y) && (position.x+ position.width)< other.x+200) 
//		{
//			manVelocity.x = 0;
//			position.x = other.x - position.width;
//		} else {
//			if (!onGround) {
//				manVelocity.y = 0;
//				position.y = other.y + other.height;
//				onGround=true;
//			}
//		}
	}
}
