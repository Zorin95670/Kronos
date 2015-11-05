package com.aits.kronos.controller.timeline;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class InformationVisibilityEvent implements EventHandler<MouseEvent> {

	private Label[] info;
	
	public InformationVisibilityEvent(Label[]info) {
		this.info = info;
	}
	
	@Override
	public void handle(MouseEvent event) {
		boolean selected = ((CheckBox) event.getSource()).isSelected();
		Scene s = ((Node)event.getSource()).getScene();
		for(int i = 0 ; i < info.length ; i++){
			if (i < 4){

			}
			info[i].setVisible(selected);
		}
	}

}
