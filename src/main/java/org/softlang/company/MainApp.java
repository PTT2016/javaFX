package org.softlang.company;

import java.io.IOException;

import org.softlang.company.model.Company;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;
import org.softlang.company.view.RootLayoutController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application
{

	private Stage primaryStage;

	private ObservableList<Company> companyData = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage)
	{
		System.out.println("start");
		try
		{
			this.primaryStage = primaryStage;
			primaryStage.setTitle("101Companies - Java FunctionalFX");

			initRootLayout();

			companyData.add(exampleCompany("Company 1"));
			companyData.add(exampleCompany("Company 2"));
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
			BorderPane root = fxmlLoader.load();

			RootLayoutController controller = fxmlLoader.getController();
			controller.setMainApp(this);

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

	public ObservableList<Company> getCompanyData()
	{
		return companyData;
	}

	public void showCompany(Company company)
	{
		// TODO: show company panel with info
	}

	public void showDepartment(Department dept)
	{
		// TODO: show department panel with info
	}

	public void showEmployee(Employee employee)
	{
		// TODO: show employee panel with info
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	Company exampleCompany(String name)
	{
		Company c = new Company();
		c.setName(name);

		Department d1 = new Department();
		d1.setName("Einigkeit");
		Employee e1 = new Employee();
		e1.setSalary(200d);
		d1.getEmployees().add(e1);
		Employee e2 = new Employee();
		e2.setSalary(400d);
		d1.getEmployees().add(e2);

		c.getDepartments().add(d1);

		Department d2 = new Department();
		d1.setName("Freiheit");
		Employee e3 = new Employee();
		e3.setSalary(300d);
		d2.getEmployees().add(e3);
		Employee e4 = new Employee();
		e4.setSalary(600d);
		d2.getEmployees().add(e4);

		c.getDepartments().add(d2);

		return c;
	}

}
