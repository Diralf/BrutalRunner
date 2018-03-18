package com.eastarea.mygame2.gui.list;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import java.util.*;

public interface IListItem
{
	public Actor getContainer(int index, ArrayList items);
}
