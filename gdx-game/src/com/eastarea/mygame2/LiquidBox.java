package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.*;

public class LiquidBox extends VisibleBox
{
	TextureRegion rockTexture;
	
	LiquidBox(int x, int y, int width, int height)
	{
		super(x, y, width,height);
		Texture texture2 = new Texture(Gdx.files.internal("rock.png"));
		rockTexture = new TextureRegion(texture2, 25, 0, 250, 250);
	}

	@Override
	public void render(ShapeRenderer shape)
	{
		// TODO: Implement this method
	}

	@Override
	public void render(SpriteBatch batch)
	{
		// TODO: Implement this method
		batch.draw(rockTexture, mask.x, mask.y, mask.width, mask.height);
	}

}
