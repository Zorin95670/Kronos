package com.aits.kronos.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private VBox table;
	
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
		Node header = null;
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Home.class.getResource("../view/HeaderLine.fxml"));
			header = loader.load();

		} catch (IOException e) {
			e.printStackTrace();
		}
		KronosLine line = new KronosLine();
		line.initRootLayout();
		table = (VBox) getPrimaryStage().getScene().lookup("#Table");
		table.getChildren().add(header);
		table.getChildren().add(line.getNode());
		line.initComponent();
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
