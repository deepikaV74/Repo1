package com.excel.csvJson.core;

import java.util.List;

import org.json.simple.JSONArray;

import com.google.gson.Gson;

public class JsonBean {

	private String regionName;

	private List<String> states;

	private List<String> statesNames;

	private String count;

	private String latitude;

	private String longitude;

	private JSONArray pins;
	
	private String test;
	

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public JSONArray getPins() {
		return pins;
	}

	public void setPins(JSONArray pins) {
		this.pins = pins;
	}

	public List<String> getStatesNames() {
		return statesNames;
	}

	public void setStatesNames(List<String> statesNames) {
		this.statesNames = statesNames;
	}

	public List<String> getStates() {
		return states;
	}

	public void setStates(List<String> states) {
		this.states = states;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
