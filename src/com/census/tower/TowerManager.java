package com.census.tower;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TowerManager {
	public static void main(String[] args) {
		HashMap<String, Set<String>> regionMap = new HashMap<String, Set<String>>();
		try {
			FileInputStream file = new FileInputStream(new File(
					"sample_data.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			int rowNum = sheet.getLastRowNum();
			for (int i = 1; i < rowNum - 1; i++) {
				XSSFRow row = sheet.getRow(i);
				XSSFCell antennaCell = row.getCell(6);
				String antenna = String.valueOf((int) antennaCell
						.getNumericCellValue());
				XSSFCell nicCell = row.getCell(4);
				String nic = nicCell.getStringCellValue();
				String tower = antenna.substring(0, 2);
				if(!regionMap.containsKey(tower)){
					Set<String> nicSet = new HashSet<String>();
					nicSet.add(nic);
					regionMap.put(tower, nicSet);
				}
				else{
					regionMap.get(tower).add(nic);
				}
			}
			file.close();
			System.out.println(regionMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
