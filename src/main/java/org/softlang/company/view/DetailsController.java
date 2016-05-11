package org.softlang.company.view;

import org.softlang.company.model.Company;
import org.softlang.company.model.CompanyElement;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DetailsController
{

	@FXML
	private Label title;
	@FXML
	private Label nameLabel;
	@FXML
	private Label nameContent;
	@FXML
	private Label addressLabel;
	@FXML
	private Label addressContent;
	@FXML
	private Label salaryLabel;
	@FXML
	private Label salaryContent;

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

	public void setNameLabel(String label)
	{
		this.nameLabel.setText(label);
	}

	public void setName(String name)
	{
		this.nameContent.setText(name);
	}

	public void setAddressLabel(String label)
	{
		this.addressLabel.setText(label);
	}

	public void setAddress(String address)
	{
		this.addressContent.setText(address);
	}

	public void setSalaryLabel(String label)
	{
		this.salaryLabel.setText(label);
	}

	public void setSalary(String salary)
	{
		this.salaryContent.setText(salary);
	}

	/**
	 * Set the current element to show in the Details panel.
	 *
	 * @param element the <code>CompanyElement</code> to show.
	 */
	public void showDetails(CompanyElement element)
	{
		// Dispatch to actual code
		if (element == null)
			clearDetails();
		if (element instanceof Company)
			showDetails((Company) element);
		if (element instanceof Department)
			showDetails((Department) element);
		if (element instanceof Employee)
			showDetails((Employee) element);
	}

	public void clearDetails()
	{
		setContentLayoutX(-1);
		setTitle("");
		setNameLabel("");
		setName("");
		setAddressLabel("");
		setAddress("");
		setSalaryLabel("");
		setSalary("");
	}

	private void showDetails(Company company)
	{
		clearDetails();
		String name = company.getName();
		setTitle("Company");
		setNameLabel("Name:");
		setName(name == null ? "" : name);
	}

	private void showDetails(Department department)
	{
		clearDetails();
		String name = department.getName();
		setTitle("Department");
		setNameLabel("Name:");
		setName(name == null ? "" : name);
	}

	private void showDetails(Employee employee)
	{
		clearDetails();
		String name = employee.getName();
		String address = employee.getAddress();
		String salary = "" + employee.getSalary();
		setTitle("Employee");
		setNameLabel("Name:");
		setName(name == null ? "" : name);
		setAddressLabel("Address:");
		setAddress(address == null ? "" : address);
		setSalaryLabel("Salary:");
		setSalary(salary);
	}
}
