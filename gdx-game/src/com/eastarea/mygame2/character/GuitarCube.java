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
	public Vector2 velocity;
    
    boolean onGround;
    
	int startx = 300;
	int starty = 50;
    int sizex = 50;
	int sizey = 50;
	int speedx = 500;
	int speedy = 700;
	int accely = 1300;
	int gravity = 3000;
    
    TailEffect tail;

    public GuitarCube(BrutalGame game)
    {
		this.game = game;
        reset();
        
        tail = new TailEffect(30) {

            @Override
            public void drawFigure(ShapeRenderer shape, Rectangle f)
            {
                shape.setColor(0.6f, 1, 0.8f, 0.5f);
				float[] c = {f.x, f.y,
								f.x, f.y + sizey,
								f.x + f.width, f.y + f.height,
								f.x + f.width, f.y + f.height - sizey};
				
				shape.triangle(c[0], c[1], c[2], c[3], c[4], c[5]);
				shape.triangle(c[0], c[1], c[4], c[5], c[6], c[7]);
            }
        };
    }
	
	public void reset()
	{
		position = new Rectangle(startx, starty, sizex, sizey);
        nextPosition = new Rectangle(startx, starty, sizex, sizey);
		velocity = new Vector2(0, 0);
        onGround = true;
	}
    
    public void update(GameLevel level) 
    {
        float deltaTime = Gdx.graphics.getDeltaTime();
        nextPosition.x = position.x + velocity.x * deltaTime;
		nextPosition.y = position.y;
		
		List<ICollideable> list = level.checkCollision(nextPosition, level.collisionMap.get(ECollisionType.SOLID), game.session.nextNoteNumber);
		if (!list.isEmpty())
		{
			nextPosition.x = position.x;
			game.session.resetGame();
		}
		
        nextPosition.y = position.y + velocity.y * deltaTime;
		
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
				velocity.y = 0;
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
        
		//tail.addPosition(new Rectangle(position.x, position.y, nextPosition.x - position.x, nextPosition.y + sizey - position.y));
		
        position.x = nextPosition.x ;
        position.y = nextPosition.y;
        
        if (Gdx.input.isTouched() && onGround)
        {
            velocity.y = speedy;
            onGround = false;
		}
		
		if (Gdx.input.isTouched() && !onGround)
		{
			velocity.y += accely * Gdx.graphics.getDeltaTime();
		}
       
        if (!onGround)
            velocity.y -= gravity * Gdx.graphics.getDeltaTime();
        else
            velocity.y= 0;
			
		list = level.checkCollision(nextPosition, level.collisionMap.get(ECollisionType.LIQUID), game.session.nextNoteNumber);
		for (ICollideable c : list)
		{
			c.emitCollision(this);
		}
		
    }
    
    public void run() 
    {
        velocity.x = speedx;
    }
    
    public void stop()
    {
        velocity.x = 0;
    }
    
    @Override
    public void render(ShapeRenderer shape)
    {
		shape.begin(ShapeRenderer.ShapeType.Line);
		
		shape.end();
		
        shape.begin(ShapeRenderer.ShapeType.Filled);
        //tail.render(shape);
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
