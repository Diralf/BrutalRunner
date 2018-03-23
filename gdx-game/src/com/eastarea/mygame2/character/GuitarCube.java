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
    
    int sizex = 50;
	int sizey = 100;
	int speedx = 500;
	int speedy = 700;
	int accely = 1300;
	int gravity = 3000;

    public GuitarCube(BrutalGame game)
    {
		this.game = game;
        position = new Rectangle(300, 51, sizex, sizey);
        nextPosition = new Rectangle(300, 51, sizex, sizey);
		manVelocity = new Vector2(speedx, 0);
        onGround = true;
    }
    
    public void update(GameLevel level) 
    {
        float deltaTime = Gdx.graphics.getDeltaTime();
        nextPosition.x = position.x + manVelocity.x * deltaTime;
		nextPosition.y = position.y;
		
		List<ICollideable> list = level.checkCollision(nextPosition, level.collisionMap.get(ECollisionType.SOLID), game.session.nextNoteNumber);
		if (!list.isEmpty())
		{
			nextPosition.x = position.x;
		}
		
        nextPosition.y = position.y + manVelocity.y * deltaTime;
		
		list = level.checkCollision(nextPosition, level.collisionMap.get(ECollisionType.SOLID), game.session.nextNoteNumber);
		if (!list.isEmpty())
		{
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
            manVelocity.y = speedy;
            onGround = false;
		}
		
		if (Gdx.input.isTouched() && !onGround)
		{
			manVelocity.y += accely * Gdx.graphics.getDeltaTime();
		}
       
        if (!onGround)
            manVelocity.y -= gravity * Gdx.graphics.getDeltaTime();
        else
            manVelocity.y= 0;
			
		list = level.checkCollision(nextPosition, level.collisionMap.get(ECollisionType.LIQUID), game.session.nextNoteNumber);
		for (ICollideable c : list)
		{
			c.emitCollision(this);
		}
    }
    
    @Override
    public void render(ShapeRenderer shape)
    {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(0.3f, 0.7f, 0.5f, 1);
        shape.rect(position.x, position.y, position.width, position.height);
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
