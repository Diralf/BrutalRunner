package com.eastarea.mygame2.note;
import com.badlogic.gdx.math.*;

public class Note
{
	float time;
	Rectangle floor;
	NoteItem item;
	
	public Note(float time, Rectangle rect, NoteItem item)
	{
		this.time = time;
		this.floor = rect;
		this.item = item;
	}
	
	public Note(float time) 
	{
		this(time, new NoteItem());
	}
	
	public Note(float time, NoteItem item)
	{
		this(time, new Rectangle(0, 0, 100, 50), item);
	}
	
	public Note(float time, int y, int yitem, NoteItemType type)
	{
		this(time, new Rectangle(0, y, 100,50), new NoteItem(yitem, type));
	}
	
	public Note(float time, int y)
	{
		this(time, y, y + 50, NoteItemType.BONUS);
	}
}
