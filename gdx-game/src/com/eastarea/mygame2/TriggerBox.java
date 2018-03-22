package com.eastarea.mygame2;

public class TriggerBox extends CollisionBox
{

    @Override
    public void emitCollision(ICollideable other)
    {
        // TODO: Implement this method
    }

    @Override
    public ECollisionType getType()
    {
        // TODO: Implement this method
        return ECollisionType.LIQUID;
    }
    
}
