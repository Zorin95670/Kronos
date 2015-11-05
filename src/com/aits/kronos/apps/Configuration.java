package com.aits.kronos.apps;

import java.util.List;

import org.json.JSONObject;

import com.aits.kronos.model.Year;

public class Configuration extends JSONObject implements IConfiguration {

	private static String DEFAULT_DAY_TIME = "07:22";
	private static String DEFAULT_MEEL_TIME = "00:45";
	private static String DEFAULT_MORNING_TIME = "08:00";
	private static String DEFAULT_EVENING_TIME = "18:30";
	private static String DEFAULT_DATE_FORMAT = "HH:mm";

	private List<Year> years;
	// private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

	public Configuration() {
		super();
		init(false);
	}

	Configuration(String source) {
		super(source);
		init(true);
	}

	public int init(boolean data) {
		if (!data) {
			this.put("DayTime", DEFAULT_DAY_TIME);
			this.put("MeelTime", DEFAULT_MEEL_TIME);
			this.put("MorningTime", DEFAULT_MORNING_TIME);
			this.put("EveningTime", DEFAULT_EVENING_TIME);
			this.put("DateFormat", DEFAULT_DATE_FORMAT);
		} else {
			if(!this.has("DayTime")){
				System.err.println("no day time");
				return 1;
			}
			if(!this.has("MeelTime")){
				System.err.println("no meel time");
				return 2;
			}
			if(!this.has("MorningTime")){
				System.err.println("no morning time");
				return 3;
			}
			if(!this.has("EveningTime")){
				System.err.println("no evening time");
				return 4;
			}
			if(!this.has("DateFormat")){
				System.err.println("no date format");
				return 5;
			}
		}
		return 0;
	}

}
