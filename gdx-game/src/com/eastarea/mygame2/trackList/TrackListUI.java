package com.eastarea.mygame2.trackList;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import android.app.backup.*;
import com.eastarea.mygame2.io.*;
import java.util.*;
import java.io.*;
import org.apache.poi.ss.formula.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.eastarea.mygame2.gui.*;
import com.eastarea.mygame2.gui.list.*;

public class TrackListUI extends GuiTableScene implements IStagable
{
	
	public TrackListUI()
	{
		ArrayList<File> files = IOFile.listFiles(IOFile.getExtDir(), "txt");
		ScrollList list = new ScrollList(this, files, new TrackListItem(this));
		
		
		/*VerticalGroup table = new VerticalGroup();
		//table.debug();

		final ScrollPane scroll = new ScrollPane(table, skin);
		
		//ArrayList<File> files = IOFile.listFiles(IOFile.getExtDir(), "txt");

		table.pad(10).space(4);
		for (int i = 0; i < files.size(); i++) {
			//table.addActor(new TrackListItem(this, i, files.get(i)).getContainer());
		}*/

//		final TextButton flickButton = new TextButton("Flick ", skin);
//		flickButton.setChecked(true);
//		flickButton.addListener(new ChangeListener() {
//				public void changed (ChangeEvent event, Actor actor) {
//					scroll.setFlickScroll(flickButton.isChecked());
//				}
//			});
//
//		final TextButton fadeButton = new TextButton("Fade", skin);
//		fadeButton.setChecked(true);
//		fadeButton.addListener(new ChangeListener() {
//				public void changed (ChangeEvent event, Actor actor) {
//					scroll.setFadeScrollBars(fadeButton.isChecked());
//				}
//			});
//
//		final TextButton smoothButton = new TextButton("Smooth", skin);
//		smoothButton.setChecked(true);
//		smoothButton.addListener(new ChangeListener() {
//				public void changed (ChangeEvent event, Actor actor) {
//					scroll.setSmoothScrolling(smoothButton.isChecked());
//				}
//			});
//
//		final TextButton onTopButton = new TextButton("On Top", skin);
//		onTopButton.addListener(new ChangeListener() {
//				public void changed (ChangeEvent event, Actor actor) {
//					scroll.setScrollbarsOnTop(onTopButton.isChecked());
//				}
//			});

		container.add(list.scroll).expand().fill().colspan(4);
//		container.row().space(10).padBottom(10);
//		container.add(flickButton).right().expandX();
//		container.add(onTopButton);
//		container.add(smoothButton);
//		container.add(fadeButton).left().expandX();
	}
	
	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font)
	{
		super.render();
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}
}
