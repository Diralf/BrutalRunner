package com.eastarea.mygame2.note;
import com.badlogic.gdx.math.*;
import com.eastarea.mygame2.*;

public class NoteItem
{
	public CollisionBox position;
	public NoteItemType type;
	
	public NoteItem(CollisionBox pos, NoteItemType type)
	{
		position = pos;
		this.type = type;
	}
	
	public NoteItem()
	{
		this(new LiquidBox(0, 0, 50, 50), NoteItemType.BONUS);
	}
	
	public NoteItem(int y, NoteItemType type)
	{
		this(new LiquidBox(0, y, 50, 50), type);
	}

}
