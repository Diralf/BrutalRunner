package com.eastarea.mygame2.songlist;
import com.eastarea.mygame2.gui.list.*;
import com.badlogic.gdx.scenes.scene2d.*;
import java.util.*;
import java.io.*;
import com.eastarea.mygame2.*;
import com.eastarea.mygame2.editor.*;

public class SongListItem extends ButtonListItem
{
	
	public SongListItem(GuiScene scene)
	{
		super(scene);
	}

	@Override
	public void buttonAction(int index, File file)
	{
		MyGdxGame.changeStage(new BrutalEditor(file));
	}
	
}
