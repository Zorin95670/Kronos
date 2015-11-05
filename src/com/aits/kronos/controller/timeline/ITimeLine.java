package com.aits.kronos.controller.timeline;

import com.aits.kronos.model.Day;

import javafx.scene.Node;

public interface ITimeLine {

	Node getNode();
	void setNode(Node node);
	
	void loadNode(String name);
	
	void initComponent();
	
	void setInformationVisible(boolean visible);
	
	void setDay(Day day);
	Day getDay();
}
