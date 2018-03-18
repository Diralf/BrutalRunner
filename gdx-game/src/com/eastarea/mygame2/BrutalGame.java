package com.eastarea.mygame2;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.eastarea.mygame2.Menu.*;
import com.eastarea.mygame2.game.*;
import java.io.*;

public class BrutalGame implements IStagable
{
	
	GameSession session;
	public OrthographicCamera camera;
	public TextureRegion backgroundTexture;
	
	public Sound collisionSound;
	public int cellSize=125;
	
	GameUIButtons buttons;
	
	public BrutalGame(File songFile) 
	{

		// Load background 
		Texture texture = new Texture(Gdx.files.internal("skyBackground.jpg"));
		backgroundTexture = new TextureRegion(texture, 0, 0, 2048, 563);

		// Load and position rocks
	
		collisionSound = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));

		camera = new OrthographicCamera();
		
		session = new GameSession(this, songFile);
		buttons = new GameUIButtons(this);

		buttons.back.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
					MyGdxGame.changeStage(new BrutalMainMenu());
					return true;
				}
			});

		buttons.start.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
					session.resetGame();
					return true;
				}
				});
				
		buttons.pause.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
					session.start();

					return true;
				}
			});
	}
	
	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer,BitmapFont font)
	{
	    //updateMap();
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		session.renderBatch(batch, font);

		shapeRenderer.setProjectionMatrix(camera.combined);
		session.renderShapes(shapeRenderer);
	
		buttons.render();
		
		session.update();
		
	}
	
	
	
	public void configureCamera()
	{
		if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth())
			camera.setToOrtho(false, 800, 800 * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
		else
			camera.setToOrtho(false, 800 * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), 800);
	}
	
	@Override
    public void dispose()
	{
		session.dispose();
    }

	@Override
    public void resize(int width, int height)
	{
		//resetGame();
    }
	
	@Override
    public void pause()
	{
		session.pause();
    }

	@Override
    public void resume()
	{
		session.resume();
    }
	
	
//	
//	public void updateMap() {
//		float resX = guitarMan.position.x;
//		if (backMusic.getPosition() ==0) return;
//		resX /= backMusic.getPosition();
//		//System.out.println(resX);
//		//System.out.print(" ");
//		nextNoteNumber =0;
//		for (int i=0; i<noteBoxes.size(); i++) {
//			
//			//resX *= notes.get(i);
//			//System.out.println(resX);
//			float pos = resX * notes.get(i);
//			float prev = 0;
//			noteBoxes.get(i).getMask().x= pos;
//			if (i>0) {
//				CollisionBox pBox = floorBoxes.get(i-1);
//				prev = pBox.getMask().x + pBox.getMask().width;
//			}
//		    CollisionBox cBox = floorBoxes.get(i);
//			cBox.getMask().x = prev;
//			cBox.getMask().width = pos - prev - 2;
//			
//			if ( guitarMan.position.x > pos) {
//				nextNoteNumber = i;
//			}
//			//noteBoxes.get(i).getMask().x = ((guitarMan.position.x-beginMusicPosition)*backMusic.getPosition())/notes.get(i)+beginMusicPosition;
//		}
//	}
//	
//	public void makeMapByNotes()
//	{
//		List<String> vals = IOFile.readFileSDArray("testext.txt");
//		
//		System.out.println(vals.toString());
//		
//		ECollisionType type = ECollisionType.LIQUID;
//	    float prevPosition =0;
//		int num =0;
//		
//		for (String val: vals) {
//			float note = Float.parseFloat(val);
//			CollisionBox box;
//			box = new LiquidBox((int)(note), 50, cellSize/2, cellSize/2);
//			visibleBoxes.add((IRenderable)box);
//			noteBoxes.add(box);
//			notes.add(note);
//			collisionMap.get(type).add(num, box);
//			
//			box = new SolidBox((int)prevPosition, 0, (int)(prevPosition+note), 50);
//			visibleBoxes.add((IRenderable)box);
//			floorBoxes.add(box);
//			collisionMap.get(ECollisionType.SOLID).add(num, box);
//			prevPosition = note;
//			num++;
//			
//		}
//		//updateMap();
//		
////		for (int i = 0; i<300; i++)
////		{
////			int pos[] = convertBinary(m[i% m.length]);
////			for (int j=0; j < pos.length; j++)
////			{
////				if (pos[j]==0) continue;
////				CollisionBox box;
////				if (type == ECollisionType.SOLID)
////					box = new SolidBox(i*cellSize, j*50, cellSize, 50);
////				else
////					box = new LiquidBox(i*cellSize+ cellSize/4, j*50, cellSize/2, cellSize/2);
////				visibleBoxes.add((IRenderable)box);
////				collisionMap.get(type).add(i, box);
////			}
////		}
//	}
}
