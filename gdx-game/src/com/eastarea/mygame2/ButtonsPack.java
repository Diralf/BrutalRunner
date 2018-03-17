package com.eastarea.mygame2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.*;

public class ButtonsPack
{

    public Stage stage;
    TextButtonStyle textButtonStyle;
    BitmapFont font;
    public Skin skin;
    TextureAtlas buttonAtlas;

    public ButtonsPack() {      
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
		//skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		skin = new Skin(Gdx.files.internal("comic/skin/comic-ui.json"));
    }


    public void render() {  
		stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

	protected TextButton createButton(String text, int x, int y)
	{
		TextButton newButton = new TextButton(text, skin);
		newButton.setPosition(x,y);
		stage.addActor(newButton);
		return newButton;
	}
}
