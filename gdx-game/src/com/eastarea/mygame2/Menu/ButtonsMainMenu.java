package com.eastarea.mygame2.Menu;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.eastarea.mygame2.*;

public class ButtonsMainMenu extends ButtonsPack
{

	TextButton gameButton;
	TextButton editorButton;

    public ButtonsMainMenu() {   
	    super();
		gameButton = createButton("Game", 300, 200);
		editorButton = createButton("Editor", 300, 100);
    }
}
