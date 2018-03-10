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
    Skin skin;
    TextureAtlas buttonAtlas;

    public ButtonsPack() {      
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("output/test-me!.pack"));
        skin.addRegions(buttonAtlas);
        textButtonStyle = new TextButtonStyle();
		font.setScale(4.0f);
        textButtonStyle.font = font;
    }


    public void render() {     
        stage.draw();
    }

	protected TextButton createButton(String text, int x, int y)
	{
		TextButton newButton = new TextButton(text, textButtonStyle);
		newButton.setPosition(x,y);
		stage.addActor(newButton);
		return newButton;
	}
}
