package com.eastarea.mygame2.note;
import java.io.*;
import android.os.*;
import java.util.*;
import com.eastarea.mygame2.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.*;

public class NoteIO
{
	static public void save(BufferedWriter bw, Note note) throws IOException
	{
		String separator = System.getProperty("line.separator");
		bw.write(note.time + separator);
		bw.write(note.floor.getMask().y+separator);
		bw.write(note.floor.getMask().height+separator);
		bw.write(note.item.position.getMask().y+separator);
	}
	
	static public Note load(BufferedReader br) throws IOException
	{
		String str = br.readLine();
		if (str == null) return null;
		float time = Float.parseFloat(str);
		
		str = br.readLine();
		int floorTypeNum =(int) Float.parseFloat(str);
        EFloorType floorType = EFloorType.NORMAL;
        if (floorTypeNum == 1) floorType = EFloorType.JUMP;
		
		str = br.readLine();
        if (str == null) return null;
		int hFloor = (int) Float.parseFloat(str);
		
		str = br.readLine();
		int yItem = (int) Float.parseFloat(str);
		
		return new Note(time, floorType, hFloor - 50, 50, yItem, NoteItemType.BONUS);
	}
    
    static public Note load(LinkedList<String> songText)
    {
        String str = songText.poll();
        if (str == null) return null;
        float time = Float.parseFloat(str);

        str = songText.poll();
        int floorTypeNum =(int) Float.parseFloat(str);
        EFloorType floorType = EFloorType.NORMAL;
        if (floorTypeNum == 1) floorType = EFloorType.JUMP;

        str = songText.poll();
        if (str == null) return null;
        int hFloor = (int) Float.parseFloat(str);

        str = songText.poll();
        int yItem = (int) Float.parseFloat(str);

        return new Note(time, floorType, hFloor - 50, 50, yItem, NoteItemType.BONUS);
	}
	
	static public void writeExtArray(String fileName, List<Note> data) {
		// проверяем доступность SD
        
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			System.out.println( "SD-карта не доступна: " + Environment.getExternalStorageState());
			return;
		}
		File sdPath = MyGdxGame.context.getExternalFilesDir(null);
		sdPath.mkdirs();
		File sdFile = Gdx.files.internal(fileName).file();//new File(sdPath, fileName);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
			for (Note note : data)
			{
				NoteIO.save(bw, note);
			}
			bw.close();
			// открываем поток для записи
			System.out.println( "Note записан на SD: " + sdFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public List<Note> readExtArray(String fileName) {
		// проверяем доступность SD
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			System.out.println("SD-карта не доступна: " + Environment.getExternalStorageState());
			return null;
		}
		// получаем путь к SD
		File sdPath = MyGdxGame.context.getExternalFilesDir(null);
		// добавляем свой каталог к пути
		//sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
		// формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, fileName);
		List<Note> list = new ArrayList<Note>();
		try {
			// открываем поток для чтения
			BufferedReader br = new BufferedReader(new FileReader(sdFile));
			Note note;
			// читаем содержимое
            int i= 0;
			while ((note = NoteIO.load(br)) != null) {
                list.add(note);
                i++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
    
    static public List<Note> readAssetArray(String fileName) {
        String text =Gdx.files.internal(fileName).readString();
        LinkedList<String> lineList = new LinkedList<String>();
        List<Note> list = new ArrayList<Note>();
        Note note;
        int i= 0;
        
        lineList.addAll(Arrays.asList(text.split("\n")));
        
        while ((note = NoteIO.load(lineList)) != null) {
            list.add(note);
            i++;
        }
        return list;
	}
}
