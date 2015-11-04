package com.aits.kronos.model;

public class Day {
	private Time[] times;
	private Time[] defaultTimes;
	private Time defaultMeelTime;
	private Time defaultDayTime;
	
	public Day() {
		setTimes(new Time[]{
				new Time( 8, 00),
				new Time(12, 00),
				new Time(13, 00),
				new Time(15, 00)
		});
		setDefaultTimes(new Time[]{
				new Time( 8, 00),
				new Time(11, 45),
				new Time(14, 00),
				new Time(18, 30)
		});
		setDefaultMeelTime(new Time( 0, 45));
		setDefaultDayTime(new Time( 7, 22));
	}
	
	public Time getMeetTimes(){
		int meel = defaultMeelTime.get();
		int total = times[2].get() - times[1].get();
		if(total < meel)
			total = meel;
		return new Time(total);
	}
	
	public Time getTotalDay(){
		return new Time(times[3].get() - times[0].get() - getMeetTimes().get());
	}
	
	public Time getCounter(){
		return new Time(getTotalDay().get() - getDefaultDayTime().get());
	}
	public Time[] getTimes() {
		return times;
	}

	public void setTimes(Time[] times) {
		this.times = times;
	}

	public Time[] getDefaultTimes() {
		return defaultTimes;
	}

	public void setDefaultTimes(Time[] defaultTimes) {
		this.defaultTimes = defaultTimes;
	}

	public Time getDefaultMeelTime() {
		return defaultMeelTime;
	}

	public void setDefaultMeelTime(Time defaultMeelTime) {
		this.defaultMeelTime = defaultMeelTime;
	}	

	public Time getDefaultDayTime() {
		return defaultDayTime;
	}

	public void setDefaultDayTime(Time defaultDayTime) {
		this.defaultDayTime = defaultDayTime;
	}
	
	public static void main(String[] args) {
		Day d = new Day();
		System.out.println(d.getMeetTimes());
		System.out.println(d.getTotalDay());
		System.out.println(d.getCounter());
		
	}
}
