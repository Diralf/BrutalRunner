package com.eastarea.mygame2.editor;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;

public class ButtonExample
{

    Stage stage;
    TextButton button;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
	TextButton startButton;
	TextButton hitButton;
	TextButton saveButton;
	TextButton loadButton;
    
    public void create() {      
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("output/test-me!.pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
		font.setScale(4.0f);
        textButtonStyle.font = font;
        //textButtonStyle.up = skin.getDrawable("up-button");
        //textButtonStyle.down = skin.getDrawable("down-button");
        //textButtonStyle.checked = skin.getDrawable("checked-button");
//        )button = new TextButton("Button1", textButtonStyle);
//        stage.addActor(button);
//		button.setX(100);
//		
//		button.addListener(new ChangeListener() {
//				@Override
//				public void changed (ChangeEvent event, Actor actor) {
//					System.out.println("Button Pressed");
//					System.out.println(event.toString());
//				}
//				
//				
//			});
			
		startButton = createButton(stage, "Start", 0, 0);
		hitButton = createButton(stage, "HIT", 700, 0);
		saveButton = createButton(stage, "Save", 0, 300);
		loadButton = createButton(stage, "Load", 0, 200);
    }

   
    public void render() {     
        stage.draw();
    }
	
	private TextButton createButton(Stage stage, String text, int x, int y)
	{
		TextButton newButton = new TextButton(text, textButtonStyle);
		newButton.setPosition(x,y);
		stage.addActor(newButton);
		return newButton;
	}
}
