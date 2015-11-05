package com.aits.kronos.controller.timeline;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LabelTimeMouseEvent extends AbstractTimeLineEvent implements EventHandler<MouseEvent> {
	private Label[] times;
	private TextField[] fields;

	public LabelTimeMouseEvent(ActiveNode activeNode, Label[] times, TextField[] fields) {
		super();
		setActiveNode(activeNode);
		this.times = times;
		this.fields = fields;
	}

	@Override
	public void handle(MouseEvent event) {
		Label source = (Label) event.getSource();
		int i = Integer.parseInt(source.getId().substring(1));
		if (getActiveNode().getIndex() != -1) {
			fields[getActiveNode().getIndex()].setVisible(false);
			times[getActiveNode().getIndex()].setVisible(true);
		}
		getActiveNode().setIndex(i);
		
		fields[i].setText(parse(times[i].getText()));
		times[i].setVisible(false);
		fields[i].setVisible(true);
		fields[i].requestFocus();
	}
}