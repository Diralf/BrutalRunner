package com.eastarea.mygame2;
import com.badlogic.gdx.math.*;

public interface ICollideable
{
	public void emitCollision(ICollideable other);
	public Rectangle getMask();
}
