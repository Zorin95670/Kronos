package com.aits.kronos.controller;

import java.io.IOException;

import com.aits.kronos.controller.timeline.ActiveNode;
import com.aits.kronos.controller.timeline.FieldTimeKeyEvent;
import com.aits.kronos.controller.timeline.LabelTimeMouseEvent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Kronos");
		primaryStage.setMinHeight(225);
		primaryStage.setMinWidth(280);

		initRootLayout();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("../view/Main.fxml"));
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
		VBox container = new VBox();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Home.class.getResource("../view/HeaderLine.fxml"));
			container.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		GridPane table = new GridPane();
		int rows = 4;
		int cols = 9;
		Label[] name = new Label[cols];
		Label[] label = new Label[16];
		Label[] info= new Label[16];
		TextField[] field = new TextField[16];
		ColumnConstraints c = new ColumnConstraints(100);
		c.setHalignment(HPos.CENTER);
		for (int col = 0; col < cols; col++)
			table.getColumnConstraints().add(c);
		for (int row = 0; row < rows; row++)
			table.getRowConstraints().add(new RowConstraints(60));

		for (int row = 0, i = 0, j = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if (col == 0) {
					name[row] = initLabel("t" + row);
					table.add(name[row], col, row);
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
		ActiveNode activeNode = new ActiveNode();
		LabelTimeMouseEvent e1 = new LabelTimeMouseEvent(activeNode, label, field);
		FieldTimeKeyEvent e2 = new FieldTimeKeyEvent(activeNode, label, field);
		for (int i = 0 ; i < label.length ; i++) {
			label[i].setOnMouseReleased(e1);
			field[i].setOnKeyReleased(e2);
		}
		container.getChildren().add(table);
		scroll.setContent(container);
		
		
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

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
