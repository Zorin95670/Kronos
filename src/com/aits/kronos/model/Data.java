package com.aits.kronos.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Data extends ArrayList<Year> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5581114981349509248L;

	public Data() {
		// TODO Auto-generated constructor stub
	}

	public Data(JSONObject json) {
		if (json.has("years")) {
			JSONArray years = json.getJSONArray("years");
			for (int i = 0; i < years.length(); i++) {
				this.add(new Year(years.getJSONObject(i)));
			}
		}

	}

	@Override
	public boolean add(Year e) {
		for(int i = 0 ; i < size() ; i++)
			if(get(i).getValue() == e.getValue())
				return false;
		return super.add(e);
	}
	
	public List<Year> getYears() {
		return this;
	}

	public Year getYear(int value) {
		for (Year year : this) {
			if (year.getValue() == value)
				return year;
		}
		return new Year();

	}

	public Year getCurrentYear() {
		return getYear(YearMonth.now().getMonthValue());
	}

	public JSONObject toJSONObject() {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		for (int i = 0; i < size(); i++)
			array.put(get(i).toJSONObject());
		json.put("years", array);
		return json;
	}

	
	@Override
	public String toString() {
		return "{years:"+getYears()+"}" ;
	}
	
	public static void main(String[] args) {
		System.out.println(new Data());
	}
	
}
