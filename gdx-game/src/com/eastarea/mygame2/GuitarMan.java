package com.eastarea.mygame2;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.glutils.*;
import java.util.*;
import android.graphics.drawable.shapes.*;
import android.widget.ExpandableListView.*;

public class GuitarMan implements IRenderable, ICollideable
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
	
	public void update(Map<ECollisionType, CollisionList> collisions, int cellSize)
	{
		nextPosition.x = position.x + manVelocity.x * Gdx.graphics.getDeltaTime();
		nextPosition.y = position.y;
		
		List<ICollideable> list =checkCollision(nextPosition, collisions.get(ECollisionType.SOLID), cellSize);
		if (!list.isEmpty())
		{
			nextPosition.x = position.x;
		}
		
		nextPosition.y = position.y + manVelocity.y * Gdx.graphics.getDeltaTime();
		list =checkCollision(nextPosition, collisions.get(ECollisionType.SOLID), cellSize);
		if (!list.isEmpty())
		{
			ICollideable meetRect = list.get(0);
			float minDist = manVelocity.y;
			for (ICollideable c : list)
			{
				float dist = position.y - c.getMask().y - c.getMask().height;
				if (dist < minDist && dist > 0)
				{
					meetRect = c;
				}
				c.emitCollision(this);
			}
			//meetRect.emitCollision(this);
			Rectangle meetMask = meetRect.getMask();
			if (position.y > meetMask.y + meetMask.height)
			{
				nextPosition.y = meetMask.y + meetMask.height;
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
		
		list =checkCollision(nextPosition, collisions.get(ECollisionType.LIQUID), cellSize);
		for (ICollideable c : list)
		{
			c.emitCollision(this);
		}
		
	}
	
	public List<ICollideable> checkCollision(Rectangle first, CollisionList collisions, int cellSize)
	{
	    List<ICollideable> list = new ArrayList<ICollideable>();
		int indMan =(int) first.x /cellSize;
		// Detect collision
		for (int i = indMan - 1; i < indMan + 3; i++)
		{
			if (i<0) continue;
			for (ICollideable other : collisions.list.get(i))
			{
				if (first.overlaps(other.getMask()))
				{
					list.add(other);
					//return other;// make check for all rect
				}
			}
		}
		return list;
	}

	@Override
	public void emitCollision(ICollideable other)
	{
		// TODO: Implement this method
	}

	@Override
	public Rectangle getMask()
	{
		// TODO: Implement this method
		return position;
	}
	
}
