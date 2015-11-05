package com.aits.kronos.controller.timeline;

import com.aits.kronos.model.Day;

import javafx.scene.Node;

public interface ITimeLine {

	public Node getNode();
	public void setNode(Node node);
	
	public void loadNode(String name);
	
	public void initComponent();
	
	public void setInformationVisible(boolean visible);
	
	public void setDay(Day day);
	public Day getDay();
}
