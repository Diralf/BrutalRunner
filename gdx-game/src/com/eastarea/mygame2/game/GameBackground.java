package com.eastarea.mygame2.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.graphics.glutils.*;

public class GameBackground implements IRenderable
{
    BrutalGame game;
    public TextureRegion backgroundTexture0;
	public TextureRegion backgroundTexture1;
	public TextureRegion backgroundTexture2;
	public TextureRegion backgroundTexture3;
    public int size = 1126;
    public GameBackground(BrutalGame game)
    {
        this.game = game;
        Texture texture = new Texture(Gdx.files.internal("background0.jpg"));
        backgroundTexture0 = new TextureRegion(texture, 0, 0, size, size);
		texture = new Texture(Gdx.files.internal("background1.png"));
        backgroundTexture1= new TextureRegion(texture, 0, 0, size, size);
		texture = new Texture(Gdx.files.internal("background2.png"));
        backgroundTexture2 = new TextureRegion(texture, 0, 0, size, size);
		texture = new Texture(Gdx.files.internal("background3.png"));
        backgroundTexture3 = new TextureRegion(texture, 0, 0, size, size);
        
    }
    
    @Override
    public void render(ShapeRenderer shape)
    {
        // TODO: Implement this method
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.begin();
        batch.disableBlending();
        
        renderBack(batch, backgroundTexture0, 0.0625f);
        
        batch.enableBlending();
		
		renderBack(batch, backgroundTexture1, 0.125f);
        renderBack(batch, backgroundTexture2, 0.25f);
        renderBack(batch, backgroundTexture3, 0.5f);
        
        batch.end();
    }
	
	void renderBack(SpriteBatch batch, TextureRegion backgroundTexture, float speed)
	{
		float gcx = game.session.guitarCube.position.x;
        float sx = (((int)(gcx*speed/800))-1)*800 + (gcx - gcx*speed);
        for (int i = 0; i < 3; i++)
        {
            float x = i * 800 + sx;
            batch.draw(backgroundTexture, x, 0, 800, 800);
        }
	}
}
