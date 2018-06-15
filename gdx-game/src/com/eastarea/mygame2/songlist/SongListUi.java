package com.eastarea.mygame2.songlist;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.eastarea.mygame2.*;
import com.eastarea.mygame2.gui.*;
import com.eastarea.mygame2.gui.list.*;
import com.eastarea.mygame2.io.*;
import com.eastarea.mygame2.trackList.*;
import java.io.*;
import java.util.*;
import com.eastarea.mygame2.Menu.*;

public class SongListUi extends GuiTableScene implements IStagable
{

	public SongListUi()
	{
		//ArrayList<File> files = IOFile.listFiles(IOFile.getExtDir(), "mp3");
        ArrayList<File> fileList = (ArrayList<File>) IOFile.fileHandleToFileList(IOFile.listAssetFiles("mp3"));
		SongListItem listItem = new SongListItem(this);
		ScrollList list = new ScrollList(this, fileList, listItem);
		
		TextButton onTopButton = new TextButton("Back to menu", skin);
		onTopButton.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					MyGdxGame.changeStage(new BrutalMainMenu());
				}
			});
		
		container.add(list.scroll).expand().fill().colspan(4);
		container.row();
		container.add(onTopButton);
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
