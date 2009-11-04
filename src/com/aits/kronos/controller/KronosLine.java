package com.aits.kronos.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KronosLine extends Application {

	private Stage primaryStage;
	private GridPane rootLayout;
	private Label date;
	private Label[] times;
	private TextField[] fields;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setMinHeight(60);
		primaryStage.setMinWidth(500);

		initRootLayout();
		initComponent();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Home.class.getResource("../view/KronosLine.fxml"));
			rootLayout = (GridPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initComponent() {
		date = (Label) getPrimaryStage().getScene().lookup("#Date");
		date.setText("test");
		times = new Label[4];
		fields = new TextField[4];
		Active active = new Active();
		TimeEvent mouseEvent = new TimeEvent(active, times, fields);
		FieldEvent keyEvent = new FieldEvent(active, times, fields);
		for (int i = 0; i < 4; i++) {
			times[i] = (Label) getPrimaryStage().getScene().lookup("#time" + i);
			fields[i] = (TextField) getPrimaryStage().getScene().lookup("#field" + i);
			fields[i].setVisible(false);
			times[i].setOnMouseReleased(mouseEvent);
			fields[i].setOnKeyReleased(keyEvent);
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	class TimeEvent implements EventHandler<MouseEvent> {
		private Active active;
		private Label[] times;
		private TextField[] fields;
		
		
		public TimeEvent(Active active, Label[] times, TextField[] fields) {
			this.active = active;
			this.times = times;
			this.fields = fields;
		}

		@Override
		public void handle(MouseEvent event) {
			Label source = (Label) event.getSource();
			int i = getId(source.getId());
			if(active.id != -1){
				fields[active.getId()].setVisible(false);
				times[active.getId()].setVisible(true);
			}
			active.setId(i);;
			fields[i].setText(times[i].getText());
			times[i].setVisible(false);
			fields[i].setVisible(true);
			fields[i].requestFocus();
		}

		private int getId(String id) {
			return Integer.parseInt(id.substring(id.length() - 1));
		}

	}

	class FieldEvent implements EventHandler<KeyEvent> {
		private Active active;
		private Label[] times;
		private TextField[] fields;

		public FieldEvent(Active active, Label[] times, TextField[] fields) {
			this.active = active;
			this.times = times;
			this.fields = fields;
		}

		@Override
		public void handle(KeyEvent event) {
			if (event.getCode().equals(KeyCode.ENTER)) {
				TextField source = (TextField) event.getSource();
				int i = getId(source.getId());
				times[i].setText(fields[i].getText());
				fields[i].setVisible(false);
				times[i].setVisible(true);
				active.setId(-1);
			}
		}

		private int getId(String id) {
			return Integer.parseInt(id.substring(id.length() - 1));
		}

	}
	
	class Active {
		private int id;
		public Active() {
			setId(-1);
		}
		public synchronized int getId(){
			return id;
		}
		public synchronized void setId(int i){
			this.id = i;
		}
	}
}
