package com.eastarea.mygame2.editor;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.eastarea.mygame2.*;

public class ButtonExample extends ButtonsPack 
{
	Table container;
	
	TextButton startButton;
	TextButton hitButton;
	TextButton saveButton;
	TextButton loadButton;
	TextButton menuButton;
    
    public ButtonExample() {      
			
		startButton = createButton( "Start", 0, 0);
		hitButton = createButton("HIT", 700, 0);
		saveButton = createButton("Save", 0, 300);
		loadButton = createButton("Load", 0, 200);
		menuButton = createButton("Back to menu", 0, 400);
    }
}
