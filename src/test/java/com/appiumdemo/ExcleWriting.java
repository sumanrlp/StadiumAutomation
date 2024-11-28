package com.appiumdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcleWriting {

	public void updateResult(String result, int testCaseNo) throws IOException {
		
		Scanner s = new Scanner(System.in);
		
		String excelfilepath = ".//Resources//DataFiles//TestCases.xlsx";
		
		FileInputStream inputFile= new FileInputStream(excelfilepath);
		XSSFWorkbook workbook = new XSSFWorkbook(inputFile);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int rows = sheet.getLastRowNum();
		int colms = sheet.getRow(0).getLastCellNum();
		ArrayList<Integer> playerNo = new ArrayList<>();
		
		for(int i=1; i<=rows; i++) {
			XSSFRow row= sheet.getRow(i);
			
				if(i==testCaseNo) {
						
					XSSFCell cell= row.getCell(3, org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					if(cell==null) {
						row.createCell(3);
						cell.setCellValue(result);
					}else {
						row.createCell(3);
						cell.setCellValue(result);
					}
			}
		}
		
		FileOutputStream updateFile= new FileOutputStream(excelfilepath);
		workbook.write(updateFile);
		workbook.close();

	}
	
	public static void main(String[] args) throws IOException {
		
		ExcleWriting objData = new ExcleWriting();
		
		objData.updateResult("Pass", 1);
	}

}
