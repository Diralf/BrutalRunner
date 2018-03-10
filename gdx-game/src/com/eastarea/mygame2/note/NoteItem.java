package com.eastarea.mygame2.note;
import com.badlogic.gdx.math.*;

public class NoteItem
{
	Rectangle position;
	NoteItemType type;
	
	public NoteItem(Rectangle pos, NoteItemType type)
	{
		position = pos;
		this.type = type;
	}
	
	public NoteItem()
	{
		this(new Rectangle(0, 0, 50, 50), NoteItemType.BONUS);
	}
	
	public NoteItem(int y, NoteItemType type)
	{
		this(new Rectangle(0, y, 50, 50), type);
	}

}
