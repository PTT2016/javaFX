package org.softlang.company.model;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Depth;
import org.softlang.company.feature.Total;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class Department extends CompanyElement implements Cut, Total, Depth
{

	private ObservableList<Department> departments = FXCollections.observableArrayList();
	private ObservableList<Employee> employees = FXCollections.observableArrayList();

	public ObservableList<Department> getDepartments()
	{
		return departments;
	}

	public ObservableList<Employee> getEmployees()
	{
		return employees;
	}

	/**
	 * sum up the salaries of all employees of a specific departement
	 *
	 * @return the sum of the salaries of all subdepartements and all employees
	 *         in the first level
	 */
	@Override
	public double total()
	{
		Double dept = departments.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		Double emp = employees.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		return dept + emp;
	}

	/**
	 * cuts the salaries of all employees of a specific departement in half
	 */
	@Override
	public void cut()
	{
		departments.stream().forEach(d -> d.cut());
		employees.stream().forEach(d -> d.cut());
	}

	/**
	 * computes the depth of a specific departement
	 *
	 * @return an integer containing the depth
	 */
	@Override
	public int departmentDepth()
	{
		Integer depth = departments.stream().reduce(0, (a, d) -> Math.max(a, d.departmentDepth()),
				(a, d) -> Math.max(a, d));
		return 1 + depth;
	}

	/**
	 * lists all the employees and all the departements
	 * used for the access in the GUI
	 */
	@Override
	public ObservableList<CompanyElement> getChildren()
	{
		ObservableList<CompanyElement> children = FXCollections.observableArrayList();
		children.addAll(getEmployees());
		children.addAll(getDepartments());
		return children;
	}

	/**
	 * all changes in the structure of employees and departements are given to
	 * the listener
	 * used in the GUI
	 */
	@Override
	public void addListChangeListener(ListChangeListener<CompanyElement> listener)
	{
		employees.addListener(listener);
		departments.addListener(listener);
	}

	/**
	 * removes the listener if not needed anymore
	 */
	@Override
	public void removeListChangeListener(ListChangeListener<CompanyElement> listener)
	{
		employees.removeListener(listener);
		departments.removeListener(listener);
	}

	@Override
	public String toString()
	{
		return getName();
	}

}
