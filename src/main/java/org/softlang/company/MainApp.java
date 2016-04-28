package org.softlang.company;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application
{

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			this.primaryStage = primaryStage;
			primaryStage.setTitle("101Companies - Java FunctionalFX");

			initRootLayout();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void initRootLayout()
	{
		try
		{
			// Load root layout fxml
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("RootLayout.fxml"));
			Parent root = fxmlLoader.load();

			// Show scene with root layout and css
			Scene scene = new Scene(root);
			scene.getStylesheets().add(ClassLoader.getSystemResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		// Company c = SerializationTest.createCompany();
		// System.out.println(c.total());
		// System.exit(0);
		launch(args);
	}

}
