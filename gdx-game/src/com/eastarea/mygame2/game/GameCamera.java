package com.eastarea.mygame2.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.eastarea.mygame2.character.*;

public class GameCamera
{
	public OrthographicCamera camera;
	
	float cameraOffsetx;
	float cameraOffsety;
	int speedy = 10;
	
	public GameCamera()
	{
		this.camera = new OrthographicCamera();
		configureCamera();
		
		cameraOffsetx = camera.viewportWidth / 2 - 100;
		cameraOffsety = camera.viewportHeight/ 2 - 200;
	}
	
	public void configureCamera()
	{
		if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth())
			camera.setToOrtho(false, 800, 800 * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
		else
			camera.setToOrtho(false, 800 * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), 800);
	}
	
	public void update(GuitarCube guitarCube)
	{
		float gy = guitarCube.position.y;
		camera.position.x = guitarCube.position.x + cameraOffsetx;
		if (camera.position.y > camera.viewportHeight/2 || gy > camera.viewportHeight/2 || camera.position.y <0)
			camera.position.y = moveTo(camera.position.y, gy + cameraOffsety, speedy);

		camera.update();
	}
	
	float moveTo(float start, float target, float speed)
	{
		float delta = Math.abs(target-start) *  speed * Gdx.graphics.getDeltaTime();
		if (Math.abs(target-start) < delta)
			return target;
		return start + (delta * Math.signum(target - start));
	}
}
