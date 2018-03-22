package com.eastarea.mygame2.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.graphics.glutils.*;

public class GameBackground implements IRenderable
{
    BrutalGame game;
    public TextureRegion backgroundTexture;
    
    public GameBackground(BrutalGame game)
    {
        this.game = game;
        Texture texture = new Texture(Gdx.files.internal("background.jpg"));
        backgroundTexture = new TextureRegion(texture, 0, 0, 563, 563);
        
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
        
        float gcx = game.session.guitarCube.position.x;
        float sx = (((int)(gcx/1600))-1)*800 + gcx/2;
        for (int i = 0; i < 3; i++)
        {
            float x = i * 800 + sx;
            batch.draw(backgroundTexture, x, 0, 800, 800);
        }
        
        batch.enableBlending();
        batch.end();
    }
}
