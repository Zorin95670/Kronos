package com.aits.kronos.controller.timeline;

import java.text.SimpleDateFormat;

public interface ITimeLineEvent {

	String parse(String date);

	ActiveNode getActiveNode();
	void setActiveNode(ActiveNode activeNode);
	
	public SimpleDateFormat getOutput();
	public void setOutput(SimpleDateFormat output);

	public SimpleDateFormat getInput();
	public void setInput(SimpleDateFormat input);
}
