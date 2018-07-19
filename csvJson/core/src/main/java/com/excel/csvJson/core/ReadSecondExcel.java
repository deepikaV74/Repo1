package com.excel.csvJson.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

import org.json.simple.JSONArray;

public class ReadSecondExcel {

	

	// get first excel
	@SuppressWarnings("resource")
	public static List<JsonBean> readFirstExcel(final FileInputStream inputStream) {
		List<JsonBean> cellValues = new ArrayList<JsonBean>();

		XSSFWorkbook workbook;
		try {

			workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			cellValues = allCellValues(sheet, cellValues);

			return cellValues;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cellValues;

	}

	// get cell values and add to bean


	private static List<JsonBean> allCellValues(XSSFSheet sheet, List<JsonBean> cellValues) {

		int num = sheet.getPhysicalNumberOfRows();
		List<String> statesList = null;
		List<String> statesNameList = null;
		JsonBean object = null;
		JSONArray pinsObj = null;
		for (int i = 1; i < num; i++) {

			Row column = sheet.getRow(i);

			if (column.getCell(0) == null && column.getCell(1) != null) {

				String states = column.getCell(1).getStringCellValue();
				statesList.add(states);
				object.setStates(statesList);

				String stateName = column.getCell(2).getStringCellValue();

				compareStates(pinsObj, states, stateName);

				statesNameList.add(stateName);
				object.setStatesNames(statesNameList);
				object.setPins(pinsObj);

			}

			else {
				object = new JsonBean();
				pinsObj = new JSONArray();
				statesList = new ArrayList<>();
				statesNameList = new ArrayList<>();
				String name = column.getCell(0).getStringCellValue();
				object.setRegionName(name);

				String states = column.getCell(1).getStringCellValue();
				statesList.add(states);
				object.setStates(statesList);

				String stateName = column.getCell(2).getStringCellValue();

				compareStates(pinsObj, states, stateName);

				statesNameList.add(stateName);
				object.setStatesNames(statesNameList);
				object.setPins(pinsObj);

				String count = column.getCell(3).toString();

				object.setCount(count);

				String latitude = column.getCell(4).toString();

				object.setLatitude(latitude);

				String longitude = column.getCell(5).toString();

				object.setLongitude(longitude);
				cellValues.add(object);
			}

		}

		return cellValues;

	}

	@SuppressWarnings("unchecked")
	private static void compareStates(JSONArray pinsObj, String states, String stateName) {
		JSONArray secondExcelArray = readSecondExcel();
		//test map implementation
		for (int k = 0; k < secondExcelArray.size(); k++) {
			JSONObject objectInJson = (JSONObject) secondExcelArray.get(k);
			String statesInSecondList = objectInJson.get("state").toString();

			if (statesInSecondList.equalsIgnoreCase(stateName) || statesInSecondList.equalsIgnoreCase(states)) {
				JSONObject testPins = new JSONObject();
				String lat = objectInJson.get("latitude").toString();
				String longi = objectInJson.get("longitude").toString();
				
				
				testPins.put("Longitude", longi);
				testPins.put("Latitude", lat);
				pinsObj.add(testPins);

			}

		}
	}

	// get excel first then compare the cell name with the list entries
	// @SuppressWarnings({ "resource", "unchecked" })
	@SuppressWarnings("unchecked")
	private static JSONArray readSecondExcel() {

		FileInputStream secndExcel;
		String secondFilePath = "D:/now/Litter Stand Selection Transfer File 6.4.2018.xlsx";
		XSSFWorkbook workbook2;
		JSONArray latLong = new JSONArray();
		try {
			secndExcel = new FileInputStream(new File(secondFilePath));
			workbook2 = new XSSFWorkbook(secndExcel);
			XSSFSheet sheet2 = workbook2.getSheetAt(0);
			// Now get the cell Values based on the state name
			int num2 = sheet2.getPhysicalNumberOfRows();

			for (int i = 1; i < num2; i++) {

				JSONObject secondObj = new JSONObject();
				Row column = sheet2.getRow(i);
				String stateInListTwo = column.getCell(1).getStringCellValue();

				String latitude2 = column.getCell(2).toString();
				String longitude2 = column.getCell(3).toString();
				secondObj.put("state", stateInListTwo);
				secondObj.put("longitude", longitude2);
				secondObj.put("latitude", latitude2);

				latLong.add(secondObj);
			

			}
			secndExcel.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return latLong;

	}

	// create json from bean
	@SuppressWarnings("unchecked")
	public static JSONArray getJsonFromMyExcelObject(List<JsonBean> obj) throws JSONException {

		JSONArray jsonArray = new JSONArray();
		Map<String, JSONArray> item_root = new LinkedHashMap<String, JSONArray>();
		JSONArray item_array = new JSONArray();
		for (int i = 0; i < obj.size(); i++) {
			Map<String, Object> mapObj = new LinkedHashMap<String, Object>();
			mapObj.put("Name", obj.get(i).getRegionName());
			mapObj.put("States", obj.get(i).getStates());
			mapObj.put("Pins", obj.get(i).getPins());
			
			mapObj.put("Longitude", obj.get(i).getLongitude());
			mapObj.put("Latitude", obj.get(i).getLatitude());
			mapObj.put("Count", obj.get(i).getCount());
			item_array.add(mapObj);
		
		}
		item_root.put("Kab", item_array);
		jsonArray.add(item_root);
		//System.out.println(" csv to json :::::\n" + jsonArray);

		return jsonArray;
	}

}
