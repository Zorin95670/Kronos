package com.aits.kronos.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Month extends ArrayList<Day> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8290979362591443531L;
	private int value;
	
	public Month() {
		YearMonth.now().
	}
	
	public Month(int value) {
		setValue(value);
	}
	
	public Month(JSONObject month) {
		if(month.has("value")){
			setValue(month.getInt("value"));
			if(month.has("days")){
				JSONArray days = month.getJSONArray("days");
				for (int i = 0; i < days.length(); i++) {
					this.add(new Day(days.getJSONObject(i)));
				}
			}
		}
	}
		
	public List<Day> getDays(){
		return this;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "{value:"+getValue()+", days:"+getDays()+"}" ;
	}
	
	public JSONObject toJSONObject(){
		JSONObject json = new JSONObject();
		json.put("value", getValue());
		JSONArray array = new JSONArray();
		for (int i = 0; i < size(); i++)
			array.put(get(i).toJSONObject());
		json.put("days", array);
		return json;
	}
}
