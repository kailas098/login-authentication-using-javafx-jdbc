package com.example.loginapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
	Controller c;
	@Override
	public void start(Stage stage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));

		Pane root = loader.load();
		c = loader.getController();

		root.getChildren().add(0,createMenu());

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Login App");
		stage.show();
	}

	private MenuBar createMenu()
	{
		Menu options = new Menu("Options");
		MenuItem clear = new MenuItem("Clear");
		clear.setOnAction(event->c.reset());
		MenuItem about = new MenuItem("About");
		about.setOnAction(event->
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Login App");
			alert.setHeaderText("About");
			alert.setContentText("Simple user login app");
			alert.show();
		});
		MenuItem credit = new MenuItem("Credit");
		credit.setOnAction(event->
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Login App");
			alert.setHeaderText("Credits");
			alert.setContentText("Kailas Nath S");
			alert.show();
		});

		options.getItems().addAll(clear,about,credit);
		MenuBar mb = new MenuBar(options);
		mb.prefWidth(300.0);
		return mb;
	}
}
