package com.eastarea.mygame2;
import com.eastarea.mygame2.character.*;
import com.badlogic.gdx.graphics.*;

public class JumpBox extends FloorBox
{
    float speed = 700;
    int gravity = 620;
    
    public JumpBox(int x, int y, int width, int heigth)
    {
        super(x, y, width, heigth);
        color = new Color(0.7f, 0, 0, 1);
    }
    
    @Override
    public void emitCollision(ICollideable other)
    {
        if (other instanceof GuitarCube)
        {
            ((GuitarCube)other).jump(speed, gravity);
        }
    }
}
