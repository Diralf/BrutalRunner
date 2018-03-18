package com.eastarea.mygame2.trackList;
import com.eastarea.mygame2.gui.list.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import java.io.*;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import java.util.*;

public class TrackListItem extends ButtonListItem
{
	
	public TrackListItem(GuiScene scene)
	{
		super(scene);
	}

	@Override
	public void buttonAction(int index, File file)
	{
		MyGdxGame.changeStage(new BrutalGame(file));
	}
	
	
	
}
