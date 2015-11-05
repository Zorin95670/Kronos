package com.aits.kronos.model;

import java.time.LocalTime;

public class Time {
	private int second;

	public int get() {
		return second;
	}
	
	public Time(int second) {
		set(second);
	}

	public Time(int hour, int minute) {
		set(hour, minute);
	}	

	public void set(int second) {
		this.second = second;
	}

	public void set(int hour, int minute) {
		this.second = LocalTime.of(hour, minute).toSecondOfDay();
	}
	
	@Override
	public String toString() {
		String s;
		if(second > 0)
			s = LocalTime.ofSecondOfDay(second).toString();
		else
			s = "-" + LocalTime.ofSecondOfDay(-second).toString();
		return s;
	}
}
