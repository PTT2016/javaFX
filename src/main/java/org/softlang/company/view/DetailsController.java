package org.softlang.company.view;

import org.softlang.company.model.Company;
import org.softlang.company.model.CompanyElement;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DetailsController
{

	@FXML
	private Label title;
	@FXML
	private Label nameLabel;
	@FXML
	private TextField nameContent;
	@FXML
	private Label addressLabel;
	@FXML
	private TextField addressContent;
	@FXML
	private Label salaryLabel;
	@FXML
	private TextField salaryContent;

	private CompanyElement currentElement = null;

	/**
	 * Called by javafx on initialization.
	 */
	@FXML
	public void initialize()
	{
		nameContent.textProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				setElementName(newValue);
			}
		});
		salaryContent.textProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				try
				{
					setElementSalary(newValue);
				}
				catch (NumberFormatException e)
				{
					if (currentElement != null)
						salaryContent.setText(String.valueOf(((Employee) currentElement).getSalary()));
				}
			}
		});
		addressContent.textProperty().addListener(new ChangeListener<String>()
		{

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
			{
				setElementAddress(newValue);
			}
		});

	}

	private void setElementName(String text)
	{
		if (currentElement != null)
		{
			if (!text.equals(currentElement.getName()))
			{
				currentElement.setName(text);
			}
		}
	}

	private void setElementSalary(String text)
	{
		if (currentElement != null)
		{
			if (currentElement instanceof Employee)
			{
				// TODO: convert value
				double salary = Double.valueOf(text);
				((Employee) currentElement).setSalary(salary);
			}
		}
	}

	private void setElementAddress(String text)
	{
		if (currentElement != null)
		{
			if (currentElement instanceof Employee)
			{
				((Employee) currentElement).setAddress(text);
			}
		}
	}

	public void setContentLayoutX(double layoutX)
	{
		if (layoutX < 0)
			layoutX = 60;
		nameContent.setLayoutX(layoutX);
		addressContent.setLayoutX(layoutX);
		salaryContent.setLayoutX(layoutX);
	}

	public void setTitle(String title)
	{
		this.title.setText(title);
	}

	public void setName(String name)
	{
		this.nameContent.setText(name);
		this.nameLabel.setVisible(true);
		this.nameContent.setVisible(true);
	}

	public void setAddress(String address)
	{
		this.addressContent.setText(address);
		this.addressLabel.setVisible(true);
		this.addressContent.setVisible(true);
	}

	public void setSalary(String salary)
	{
		this.salaryContent.setText(salary);
		this.salaryLabel.setVisible(true);
		this.salaryContent.setVisible(true);
	}

	/**
	 * Set the current element to show in the Details panel.
	 *
	 * @param element the <code>CompanyElement</code> to show.
	 */
	public void showDetails(CompanyElement element)
	{
		currentElement = null;
		// Dispatch to actual code
		if (element == null)
		{
			clearDetails();
			return;
		}
		else if (element instanceof Company)
			showDetails((Company) element);
		else if (element instanceof Department)
			showDetails((Department) element);
		else if (element instanceof Employee)
			showDetails((Employee) element);

		currentElement = element;
	}

	public void clearDetails()
	{
		currentElement = null;
		setContentLayoutX(-1);

		nameLabel.setVisible(false);
		addressLabel.setVisible(false);
		salaryLabel.setVisible(false);

		nameContent.setVisible(false);
		addressContent.setVisible(false);
		salaryContent.setVisible(false);

		setTitle("");
	}

	private void showDetails(Company company)
	{
		clearDetails();
		String name = company.getName();

		setTitle("Company");
		setName(name == null ? "" : name);
	}

	private void showDetails(Department department)
	{
		clearDetails();
		String name = department.getName();

		setTitle("Department");
		setName(name == null ? "" : name);
	}

	private void showDetails(Employee employee)
	{
		clearDetails();
		String name = employee.getName();
		String address = employee.getAddress();
		String salary = "" + employee.getSalary();

		setTitle("Employee");
		setName(name == null ? "" : name);
		setAddress(address == null ? "" : address);
		setSalary(salary);
	}
}
