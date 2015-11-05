package com.aits.kronos.controller.timeline;

import java.io.IOException;

import com.aits.kronos.controller.Home;
import com.aits.kronos.model.Day;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class AbstractTimeLine implements ITimeLine {

	private Node node;
	private Day day;

	@Override
	public Node getNode() {
		return node;
	}

	@Override
	public void setNode(Node node) {
		this.node = node;
	}
	
	@Override
	public void loadNode(String name) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Home.class.getResource(name));
			setNode(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setInformationVisible(boolean visible) {
		if(getNode() != null){
			Node info = null;
			for(int i = 0 ; i < 4 ; i++){
				info = getNode().getScene().lookup("#indo"+i);
				if(info != null){
					info.setVisible(visible);
				}
			}
		}
	}

	@Override
	public void setDay(Day day) {
		this.day = day;
	}

	@Override
	public Day getDay() {
		return day;
	}

}
