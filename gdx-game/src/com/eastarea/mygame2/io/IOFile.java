package com.eastarea.mygame2.io;
import java.io.*;
import android.util.*;
import android.content.*;
import com.eastarea.mygame2.*;
import java.util.*;
import android.os.*;

public class IOFile
{
	static public void write(String fileName, String data) {
		Context context = MyGdxGame.context;
		try {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
			outputStreamWriter.write(data);
			outputStreamWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	static public void writeArray(String fileName, List data) {
		Context context = MyGdxGame.context;
		try {
			String separator = System.getProperty("line.separator");
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
			for (int i=0; i< data.size(); i++) {
				outputStreamWriter.write(data.get(i).toString());
				outputStreamWriter.write(separator);
			}
			outputStreamWriter.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		} 
	}

	static public String read(String fileName) {
		Context context = MyGdxGame.context;
		String ret = "";

		try {
			InputStream inputStream = context.openFileInput(fileName);

			if ( inputStream != null ) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ( (receiveString = bufferedReader.readLine()) != null ) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ret;
	}
	
	static public List<String> readArray(String fileName) {
		Context context = MyGdxGame.context;
		//String ret = "";
		List<String> list = new ArrayList<String>();

		try {
			InputStream inputStream = context.openFileInput(fileName);

			if ( inputStream != null ) {
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String receiveString = "";
				//StringBuilder stringBuilder = new StringBuilder();

				while ( (receiveString = bufferedReader.readLine()) != null ) {
					//stringBuilder.append(receiveString);
					list.add(receiveString);
				}

				inputStream.close();
				//ret = stringBuilder.toString();
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	/* Checks if external storage is available for read and write */
	public boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state) ||
			Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}
	
	public File getAlbumStorageDir(Context context, String albumName) {
		// Get the directory for the app's private pictures directory.
		File file = new File(context.getExternalFilesDir(null), albumName);
		if (!file.mkdirs()) {
			System.out.println( "Directory not created");
		}
		return file;
	}
	
	
	static public void writeFileSD(String fileName) {
		// проверяем доступность SD
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			System.out.println( "SD-карта не доступна: " + Environment.getExternalStorageState());
			return;
		}
		// получаем путь к SD
		File sdPath = MyGdxGame.context.getExternalFilesDir(null);
		// добавляем свой каталог к пути
		//sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
		// создаем каталог
		sdPath.mkdirs();
		// формируем объект File, который содержит путь к файлу
		File sdFile = new File(sdPath, fileName);
		try {
			// открываем поток для записи
			BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
			// пишем данные
			bw.write("Содержимое файла на SD");
			// закрываем поток
			bw.close();
			System.out.println( "Файл записан на SD: " + sdFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public void readFileSD(String fileName) {
		// проверяем доступность SD
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			System.out.println("SD-карта не доступна: " + Environment.getExternalStorageState());
			return;
		}
		// получаем путь к SD
		File sdPath = MyGdxGame.context.getExternalFilesDir(null);
		// добавляем свой каталог к пути
		//sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
		// формируем объект File, который содержит путь к файлу
		File sdFile = new File(sdPath, fileName);
		try {
			// открываем поток для чтения
			BufferedReader br = new BufferedReader(new FileReader(sdFile));
			String str = "";
			// читаем содержимое
			while ((str = br.readLine()) != null) {
				System.out.println( str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static public void writeFileSDArray(String fileName, List data) {
		// проверяем доступность SD
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			System.out.println( "SD-карта не доступна: " + Environment.getExternalStorageState());
			return;
		}
		File sdPath = MyGdxGame.context.getExternalFilesDir(null);
		sdPath.mkdirs();
		File sdFile = new File(sdPath, fileName);
		try {
			String separator = System.getProperty("line.separator");
			BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
			for (int i=0; i< data.size(); i++) {
				bw.write(data.get(i).toString());
				bw.write(separator);
			}
			bw.close();
			// открываем поток для записи
			System.out.println( "Файл записан на SD: " + sdFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static public List<String> readFileSDArray(String fileName) {
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
		List<String> list = new ArrayList<String>();
		try {
			// открываем поток для чтения
			BufferedReader br = new BufferedReader(new FileReader(sdFile));
			String str = "";
			// читаем содержимое
			while ((str = br.readLine()) != null) {
				list.add(str);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	static public ArrayList<File> listFiles(File dir, final String ext) {
		ArrayList<File> filesList = new ArrayList<File>();
		File[] files = dir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith("." + ext);
				}
			});
		filesList.addAll(Arrays.asList(files));
		return filesList;
	}
	
	static public File getExtDir()
	{
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			System.out.println("SD-карта не доступна: " + Environment.getExternalStorageState());
			return null;
		}
		return MyGdxGame.context.getExternalFilesDir(null);
	}
	
	static public String getSortName(File file)
	{
		return file.getName().substring(0, file.getName().indexOf('.'));
	}
}
