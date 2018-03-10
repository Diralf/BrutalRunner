package com.eastarea.mygame2.io;
import java.io.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.poifs.filesystem.*;

public class IOExcel
{
	
	public static void loadExcel() {
		// получаем файл в формате xls
		try
		{
			System.out.println("start read excel file");
			
			POIFSFileSystem fs;

            fs = new POIFSFileSystem(new FileInputStream("/storage/emulated/0/AppProjects/BrutalRunner/gdx-game-android/assets/tes1.xls"));
			
			//FileInputStream file = new FileInputStream(new File("/storage/emulated/0/AppProjects/BrutalRunner/gdx-game-android/assets/test.xls"));
			// формируем из файла экземпляр HSSFWorkbook
		
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			
			

// выбираем первый лист для обработки 
// нумерация начинается с 0
			HSSFSheet sheet = workbook.getSheetAt(0);

// получаем Iterator по всем строкам в листе
			Iterator<Row> rowIterator =  sheet.iterator();

			Row row = rowIterator.next();
// получаем Iterator по всем ячейкам в строке
			Iterator<Cell> cellIterator = row.cellIterator();
			System.out.println(cellIterator.next());
		}
		catch (FileNotFoundException e)
		{ 
		e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			}


	}
}
