package com.eastarea.mygame2.game;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.eastarea.mygame2.*;
import com.eastarea.mygame2.Menu.*;

public class GameUIButtons extends GuiScene
{
	
	public TextButton back;
	public TextButton start;
	public TextButton pause;
	
	public GameUIButtons(final BrutalGame game) {
		super();
		back = createButton("Back", 0, 0);
		start = createButton("Start", 200,0);
		pause = createButton("Pause", 400, 0);
 
        back.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
                    MyGdxGame.changeStage(new BrutalMainMenu());
                    return true;
                }
            });

        start.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
                    //game.grun.resetGame();
                    game.session.resetGame();
                    return true;
                }
            });

        pause.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
                    //game.grun.backMusic.pause();
                    //game.grun.backMusic.play();

                    return true;
                }
			});
	}
}
