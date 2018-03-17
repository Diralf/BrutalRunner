package com.eastarea.mygame2.trackList;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import android.app.backup.*;
import com.eastarea.mygame2.io.*;
import java.util.*;
import java.io.*;

public class TrackListUI extends ButtonsPack
{
	Table container;
	public TrackListUI()
	{
		container = new Table();
		container.debug();
		stage.addActor(container);
		container.setFillParent(true);
		
		Table table = new Table();
		//table.debug();

		final ScrollPane scroll = new ScrollPane(table, skin);

		InputListener stopTouchDown = new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				event.stop();
				return false;
			}
		};
		
		ArrayList<File> files = IOFile.listFiles(IOFile.getExtDir(), "txt");

		table.pad(10).defaults().expandX().space(4);
		for (int i = 0; i < 100; i++) {
			table.row();
			table.add(new Label(i + "uno", skin)).expandX().fillX();

			TextButton button = new TextButton(i + "dos", skin);
			table.add(button);
			button.addListener(new ClickListener() {
					public void clicked (InputEvent event, float x, float y) {
						System.out.println("click " + x + ", " + y);
					}
				});

			Slider slider = new Slider(0, 100, 1, false, skin);
			slider.addListener(stopTouchDown); // Stops touchDown events from propagating to the FlickScrollPane.
			table.add(slider);

			table.add(new Label(i + "tres long0 long1 long2 long3 long4 long5 long6 long7 long8 long9 long10 long11 long12", skin));
		}

		final TextButton flickButton = new TextButton("Flick ", skin);
		flickButton.setChecked(true);
		flickButton.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					scroll.setFlickScroll(flickButton.isChecked());
				}
			});

		final TextButton fadeButton = new TextButton("Fade", skin);
		fadeButton.setChecked(true);
		fadeButton.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					scroll.setFadeScrollBars(fadeButton.isChecked());
				}
			});

		final TextButton smoothButton = new TextButton("Smooth", skin);
		smoothButton.setChecked(true);
		smoothButton.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					scroll.setSmoothScrolling(smoothButton.isChecked());
				}
			});

		final TextButton onTopButton = new TextButton("On Top", skin);
		onTopButton.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					scroll.setScrollbarsOnTop(onTopButton.isChecked());
				}
			});

		container.add(scroll).expand().fill().colspan(4);
		container.row().space(10).padBottom(10);
		container.add(flickButton).right().expandX();
		container.add(onTopButton);
		container.add(smoothButton);
		container.add(fadeButton).left().expandX();
	}
}
