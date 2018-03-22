package com.eastarea.mygame2.character;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.eastarea.mygame2.*;
import java.util.*;
import com.eastarea.mygame2.game.*;

public class GuitarCube implements IRenderable, ICollideable
{
	BrutalGame game;
	
    public Rectangle position;
    public Rectangle nextPosition;
	public Vector2 manVelocity;
    
    boolean onGround;
    
    int size = 50;

    public GuitarCube(BrutalGame game)
    {
		this.game = game;
        position = new Rectangle(300, 51, size, size);
        nextPosition = new Rectangle(300, 51, size, size);
		manVelocity = new Vector2(500, 0);
        onGround = true;
    }
    
    public void update(GameLevel level) 
    {
        float deltaTime = Gdx.graphics.getDeltaTime();
        nextPosition.x = position.x + manVelocity.x * deltaTime;
		nextPosition.y = position.y;
		
		List<ICollideable> list = level.checkCollision(nextPosition, level.collisionMap.get(ECollisionType.SOLID), 0);
		if (!list.isEmpty())
		{
			nextPosition.x = position.x;
		}
		
        nextPosition.y = position.y + manVelocity.y * deltaTime;
		
		list = level.checkCollision(nextPosition, level.collisionMap.get(ECollisionType.SOLID), 0);
		if (!list.isEmpty())
		{
			//nextPosition.y = position.y;
			ICollideable meetRect = list.get(0);
			float minDist = manVelocity.y;
			float higest = 0;
			float lowest = 10000;
			for (ICollideable c : list)
			{
				float high = c.getMask().y + c.getMask().height;
				float low = c.getMask().y;
				if (high > higest) {
					higest = high;
				}
				if (low < lowest) {
					lowest = low;
				}
				c.emitCollision(this);
			}
			if (position.y < lowest) {
				nextPosition.y = lowest - position.height;
				manVelocity.y = 0;
				onGround =false;
			} else {
				nextPosition.y = higest;
				onGround =true;
			}
			//meetRect.emitCollision(this);
			//Rectangle meetMask = meetRect.getMask();
//			if (position.y > meetMask.y + meetMask.height)
//			{
//				nextPosition.y = meetMask.y + meetMask.height;
//			} else {
//				nextPosition.y = position.y;
//			}
			
		} else {
			onGround = false;
		}
        
        if (nextPosition.y < 0) 
        {
            nextPosition.y = 0;
            onGround = true;
        }
        
        position.x = nextPosition.x ;
        position.y = nextPosition.y;
        
        if (Gdx.input.isTouched() && onGround)
        {
            manVelocity.y = 500;
            onGround = false;
		}
       
        if (!onGround)
            manVelocity.y -= 1500 * Gdx.graphics.getDeltaTime();
        else
            manVelocity.y= 0;
    }
    
    @Override
    public void render(ShapeRenderer shape)
    {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(0.3f, 0.7f, 0.5f, 1);
        shape.rect(position.x, position.y, size, size);
        shape.end();
    }

    @Override
    public void render(SpriteBatch batch)
    {
        // TODO: Implement this method
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
        return null;
    }

    @Override
    public ECollisionType getType()
    {
        // TODO: Implement this method
        return ECollisionType.SOLID;
    }

    
    
}
