package com.eastarea.mygame2.note;
import com.badlogic.gdx.math.*;
import com.eastarea.mygame2.*;

public class Note
{
	public float time;
	public CollisionBox floor;
	public NoteItem item;
	
	public Note(float time, CollisionBox rect, NoteItem item)
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
		this(time, new SolidBox(0, 0, 100, 50), item);
	}
	
	public Note(float time, int y, int h, int yitem, NoteItemType type)
	{
		this(time, new SolidBox(0, y, 100, h), new NoteItem(yitem, type));
	}
	
	public Note(float time, int y)
	{
		this(time, y,50,  y + 50, NoteItemType.BONUS);
	}
	
	public void updateOX(Note prevNote, float speed, int offset)
	{
		int resultPosition = (int)(time * speed) + offset;
		floor.getMask().x = resultPosition;
		item.position.getMask().x = resultPosition;
		
		Rectangle prevMask = prevNote.floor.getMask();
		prevMask.width = resultPosition - prevMask.x;
	}
}
