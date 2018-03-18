package com.eastarea.mygame2.gui.list;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.eastarea.mygame2.*;
import java.util.*;

public class ScrollList
{
	public VerticalGroup table;
	public ScrollPane scroll;
	
	public ScrollList(GuiScene scene, ArrayList items, IListItem listItem) {
		table = new VerticalGroup();
		//table.debug();
		scroll = new ScrollPane(table, scene.skin);

		table.pad(10).space(4);
		for (int i = 0; i < items.size(); i++) {
			table.addActor(listItem.getContainer(i, items));
		}
	}
}
