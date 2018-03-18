package com.eastarea.mygame2.gui;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public abstract class GuiTableScene extends GuiScene
{
	public Table container;
	
	public GuiTableScene() {
		container = new Table();
		container.debug();
		stage.addActor(container);
		container.setFillParent(true);
	}
}
