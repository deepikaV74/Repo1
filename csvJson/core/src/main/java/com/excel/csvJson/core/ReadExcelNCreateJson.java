package com.excel.csvJson.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;

public class ReadExcelNCreateJson {

	// @SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {
		
		String filePath = "D:/now/Regional KAB 6.6.18.xlsx";
		System.out.println("Enter this "+filePath);
		
		FileInputStream ip;
		
		try {

			// MY Code
			ip = new FileInputStream(new File(filePath));
			
			List<JsonBean> jsonBean = ReadSecondExcel.readFirstExcel(ip);
			JSONArray test1 = ReadSecondExcel.getJsonFromMyExcelObject(jsonBean);
			String jsonStr = test1.toString();
			
			System.out.println("marker data final json:\n " + jsonStr.substring(1, jsonStr.length()-1));

			ip.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
