package com.eastarea.mygame2.character;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.eastarea.mygame2.*;

public class GuitarCube implements IRenderable, ICollideable
{
    public Rectangle position;
    public Rectangle nextPosition;
	public Vector2 manVelocity;
    
    boolean onGround;
    
    int size = 50;

    public GuitarCube()
    {
        position = new Rectangle(300, 51, size, size);
        nextPosition = new Rectangle(300, 51, size, size);
		manVelocity = new Vector2(500, 0);
        onGround = true;
    }
    
    public void update() 
    {
        float deltaTime = Gdx.graphics.getDeltaTime();
        nextPosition.x = position.x + manVelocity.x * deltaTime;
        nextPosition.y = position.y + manVelocity.y * deltaTime;
        
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
