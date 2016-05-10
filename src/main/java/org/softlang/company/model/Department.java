package org.softlang.company.model;

import java.util.LinkedList;
import java.util.List;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Depth;
import org.softlang.company.feature.Total;

public class Department implements Cut, Total, Depth
{

	private String name;
	private List<Department> depts = new LinkedList<>();
	private List<Employee> employees = new LinkedList<>();

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

	public List<Employee> getEmployees()
	{
		return employees;
	}

	public void addEmployees(Employee e)
	{
		this.employees.add(e);
	}

	/**
	 * sum up the salaries of all employees of a specific departement
	 *
	 * @return the sum of the salaries of all subdepartements and all employees
	 *         in the first level
	 */
	@Override
	public Double total()
	{
		Double dept = depts.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		Double emp = employees.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		return dept + emp;
	}

	/**
	 * cuts the salaries of all employees of a specific departement in half
	 */
	@Override
	public void cut()
	{
		depts.stream().forEach(d -> d.cut());
		employees.stream().forEach(d -> d.cut());
	}

	/**
	 * computes the depth of a specific departement
	 * 
	 * @return an integer containing the depth
	 */
	@Override
	public Integer departementDepth()
	{
		Integer depth = depts.stream().reduce(0, (a, d) -> Math.max(a, d.departementDepth()), (a, d) -> Math.max(a, d));
		return 1 + depth;
	}

}
