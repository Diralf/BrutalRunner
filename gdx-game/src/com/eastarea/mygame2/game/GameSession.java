package com.eastarea.mygame2.game;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.eastarea.mygame2.*;
import com.eastarea.mygame2.character.*;
import java.util.*;
import org.apache.poi.ss.formula.functions.*;

public class GameSession
{
    BrutalGame game;
    
    public GameLevel level;
    GuitarCube guitarCube;
    
    CollisionBox startFloor;

    public GameSession(BrutalGame game)
    {
        this.game = game;
        level = new GameLevel(500);
        
        guitarCube = new GuitarCube(game);
		
        startFloor = new SolidBox(0,10,10000,40);
        level.add(0, startFloor);
		level.add(0, (ICollideable) new SolidBox(0, 15, 10000, 40));
		
		level.add(0, (ICollideable) new SolidBox(1000, 120, 10000, 40));
    }

    public void resetGame()
    {
        // TODO: Implement this method
    }
    
    public void update ()
    {
        guitarCube.update(level);
        
        game.camera.position.x = guitarCube.position.x + game.camera.viewportWidth / 2 - 100;
    }
    
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font)
    {
        batch.setProjectionMatrix(game.camera.combined);
        shapeRenderer.setProjectionMatrix(game.camera.combined);
        
        game.background.render(batch);
        
        batch.begin();
        font.setColor(0,0,0,1);
        font.draw(batch, (int) (guitarCube.position.x) + "m", game.camera.position.x - 10, 300);
        batch.end();
       
        level.render(batch, shapeRenderer);
        
        guitarCube.render(shapeRenderer);
    }

    public void dispose()
    {
        // TODO: Implement this method
    }

    public void resize(int width, int height)
    {
        // TODO: Implement this method
    }

    public void pause()
    {
        // TODO: Implement this method
    }

    public void resume()
    {
        // TODO: Implement this method
    }
}
