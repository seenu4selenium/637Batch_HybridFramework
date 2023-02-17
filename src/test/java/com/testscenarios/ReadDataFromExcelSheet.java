package com.testscenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcelSheet {

	public static void main(String[] args) throws Exception {

		//Find the Excel sheet path
		FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\testdata\\td.xlsx");
		//Workbook
		Workbook w = new XSSFWorkbook(fi); //.xlsx extension
		//Workbook w = new HSSFWorkbook(fi);//.xls extension
		//Sheet
		Sheet s = w.getSheet("Sheet1");
		//Row
		Row r = s.getRow(3);		
		//Column
		Cell c = r.getCell(8);		
		System.out.println(c);
		
		

	}

}
