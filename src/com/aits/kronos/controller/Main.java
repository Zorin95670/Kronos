package com.aits.kronos.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.aits.kronos.controller.timeline.ActiveNode;
import com.aits.kronos.controller.timeline.FieldTimeKeyEvent;
import com.aits.kronos.controller.timeline.InformationVisibilityEvent;
import com.aits.kronos.controller.timeline.LabelTimeMouseEvent;
import com.aits.kronos.model.Day;
import com.aits.kronos.model.Time;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private List<Day> days;
	private Label[] label, date, info;
	private TextField[] field;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Kronos");
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(710);
		primaryStage.setHeight(500);
		primaryStage.setWidth(710);
		primaryStage.setMaxHeight(500);
		primaryStage.setMaxWidth(710);
		primaryStage.setResizable(false);

		days = new ArrayList<>();
		
		Day day = new Day();
		day = new Day();
		try {
			day.setDate(new SimpleDateFormat("dd/MM/yyyy").parse("04/11/2015"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		days.add(day);
		day = new Day();
		day.setDate(new Date());
		day.setTimes(new Time[]{
				new Time(8,24),
				new Time(11,50),
				new Time(12,25),
				new Time(17,50)});
		days.add(day);
		initRootLayout();
	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Home.class.getResource("view"+File.separator+"Main.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			initComponent();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initComponent() {
		ScrollPane scroll = (ScrollPane) getPrimaryStage().getScene().lookup("#scroll");
		scroll.setMaxHeight(300);
		scroll.setPrefWidth(515);
		scroll.setMaxWidth(515);
		VBox container = new VBox();
		container.setAlignment(Pos.TOP_LEFT);
		GridPane table = new GridPane();
		int rows = days.size();
		int cols = 9;
		date = new Label[rows];
		label = new Label[rows*4];
		info= new Label[rows*4];
		field = new TextField[rows*4];
		ColumnConstraints c = new ColumnConstraints(100);
		c.setHalignment(HPos.CENTER);
		for (int col = 0; col < cols; col++)
			table.getColumnConstraints().add(c);
		for (int row = 0; row < rows; row++)
			table.getRowConstraints().add(new RowConstraints(60));

		for (int row = 0, i = 0, j = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (col == 0) {
					date[row] = initLabel("t" + row);
					table.add(date[row], col, row);
				} else if(col < 5){
					label[i] = initLabel(i);
					field[i] = initField(i);
					table.add(field[i], col, row);
					table.add(label[i], col, row);
					i++;
					
				} else {
					info[j] = initLabel("info"+j);
					table.add(info[j], col, row);
					j++;
				}
			}
		}
		initData();
		ActiveNode activeNode = new ActiveNode();
		LabelTimeMouseEvent e1 = new LabelTimeMouseEvent(activeNode, label, field);
		FieldTimeKeyEvent e2 = new FieldTimeKeyEvent(activeNode, label, field);
		for (int i = 0 ; i < label.length ; i++) {
			label[i].setOnMouseReleased(e1);
			field[i].setOnKeyReleased(e2);
		}
		CheckBox checkbox = (CheckBox)getPrimaryStage().getScene().lookup("#check");
		checkbox.setSelected(false);
		checkbox.setOnMouseReleased(new InformationVisibilityEvent(getPrimaryStage(), info));
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Home.class.getResource("view"+File.separator+"HeaderLine.fxml"));
			container.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		container.getChildren().add(table);
		scroll.setContent(container);
		
		for(int i = 0 ; i < info.length ; i++){
			if (i < 4){
				getPrimaryStage().getScene().lookup("#info"+i).setVisible(false);
			}
			info[i].setVisible(false);
		}
		
	}

	private Label initLabel(int index) {
		return initLabel("l" + index);
	}

	private Label initLabel(String id) {
		return initLabel(id, 100, 60);

	}

	private Label initLabel(String id, int width, int height) {
		Label label = new Label(id);
		label.setId(id);
		label.setAlignment(Pos.CENTER);
		label.setMaxHeight(height);
		label.setPrefHeight(height);
		label.setMaxWidth(width);
		label.setPrefWidth(width);
		return label;

	}

	private TextField initField(int index) {
		TextField field = new TextField();
		field.setId("f" + index);
		field.setAlignment(Pos.CENTER);
		field.setMaxHeight(30);
		field.setPrefHeight(30);
		field.setMaxWidth(50);
		field.setPrefWidth(100);
		field.setVisible(false);
		field.setText("f"+index);
		return field;
	}
	
	private void initData(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int second = 0;
		for(int row = 0, i=00 ; row < days.size() ; row++){
			Day day = days.get(row);
			second += day.getCounter().get();
			for(int col = 0 ; col < 4 ; col++, i++ ){
				label[i].setText(day.getTimes()[col].toString());
				switch (col) {
				case 0:
					info[i].setText(day.getTotalDay().toString());
					break;
				case 1:
					info[i].setText(day.getCounter().toString());
					break;
				case 2:
					info[i].setText(day.getMeetTimes().toString());
					break;
				default:
					info[i].setText(LocalTime.ofSecondOfDay(second).toString());
					break;
				}
			}
			date[row].setText(sdf.format(day.getDate()));
		}
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
