package com.aits.kronos.controller.timeline;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

public class InformationVisibilityEvent implements EventHandler<MouseEvent> {

	private Label[] info;
	private Stage stage;
	
	public InformationVisibilityEvent(Stage stage, Label[]info) {
		this.info = info;
		this.stage = stage;
	}
	
	@Override
	public void handle(MouseEvent event) {
		boolean selected = ((CheckBox) event.getSource()).isSelected();
		Scene s = ((Node)event.getSource()).getScene();
		for(int i = 0 ; i < info.length ; i++){
			if (i < 4){
				s.lookup("#info"+i).setVisible(selected);
			}
			info[i].setVisible(selected);
		}
		Window w = stage.getScene().getWindow();
		if(selected){
			((ScrollPane)s.lookup("#scroll")).setPrefWidth(915);
			((ScrollPane)s.lookup("#scroll")).setMaxWidth(915);
			stage.setMinWidth(1110);
			stage.setWidth(1110);
			stage.setMaxWidth(1110);
			w.setX(w.getX()-200);
		} else {
			((ScrollPane)s.lookup("#scroll")).setPrefWidth(515);
			((ScrollPane)s.lookup("#scroll")).setMaxWidth(515);
			stage.setMinWidth(710);
			stage.setWidth(710);
			stage.setMaxWidth(710);
			w.setX(w.getX()+200);
		}
	}

}
