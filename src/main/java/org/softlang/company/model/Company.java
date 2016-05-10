package org.softlang.company.model;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Depth;
import org.softlang.company.feature.Total;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A company has a name and consists of (possibly nested) departments.
 */
public class Company implements Cut, Total, Depth, CompanyElement
{

	private String name;
	private ObservableList<Employee> employees = FXCollections.observableArrayList();
	private ObservableList<Department> departments = FXCollections.observableArrayList();

	public ObservableList<Employee> getEmployees()
	{
		return employees;
	}

	public ObservableList<Department> getDepartments()
	{
		return departments;
	}

	public void addDepartments(Department d)
	{
		this.departments.add(d);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public double total()
	{
		Double emp = employees.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		Double dep = departments.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		return emp + dep;
	}

	@Override
	public void cut()
	{
		employees.stream().forEach(d -> d.cut());
		departments.stream().forEach(d -> d.cut());
	}

	/**
	 * sums up the salaries of all employees of the company in a functional
	 * manner
	 * 
	 * @return a double containing all salaries
	 */
	@Override
	public int departmentDepth()
	{
		return departments.stream().reduce(0, (a, d) -> Math.max(a, d.departmentDepth()), (a, d) -> Math.max(a, d));
	}

	/**
	 * cuts the salaries of all employees of the company in half
	 *
	 */
	@Override
	public String toString()
	{
		return getName();
	}

	/**
	 * computes the nesting depth of departments as 1 + the maximum of the
	 * depths of its sub-departments
	 * 
	 * @return the depth of the department
	 */
	@Override
	public ObservableList<CompanyElement> getChildren()
	{
		ObservableList<CompanyElement> children = FXCollections.observableArrayList();
		children.addAll(getEmployees());
		children.addAll(getDepartments());
		return children;
	}

}
