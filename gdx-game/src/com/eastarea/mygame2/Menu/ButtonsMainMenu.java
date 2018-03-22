package com.eastarea.mygame2.Menu;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.eastarea.mygame2.editor.*;
import com.eastarea.mygame2.trackList.*;
import com.eastarea.mygame2.songlist.*;

public class ButtonsMainMenu extends GuiScene
{
	Table container;

	TextButton gameButton;
	TextButton editorButton;
	
    public ButtonsMainMenu() {   
	    super();
		
		//skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		container = new Table();
		//container.debug();
		stage.addActor(container);
		container.setFillParent(true);
		
		gameButton = createButton("Game", 300, 200);
		editorButton = createButton("Editor", 300, 100);
		
		Label nameLabel = new Label("Name:", skin);
		TextField nameText = new TextField("", skin);
		Label addressLabel = new Label("Address:", skin);
		TextField addressText = new TextField("", skin);
		
		
		container.add(gameButton).pad(20).left();
		container.row();
		container.add(editorButton);
		
		gameButton.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
					MyGdxGame.changeStage(new TrackListUI());
					return true;
				}
			});

		editorButton.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
					MyGdxGame.changeStage(new SongListUi());
					return true;
				}
			});
		
		/*
		Table table = new Table();
		 //table.debug();

		final ScrollPane scroll = new ScrollPane(table, skin);

		InputListener stopTouchDown = new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				event.stop();
				return false;
			}
		};

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

		final TextButton flickButton = new TextButton("Flick ", skin.get("toggle", TextButtonStyle.class));
		flickButton.setChecked(true);
		flickButton.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					scroll.setFlickScroll(flickButton.isChecked());
				}
			});

		final TextButton fadeButton = new TextButton("Fade", skin.get("toggle", TextButtonStyle.class));
		fadeButton.setChecked(true);
		fadeButton.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					scroll.setFadeScrollBars(fadeButton.isChecked());
				}
			});

		final TextButton smoothButton = new TextButton("Smooth", skin.get("toggle", TextButtonStyle.class));
		smoothButton.setChecked(true);
		smoothButton.addListener(new ChangeListener() {
				public void changed (ChangeEvent event, Actor actor) {
					scroll.setSmoothScrolling(smoothButton.isChecked());
				}
			});

		final TextButton onTopButton = new TextButton("On Top", skin.get("toggle", TextButtonStyle.class));
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
		*/
    }
}
