package com.example.loginapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
	@FXML
	public TextField userNameField;

	@FXML
	public TextField idNoField;

	@FXML
	public Button loginBtn;

	@FXML
	public Button addBtn;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
		loginBtn.setOnAction(event->
		{
			try
			{
				authenticate();
			}
			catch (SQLException e)
			{
				System.out.println(e);
			}
		});

		addBtn.setOnAction(event->
		{
			try
			{
				addRecord();
			}
			catch (SQLException e)
			{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Name register app");
				alert.setHeaderText("id no already exists");
				alert.show();
			}
		});
	}

	private void authenticate() throws SQLException
	{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");
		Statement stmt = con.createStatement();

		if(!idNoField.getText().isEmpty())
		{
			String s = "SELECT name FROM record WHERE s_no="+Integer.parseInt(idNoField.getText());
			ResultSet res = stmt.executeQuery(s);
			StringBuilder name = new StringBuilder();

			while (res.next())
			{
				name.append(res.getString(1));
			}

			if (name.toString().equals(userNameField.getText()))
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Login App");
				alert.setHeaderText("Welcome");
				alert.setContentText(userNameField.getText() + " Have a great day.");
				alert.show();
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Login App");
				alert.setHeaderText("Wrong id no.");
				alert.setContentText("You have entered wrong username or id no.");
				alert.show();
			}
		}
		else
		{
			System.out.println("No id entered");
		}

		con.close();
		stmt.close();
	}

	private void addRecord() throws SQLException
	{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user","root","root");
		Statement stmt = con.createStatement();

		if(!userNameField.getText().isEmpty() && !idNoField.getText().isEmpty())
		{
			String s = "INSERT INTO record VALUES ('" + userNameField.getText() + "'," + Integer.parseInt(idNoField.getText()) + ")";
			stmt.execute(s);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Login App");
			alert.setHeaderText("Record added");
			alert.setContentText("Your name has been added to database. Try logging in again");
			alert.show();
		}

		else
		{
			System.out.println("Values not entered");
		}
	}

	public void reset()
	{
		userNameField.setText("");
		idNoField.setText("");
	}
}
