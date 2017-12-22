package com.eastarea.mygame2;
import com.badlogic.gdx.audio.*;

public class StartMusicTrigger extends LiquidBox
{
	StartMusicTrigger(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}

	@Override
	public void emitCollision(ICollideable other)
	{
		Music m = MyGdxGame.game.backMusic;
		//-*if (m.isPlaying()) m.stop();
		m.play();
	}
	
    
}
