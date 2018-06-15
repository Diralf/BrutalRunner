package com.eastarea.mygame2.gui.list;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.eastarea.mygame2.*;
import java.io.*;
import java.util.*;

public abstract class ButtonListItem implements IListItem
{
	HorizontalGroup line;
	GuiScene scene;

	public ButtonListItem(GuiScene scene) {
		this.scene = scene;
	}

	@Override
	public Actor getContainer(final int index, ArrayList items)
	{
		final File file = (File)items.get(index);
		line = new HorizontalGroup();
		//line.addActor(new Label(index + ". " + file.getName().substring(0, file.getName().indexOf('.')), scene.skin));

		TextButton button = new TextButton(index + ". " + file.getName()/*.substring(0, file.getName().indexOf('.'))*/, scene.skin);
		line.addActor(button);
		button.addListener(new ClickListener() {
				public void clicked (InputEvent event, float x, float y) {
					buttonAction(index, file);
				}
			});
		// TODO: Implement this method
		return line;
	}
	
	public abstract void buttonAction(int index, File file);
}
