package com.aits.kronos.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//public class KronosLine {
public class KronosLine extends Application {

	private Stage primaryStage;
	private GridPane rootLayout;
	private Label date;
	private Label[] times;
	private TextField[] fields;
	private SimpleDateFormat display, output;

	public KronosLine() {
		// TODO Auto-generated constructor stub
		display = new SimpleDateFormat("H'h' m'm'");
		output = new SimpleDateFormat("HH:mm");
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Home.class.getResource("../view/KronosLine.fxml"));
			rootLayout = (GridPane) loader.load();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initComponent() {
		date = (Label) rootLayout.getScene().lookup("#Date");
		date.setText("test");
		times = new Label[4];
		fields = new TextField[4];
		Active active = new Active();
		TimeEvent mouseEvent = new TimeEvent(active, times, fields);
		FieldEvent keyEvent = new FieldEvent(active, times, fields);
		for (int i = 0; i < 4; i++) {
			times[i] = (Label) rootLayout.getScene().lookup("#time" + i);
			fields[i] = (TextField) rootLayout.getScene().lookup("#field" + i);
			fields[i].setVisible(false);
			times[i].setOnMouseReleased(mouseEvent);
			fields[i].setOnKeyReleased(keyEvent);
		}
	}

	public SimpleDateFormat getDisplay() {
		return display;
	}

	public void setDisplay(SimpleDateFormat display) {
		this.display = display;
	}

	public SimpleDateFormat getOutput() {
		return output;
	}

	public void setOutput(SimpleDateFormat output) {
		this.output = output;
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
			if (active.id != -1) {
				fields[active.getId()].setVisible(false);
				times[active.getId()].setVisible(true);
			}
			active.setId(i);
			;
			fields[i].setText(parse(times[i].getText()));
			times[i].setVisible(false);
			fields[i].setVisible(true);
			fields[i].requestFocus();
		}

		private int getId(String id) {
			return Integer.parseInt(id.substring(id.length() - 1));
		}

		private String parse(String text) {
			String date = "";
			try {
				Date d = display.parse(text);
				date = output.format(d);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
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
				times[i].setText(parse(fields[i].getText()));
				fields[i].setVisible(false);
				times[i].setVisible(true);
				active.setId(-1);
			}
		}

		private int getId(String id) {
			return Integer.parseInt(id.substring(id.length() - 1));
		}

		private String parse(String text) {
			String date = "";
			try {
				Date d = output.parse(text);
				date = display.format(d);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
		}

	}

	class Active {
		private int id;

		public Active() {
			setId(-1);
		}

		public synchronized int getId() {
			return id;
		}

		public synchronized void setId(int i) {
			this.id = i;
		}
	}

	public Node getNode() {
		return rootLayout;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setMinHeight(60);
		primaryStage.setMinWidth(500);

		initRootLayout();
		// Show the scene containing the root layout.
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		initComponent();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
