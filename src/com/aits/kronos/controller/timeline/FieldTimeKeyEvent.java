package com.aits.kronos.controller.timeline;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FieldTimeKeyEvent extends AbstractTimeLineEvent implements EventHandler<KeyEvent> {
	private Label[] times;
	private TextField[] fields;

	public FieldTimeKeyEvent(ActiveNode activeNode, Label[] times, TextField[] fields) {
		super();
		setActiveNode(activeNode);
		this.times = times;
		this.fields = fields;
	}

	@Override
	public void handle(KeyEvent event) {
		if (event.getCode().equals(KeyCode.ENTER)) {
			TextField source = (TextField) event.getSource();
			int i = Integer.parseInt(source.getId().substring(1));
			times[i].setText(parse(fields[i].getText()));
			fields[i].setVisible(false);
			times[i].setVisible(true);
			getActiveNode().setIndex(-1);
		}
	}

}