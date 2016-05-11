package org.softlang.company.model;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Depth;
import org.softlang.company.feature.Total;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * A company has a name and consists of (possibly nested) departments.
 */
public class Company extends CompanyElement implements Cut, Total, Depth
{

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

	/**
	 * sums up the salaries of all employees of the company in a functional
	 * manner
	 *
	 * @return a double containing all salaries
	 */
	@Override
	public double total()
	{ // stream().reduce() is like a fold in haskell
		Double emp = employees.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		Double dep = departments.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		return emp + dep;
	}

	/**
	 * cuts the salaries of all employees of the company in half
	 *
	 */
	@Override
	public void cut()
	{ // stream().forEach(...) is like a map in haskell with void results
		employees.stream().forEach(d -> d.cut());
		departments.stream().forEach(d -> d.cut());
	}

	/**
	 * computes the nesting depth of departments as 1 + the maximum of the
	 * depths of its sub-departments
	 *
	 * @return the depth of the department
	 */
	@Override
	public int departmentDepth()
	{
		return departments.stream().reduce(0, (a, d) -> Math.max(a, d.departmentDepth()), (a, d) -> Math.max(a, d));
	}

	@Override
	public String toString()
	{
		return getName();
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

}
