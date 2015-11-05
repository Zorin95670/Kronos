package com.aits.kronos.controller;

import com.aits.kronos.controller.timeline.AbstractTimeLine;
import com.aits.kronos.controller.timeline.ActiveNode;
import com.aits.kronos.controller.timeline.FieldTimeKeyEvent;
import com.aits.kronos.controller.timeline.LabelTimeMouseEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class KronosLine extends AbstractTimeLine {

	private Label date;
	private Label[] times;
	private TextField[] fields;
	private String name;
	
	public KronosLine(String name) {
		this.name = name;
	}
	
	@Override
	public void initComponent() {
		date = (Label) getNode().getScene().lookup("#Date");
		date.setId(name+"date");
		date.setText("test");
		times = new Label[4];
		fields = new TextField[4];
		
		ActiveNode activeNode = new ActiveNode();
		
		for (int i = 0; i < 4; i++) {			
			times[i] = (Label) getNode().getScene().lookup("#time" + i);
			times[i].setId(name+ "t"+i);
			fields[i] = (TextField) getNode().getScene().lookup("#field" + i);
			times[i].setId(name+ "f"+i);
			fields[i].setVisible(false);
		}
		
		for(int i = 0 ; i < times.length ; i++){
			times[i].addEventHandler(MouseEvent.MOUSE_RELEASED, new LabelTimeMouseEvent(activeNode, times, fields));
			fields[i].addEventHandler(KeyEvent.KEY_RELEASED, new FieldTimeKeyEvent(activeNode, times, fields));
		}
	}
}
