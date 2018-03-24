package com.eastarea.mygame2;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import java.util.*;

abstract public class TailEffect implements IRenderable
{
   
    LinkedList<Rectangle> list;
    int length =0;

    public TailEffect(int length)
    {
        this.length = length;
        list = new LinkedList<Rectangle>();
    }
    
    @Override
    public void render(ShapeRenderer shape)
    {
        for (Rectangle r: list) {
            drawFigure(shape, r);
        }
    }

    @Override
    public void render(SpriteBatch batch)
    {
        // TODO: Implement this method
    }
    
    public abstract void drawFigure(ShapeRenderer shape, Rectangle figure);
    
    public void addPosition(Rectangle pos)
    {
        if (list.size() > length) {
            list.poll();
        }
        
        list.add(pos);
    }
    
}
