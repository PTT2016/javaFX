package org.softlang.company.model;

import java.util.LinkedList;
import java.util.List;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Depth;
import org.softlang.company.feature.Total;

/**
 * A company has a name and consists of (possibly nested) departments.
 */
public class Company implements Cut, Total, Depth
{

	private String name;
	private List<Employee> employees = new LinkedList<>();
	private List<Department> depts = new LinkedList<>();

	public List<Employee> getEmployees()
	{
		return employees;
	}

	public void setEmployees(List<Employee> employees)
	{
		this.employees = employees;
	}

	public void setDepts(List<Department> depts)
	{
		this.depts = depts;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Department> getDepts()
	{
		return depts;
	}

	public void addDepts(Department d)
	{
		this.depts.add(d);
	}

	/**
	 * sums up the salaries of all employees of the company in a functional
	 * manner
	 * 
	 * @return a double containing all salaries
	 */
	@Override
	public Double total()
	{
		Double emp = employees.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		Double dep = depts.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		return emp + dep;
	}

	/**
	 * cuts the salaries of all employees of the company in half
	 *
	 */
	@Override
	public void cut()
	{
		employees.stream().forEach(d -> d.cut());
		depts.stream().forEach(d -> d.cut());
	}

	/**
	 * computes the nesting depth of departments as 1 + the maximum of the
	 * depths of its sub-departments
	 * 
	 * @return the depth of the department
	 */
	@Override
	public Integer departementDepth()
	{
		return depts.stream().reduce(0, (a, d) -> Math.max(a, d.departementDepth()), (a, d) -> Math.max(a, d));
	}
}
