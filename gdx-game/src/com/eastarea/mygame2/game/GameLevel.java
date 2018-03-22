package com.eastarea.mygame2.game;

import com.eastarea.mygame2.*;
import java.util.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;

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
}
