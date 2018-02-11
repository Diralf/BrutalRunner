package com.eastarea.mygame2.io;
import java.io.*;
import android.util.*;
import android.content.*;
import com.eastarea.mygame2.*;
import java.util.*;

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
	
	static public void writeArray(String fileName, List<String> data) {
		Context context = MyGdxGame.context;
		try {
			String separator = System.getProperty("line.separator");
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
			for (String line: data) {
				outputStreamWriter.write(line);
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
}
