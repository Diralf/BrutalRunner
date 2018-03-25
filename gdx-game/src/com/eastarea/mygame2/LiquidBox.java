package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;

public class LiquidBox extends CollisionBox implements IRenderable
{
	
	//TextureRegion rockTexture;
	float color;
	int heightBox;
	//boolean isHit;
	//boolean isHitOnce;
	
	public LiquidBox(int x, int y, int width, int height)
	{
		super(x, y, width,height);
		//Texture texture2 = new Texture(Gdx.files.internal("rock.png"));
		//rockTexture = new TextureRegion(texture2, 25, 0, 250, 250);
		color = 1;
		//isHit = false;
	}

	@Override
	public void render(ShapeRenderer shape)
	{
		//if (isHit) return;
		shape.begin(ShapeRenderer.ShapeType.Filled);
		// TODO: Implement this method
        shape.setColor(1,color,color,1);
		shape.circle(mask.x+mask.width/2, mask.y+mask.height/2, heightBox);
        shape.setColor(1,1,1,1);
		color = 1;
        heightBox = (int) mask.height/2;
		shape.end();
	}

	@Override
	public void render(SpriteBatch batch)
	{
		// TODO: Implement this method
//		batch.setColor(1,color,color,1);
//		batch.draw(rockTexture, mask.x, mask.y, mask.width, heightBox);
//		batch.setColor(1,1,1,1);
//		color = 1;
//		heightBox = (int) mask.height;
	}

	@Override
	public void emitCollision(ICollideable other)
	{
	     color = 0;
		 heightBox = 10;
		 //isHit = true;
	}

    @Override
    public ECollisionType getType()
    {
        return ECollisionType.LIQUID;
    }

}
