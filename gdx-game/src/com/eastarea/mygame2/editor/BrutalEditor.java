package com.eastarea.mygame2.editor;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.*;
import java.io.*;
import org.jaudiotagger.*;
import org.jaudiotagger.audio.mp3.*;
import org.jaudiotagger.audio.exceptions.*;
import org.jaudiotagger.tag.*;
import com.eastarea.mygame2.io.*;
import java.util.*;
import com.eastarea.mygame2.Menu.*;
import com.eastarea.mygame2.note.*;

public class BrutalEditor implements IStagable
{

	OrthographicCamera camera;
	TextureRegion backgroundTexture;
	List<Note> notes;
	float position;
	int noteNumber;
	float cooldown = 0.2f;

	Music music;
	
	ButtonExample button;
	
	String filename = "testext.txt";
	String musicNname = "SevenNationArmy.mp3";
	
	public BrutalEditor()
	{
		Texture texture = new Texture(Gdx.files.internal("skyBackground.jpg"));
		backgroundTexture = new TextureRegion(texture, 0, 0, 2048, 563);
		music = Gdx.audio.newMusic(Gdx.files.internal(musicNname));
		//MP3AudioHeader mp3 = getMP3("/storage/emulated/0/AppProjects/BrutalRunner/gdx-game-android/assets/SevenNationArmy.mp3");
		notes = new ArrayList<Note>();
		
		camera = new OrthographicCamera();
		if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth())
			camera.setToOrtho(false, 800, 800 * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
		else
			camera.setToOrtho(false, 800 * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), 800);
		
		button = new ButtonExample();
		
		button.hitButton.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
				
						//notes.add(music.getPosition());
						notes.add(new Note(music.getPosition()));
						noteNumber++;
					
					return true;
				}
		});
		
		button.startButton.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
					System.out.println("start clicked");
					if (!music.isPlaying()) {
						System.out.println("music started");
						music.play();
					}

					return true;
				}
			});
			
		button.saveButton.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
					System.out.println("writed");
					//IOFile.writeArray("test.txt", notes);
					NoteIO.writeExtArray(filename, notes);
					return true;
				}
			});
			
		button.loadButton.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
             		//List<String> res =IOFile.readArray("test.txt");
					List<Note> res = NoteIO.readExtArray(filename);
					System.out.println("readed ");
					for (Note line : res) {
						System.out.println(line);
						System.out.println(line.time);
					}
					//IOFile.readFileSDArray("testext.txt");

					return true;
				}
			});
			
		button.menuButton.addListener(new ClickListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int p, int b) {
					MyGdxGame.changeStage(new BrutalMainMenu());
					return true;
				}
			});
			
			//IOExcel.loadExcel();
	}
	
	@Override
	public void render(SpriteBatch batch, ShapeRenderer shapeRenderer, BitmapFont font)
	{
		// TODO: Implement this method
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);
        batch.begin();

		// Draw background
		for (int i = 0; i < 30; i++)
			batch.draw(backgroundTexture, i * 2900, 0, 2900, 800);

		//font.draw(batch, (int) (manPosition.x / 70) + "m", camera.position.x - 10, 30);
		for (int i=0; i< noteNumber; i++)
			font.draw(batch, notes.get(i).time+"", camera.position.x - 10, 30+noteNumber*15-i*15);
        batch.end();


//		if (Gdx.input.isTouched())
//		{
//			if (noteNumber>0 && music.getPosition() - notes[noteNumber-1] < cooldown) 
//			{
//
//			} else {
//				notes[noteNumber] = music.getPosition();
//				noteNumber++;
//			}
//		}

		position += Gdx.graphics.getDeltaTime();
		button.render();
	}

	@Override
	public void dispose()
	{
		music.stop();
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		music.pause();
	}

	@Override
	public void resume()
	{
		music.play();
	}
	
	public MP3AudioHeader getMP3(String path) {
		MP3AudioHeader mp3 = null;
		
		File f=new  File(path) ;
		try {
			mp3=new MP3AudioHeader(f);
			System.out.println(" "+mp3.getSampleRate());
			
				MP3File mp = new MP3File(f);
				System.out.println( mp.displayStructureAsXML());
			}
			catch (IOException e)
		{e.printStackTrace();}
			catch (CannotReadException e)
		{e.printStackTrace();}
			catch (TagException e)
		{e.printStackTrace();}
			catch (InvalidAudioFrameException e)
		{e.printStackTrace();}
			catch (ReadOnlyFileException e)
		{e.printStackTrace();}
		
  
		
		return mp3;
/*
		textField.setText(f.getAbsolutePath());
		l1.setText("Частота ="+Double.parseDouble(mp3.getSampleRate())/1000+"кГц");
		l2.setText("Б.="+mp3.getBitRate()+" KBit/s");
		l3.setText(" "+mp3.getNumberOfFrames());
		l4.setText(""+mp3.getBitsPerSample());
*/
		
	}
	/*
	public void actionPerformed(ActionEvent arg0) {

		jfc.showOpenDialog(null);
		File f=new  File(jfc.getSelectedFile().getAbsolutePath()) ;
		try {
			mp3=new MP3AudioHeader(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		textField.setText(f.getAbsolutePath());
		l1.setText("Частота ="+Double.parseDouble(mp3.getSampleRate())/1000+"кГц");
		l2.setText("Б.="+mp3.getBitRate()+" KBit/s");
		l3.setText(" "+mp3.getNumberOfFrames());
		l4.setText(""+mp3.getBitsPerSample());



	}*/
	
}
