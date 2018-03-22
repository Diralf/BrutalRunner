package com.eastarea.mygame2.game;

import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;
import com.eastarea.mygame2.*;
import java.util.*;

public class GameLevel
{
    public LevelList<IRenderable> renderList;
    public Map<ECollisionType, LevelList<ICollideable>> collisionMap;
    
    int LEVEL_LENGTH = 500;
    
    public GameLevel(int levelLength)
    {
        LEVEL_LENGTH = levelLength;
        
        renderList = new LevelList<IRenderable>(LEVEL_LENGTH);
        collisionMap = new HashMap<ECollisionType, LevelList<ICollideable>>();             
        collisionMap.put(ECollisionType.SOLID, new LevelList<ICollideable>(LEVEL_LENGTH));
        collisionMap.put(ECollisionType.LIQUID, new LevelList<ICollideable>(LEVEL_LENGTH));
		
    }
    
    public void add(int index, ICollideable item)
    {
        collisionMap.get(item.getType()).add(index, item);
        
        try {
            add(index, (IRenderable)item);
        } catch (ClassCastException e) {
            System.err.println(e);
        }
    }
    
    public void add(int index, IRenderable item)
    {
        renderList.add(index, item);
    }
    
    public void render(SpriteBatch batch, ShapeRenderer shape)
    {
        for (List<IRenderable> b: renderList.list)
            for (IRenderable r: b)
		    {   
                r.render(batch);
                r.render(shape);
            }
    }
	
	public List<ICollideable> checkCollision(Rectangle first, LevelList<ICollideable> collisions, int position)
	{
	    List<ICollideable> list = new ArrayList<ICollideable>();
		int start =  position - 1;
		int end = position + 3;
		int size = collisions.list.size();

		if (start < 0) start = 0;
		if (end > size) end = size;
		// Detect collision
		for (int i = start; i < end; i++)
		{
			for (ICollideable other : collisions.list.get(i))
			{
				if (first.overlaps(other.getMask()))
				{
					list.add(other);
				}
			}
		}
		return list;
	}
}
