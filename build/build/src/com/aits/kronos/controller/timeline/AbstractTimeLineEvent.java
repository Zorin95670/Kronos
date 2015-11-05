package com.aits.kronos.controller.timeline;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AbstractTimeLineEvent implements ITimeLineEvent{

	private ActiveNode activeNode;
	private SimpleDateFormat output, input;
	
	public AbstractTimeLineEvent() {
		setInput(new SimpleDateFormat("HH:mm"));
		setOutput(new SimpleDateFormat("HH:mm"));
	}
	
	@Override
	public String parse(String date) {
		try {
			Date d = input.parse(date);
			date = output.format(d);
		} catch (Exception e) {
			date = "";
		}
		return date;
	}

	@Override
	public ActiveNode getActiveNode() {
		return activeNode;
	}

	@Override
	public void setActiveNode(ActiveNode activeNode) {
		this.activeNode = activeNode;
	}

	@Override
	public SimpleDateFormat getOutput() {
		return output;
	}

	@Override
	public void setOutput(SimpleDateFormat output) {
		this.output = output;
	}

	@Override
	public SimpleDateFormat getInput() {
		return input;
	}

	@Override
	public void setInput(SimpleDateFormat input) {
		this.input = input;
	}

}
