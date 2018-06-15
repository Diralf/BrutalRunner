package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.*;

public class FloorBox extends SolidBox
{
    boolean isPlaceMan;
    Color color;
    
    public FloorBox(int x, int y, int width, int heigth)
    {
        super(x, y, width, heigth);
        color = new Color(0, 0, 0.5f, 1);
    }
    
    @Override
    public void render(ShapeRenderer shape)
    {
        shape.begin(ShapeRenderer.ShapeType.Filled);
        // TODO: Implement this method
        if (isPlaceMan)
            shape.setColor(0, 0, 0.3f, 1);
        else
            shape.setColor(color);
        shape.rect(mask.x, mask.y, mask.width-2, mask.height);
        //isPlaceMan = false;
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
        isPlaceMan=true;
    }
    
}
