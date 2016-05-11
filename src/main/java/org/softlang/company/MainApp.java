package org.softlang.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;

import org.softlang.company.feature.Serialization;
import org.softlang.company.model.Company;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;
import org.softlang.company.view.DetailsController;
import org.softlang.company.view.RootLayoutController;

import com.google.gson.reflect.TypeToken;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MainApp extends Application implements Serialization<ObservableList<Company>>
{

	private Stage primaryStage;

	private ObservableList<Company> companyData = FXCollections.observableArrayList();

	private DetailsController detailsController;

	File input = new File("input.json");

	/**
	 * Entry Point defined by javafx Application.
	 */
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			// Set Window Title
			this.primaryStage = primaryStage;
			primaryStage.setTitle("101Companies - Java FunctionalFX");

			initRootLayout();

			if (input.exists())
			{
				// try to load from input file
				try
				{
					deserialize(input);
				}
				catch (IOException e)
				{
					System.err.println("Unable to deserialize from " + input);
				}
			}

			if (companyData.size() == 0)
			{
				// Create sample data
				companyData.add(exampleCompany("Company 1"));
				companyData.add(exampleCompany("Company 2"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void stop()
	{
		try
		{
			serialize(input);
		}
		catch (IOException e)
		{
			System.err.println("Not able to serialize to " + input);
		}
	}

	private void initRootLayout()
	{
		try
		{
			// Load root layout fxml
			FXMLLoader fxmlLoader = new FXMLLoader(
					ClassLoader.getSystemResource("org/softlang/company/view/RootLayout.fxml"));
			BorderPane root = fxmlLoader.load();

			RootLayoutController controller = fxmlLoader.getController();
			controller.setMainApp(this);

			// Load details panel fxml
			fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("org/softlang/company/view/DetailsLayout.fxml"));
			FlowPane details = fxmlLoader.load();
			root.setCenter(details);

			detailsController = fxmlLoader.getController();
			detailsController.clearDetails();

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

	public DetailsController getDetailsController()
	{
		return detailsController;
	}

	public void setDetailsController(DetailsController detailsController)
	{
		this.detailsController = detailsController;
	}

	/**
	 * Entry point for the JVM
	 *
	 * @param args command line arguments that were given to the program.
	 */
	public static void main(String[] args)
	{
		// Hand over to javafx application
		launch(args);
	}

	/**
	 * Creates a sample Company containing the members of the Team in a fixed
	 * structure, with name given as name.
	 *
	 * @param name the name of the new <code>Company</code>
	 * @return a newly created Company
	 */
	public static Company exampleCompany(String name)
	{
		Company c = new Company();
		c.setName(name);

		Department d1 = new Department();
		d1.setName("Department 1");
		Employee e1 = new Employee();
		e1.setSalary(600d);
		e1.setName("Steffen Kutscher");
		e1.setAddress("D-55430 Oberwesel, Deutschland");
		d1.getEmployees().add(e1);
		Employee e2 = new Employee();
		e2.setSalary(500d);
		e2.setName("Lukas Christmann");
		e2.setAddress("Deutschland");
		d1.getEmployees().add(e2);

		c.getDepartments().add(d1);

		Department d2 = new Department();
		d2.setName("Department 2");
		Employee e3 = new Employee();
		e3.setSalary(300d);
		e3.setName("Pascal Lief");
		d2.getEmployees().add(e3);
		Employee e4 = new Employee();
		e4.setSalary(400d);
		e4.setName("Lars Erve");
		d2.getEmployees().add(e4);

		c.getDepartments().add(d2);

		Department d3 = new Department();
		d3.setName("Department 3");
		Employee e5 = new Employee();
		e5.setSalary(200d);
		e5.setName("Biggy");
		d3.getEmployees().add(e5);
		Employee e6 = new Employee();
		e6.setSalary(100d);
		e6.setName("Willy");
		d3.getEmployees().add(e6);

		d1.getDepartments().add(d3);

		return c;
	}

	@Override
	public ObservableList<Company> deserialize(File in) throws IOException
	{
		// get generic type information from ObservableList<Company>
		Type type = new TypeToken<ObservableList<Company>>()
		{}.getType();

		// read from file
		ObservableList<Company> list = GSON.fromJson(new BufferedReader(new FileReader(in)), type);

		// add list to companyData to keep listeners
		if (list != null)
		{
			companyData.addAll(list);
		}
		System.out.println("Loaded " + list + " from " + in);

		return companyData;
	}

	@Override
	public void serialize(File out) throws IOException
	{
		try (Writer writer = new FileWriter(out))
		{
			GSON.toJson(companyData, writer);
			System.out.println("Saved companies to " + out);
		}
	}

}
