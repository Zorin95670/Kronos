package com.aits.kronos.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Year extends ArrayList<Month> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4023290203019134471L;
	private int value;

	public Year() {
		setValue(YearMonth.now().getYear());
	}
	
	public Year(int year) {
		setValue(year);
	}
	
	public Year(JSONObject year){
		if(year.has("value")){
			setValue(year.getInt("value"));
			if(year.has("months")){
				JSONArray months = year.getJSONArray("months");
				for (int i = 0; i < months.length(); i++) {
					this.add(new Month(months.getJSONObject(i)));
				}
			}
		}
	}
	
	public List<Month> getMonths() {
		return this;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Month getMonth(int value) {
		for (Month month : this) {
			if (month.getValue() == value)
				return month;
		}
		
		return new Month();

	}

	public Month getCurrentMonth() {
		return getMonth(YearMonth.now().getMonthValue());
	}
	
	@Override
	public String toString() {
		return "{value:"+getValue()+", months:"+getMonths()+"}" ;
	}
	
	public JSONObject toJSONObject(){
		JSONObject json = new JSONObject();
		json.put("value", getValue());
		JSONArray array = new JSONArray();
		for (int i = 0; i < size(); i++)
			array.put(get(i).toJSONObject());
		json.put("months", array);
		return json;
	}
}
