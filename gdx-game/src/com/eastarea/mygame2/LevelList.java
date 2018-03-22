package com.eastarea.mygame2;

import java.util.*;

public class LevelList<T>
{
    public List<List<T>> list;
    int length;

    public LevelList(int length)
    {
        list = new ArrayList<List<T>>();
        this.length = length;

        for (int i=0; i<length; i++)
        {
            list.add(new ArrayList<T>());
        }
    }

    public void add(int i, T rect)
    {
        list.get(i).add(rect);
	}
    
    public List<T> getList(int i)
    {
        return list.get(i);
    }
}
