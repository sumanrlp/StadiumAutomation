package com.appiumdemo;

import java.util.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.PublicKey;
import java.text.DateFormat;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReadingData {

	public static void main(String[] args) throws IOException {
		ExcelReadingData objData = new ExcelReadingData();
		
//		System.out.println(objData.SportsAdminEnvDetails()[0]);
//		Map<String, String> playreDataMap = new LinkedHashMap<>();
//		playreDataMap = objData.getPlayerData();
//		
//		String EmailAddress = (String) playreDataMap.keySet().toArray()[0];
//		String Pass = playreDataMap.get(EmailAddress);
//		
//		System.out.println(EmailAddress);
//		System.out.println(Pass);
		
		String parlayNoOfrows = objData.getGameDetails().get(2)[2];
		int startingRowNoofTeaser = Integer.parseInt(parlayNoOfrows) + 2;
		String teaserNoOfRows = objData.getGameDetails().get(startingRowNoofTeaser)[2];
		int startingRowNoOfRound_robin = Integer.parseInt(teaserNoOfRows) + Integer.parseInt(parlayNoOfrows) + 2;
		String gameDetails[] = objData.getGameDetails().get(startingRowNoOfRound_robin);
		
		for (String string : gameDetails) {
			System.out.println(string);
		}

//		System.out.println(objData.betTypeId());

	}

	public Map<String, String> getPlayerData() throws IOException {

		Map<String, String> playerData = new LinkedHashMap<>();
		boolean flag = false;
		String excelfilepath = ".//Resources//DataFiles//PlayerData.xlsx";

		FileInputStream inputstream = new FileInputStream(excelfilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(excelfilepath);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getLastRowNum();
		int colms = sheet.getRow(0).getLastCellNum();
//		System.out.println(rows);

		for (int i = 1; i <= rows; i++) {

			XSSFRow row = sheet.getRow(i);
			String EmailAddress_AccNo = "";
			String Pass = "";
			for (int j = 0; j <= colms; j++) {
				if (j == 0) {
					XSSFCell cell = row.getCell(j);
					switch (cell.getCellType()) {
					case STRING:
						EmailAddress_AccNo = cell.getStringCellValue().substring(1);
						break;
					case NUMERIC:
						EmailAddress_AccNo= String.valueOf(cell.getNumericCellValue());
					default:
						break;
					}
				}
				if (j == 1) {
					XSSFCell cell = row.getCell(j);
					switch (cell.getCellType()) {
					case STRING:
						Pass = cell.getStringCellValue();
						break;
					case NUMERIC:
						Pass= String.valueOf(cell.getNumericCellValue());
					default:
						break;
					}
				}
			}
			playerData.put(EmailAddress_AccNo, Pass);
		}
		return playerData;

	}

	public Map<Integer, String[]> getGameDetails() throws IOException {

		String excelfilepath = ".//Resources//DataFiles//GameDetails.xlsx";

		FileInputStream inputstream_gameDetails = new FileInputStream(excelfilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(excelfilepath);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getLastRowNum();
		int colms = sheet.getRow(0).getLastCellNum();

		Map<Integer, String[]> gameConfig = new LinkedHashMap<>();

		for (int i = 1; i <= rows; i++) {
			XSSFRow row = sheet.getRow(i);
			String gameDetails[] = new String[colms];
			
			for (int j = 0; j < colms; j++) {
				XSSFCell cell = row.getCell(j);
				if (cell==null) {
					gameDetails[j] = "null";
				} else {
					if(cell.getCellType()==CellType.STRING) {
						gameDetails[j] = cell.getStringCellValue();
					}
					if(cell.getCellType()==CellType.NUMERIC) {
						gameDetails[j] = Integer.toString((int)cell.getNumericCellValue());
					}
				}
			}
			gameConfig.put(i, gameDetails);
		}
		return gameConfig;
	}
	
	public int getGameDetailsRowsLength() throws IOException {

		String excelfilepath = ".//Resources//DataFiles//GameDetails.xlsx";

		FileInputStream inputstream_gameDetails = new FileInputStream(excelfilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(excelfilepath);

		XSSFSheet sheet = workbook.getSheetAt(0);

		return sheet.getLastRowNum();
	}

	public Map<Integer, String[]> getTestData() throws IOException {

		String excelfilepath = ".//Resources//DataFiles//TestCases.xlsx";

		FileInputStream inputStream = new FileInputStream(excelfilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(excelfilepath);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getLastRowNum();
		int colms = sheet.getRow(0).getLastCellNum();
		Map<Integer, String[]> testData = new LinkedHashMap<>();

		for (int i = 1; i <= rows; i++) {
			XSSFRow row = sheet.getRow(i);
			String testDataArr[] = new String[colms];
			
			for (int j = 0; j < colms; j++) {
				XSSFCell cell = row.getCell(j);
				if(cell!= null) {
					if(cell.getCellType()==CellType.STRING) {
						testDataArr[j] = cell.getStringCellValue();
					}
					if(cell.getCellType()==CellType.NUMERIC) {
						testDataArr[j] = Integer.toString((int)cell.getNumericCellValue());
					}
				} else {
					testDataArr[j]="null";
				}
			}
			testData.put(i, testDataArr);
		}
		return testData;
	}
	
	public String betTypeId() throws IOException {

		String excelfilepath = ".//Resources//DataFiles//TestCases.xlsx";

		FileInputStream inputstream_gameDetails = new FileInputStream(excelfilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(excelfilepath);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getLastRowNum();
		int colms = sheet.getRow(0).getLastCellNum();

		String betTypeId="0";
			
			for (int j = 0; j < rows; j++) {
				XSSFRow row = sheet.getRow(j);
				XSSFCell cell = row.getCell(5);
				if(cell!=null) {
					if( cell.getCellType()==CellType.STRING) {
						betTypeId = cell.getStringCellValue();
					}
					if(cell.getCellType()==CellType.NUMERIC) {
						betTypeId = Integer.toString((int)cell.getNumericCellValue());
					}
				}
			}
		return betTypeId;
	}
	
	public String[] SportsAdminEnvDetails() throws IOException {
		String excelfilepath = ".//Resources//DataFiles//SportsAdmin.xlsx";

		FileInputStream inputstream_gameDetails = new FileInputStream(excelfilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(excelfilepath);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getLastRowNum();
		int colms = sheet.getRow(0).getLastCellNum();

		String[] EnvDetails= new String[colms];
			
			for (int j = 0; j < rows; j++) {
				XSSFRow row = sheet.getRow(1);
				XSSFCell cell = row.getCell(j);
				if(cell!=null) {
					if( cell.getCellType()==CellType.STRING) {
						EnvDetails[j] = cell.getStringCellValue();
					}
					if(cell.getCellType()==CellType.NUMERIC) {
						EnvDetails[j] = Integer.toString((int)cell.getNumericCellValue());
					}
				}
			}
		return EnvDetails;
	}
	
	public String[] FreeCashDetails() throws IOException {
		
		String excelfilepath = ".//Resources//DataFiles//FreeCashDetails.xlsx";

		FileInputStream inputstream_gameDetails = new FileInputStream(excelfilepath);

		XSSFWorkbook workbook = new XSSFWorkbook(excelfilepath);

		XSSFSheet sheet = workbook.getSheetAt(0);

		int rows = sheet.getLastRowNum();
		int colms = sheet.getRow(0).getLastCellNum();
		
		String[] freeCashDetails = new String[colms];

		for (int i = 1; i <= rows; i++) {
			XSSFRow row = sheet.getRow(i);
			
			for (int j = 0; j < colms; j++) {
				XSSFCell cell = row.getCell(j);
				if(cell!= null) {
					if(cell.getCellType()==CellType.STRING) {
						freeCashDetails[j] = cell.getStringCellValue();
					}
					if(cell.getCellType()==CellType.NUMERIC) {
						freeCashDetails[j] = Integer.toString((int)cell.getNumericCellValue());
					}
				} else {
					freeCashDetails[j]="null";
				}
			}
		}
		
		return freeCashDetails;
		
	}
}