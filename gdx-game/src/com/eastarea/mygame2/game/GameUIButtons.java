package com.eastarea.mygame2.game;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.eastarea.mygame2.*;

public class GameUIButtons extends GuiScene
{
	
	public TextButton back;
	public TextButton start;
	public TextButton pause;
	
	public GameUIButtons(BrutalGame game) {
		super();
		back = createButton("Back", 0, 0);
		start = createButton("Start", 200,0);
		pause = createButton("Pause", 400, 0);
	}
}
