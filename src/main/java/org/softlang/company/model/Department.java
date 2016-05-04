package org.softlang.company.model;

import java.util.LinkedList;
import java.util.List;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Depth;
import org.softlang.company.feature.Total;

public class Department implements Cut, Total, Depth
{

	private List<Department> depts = new LinkedList<>();
	private List<Employee> employees = new LinkedList<>();

	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public Double total()
	{
		Double dept = depts.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		Double emp = employees.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
		return dept + emp;
	}

	@Override
	public void cut()
	{
		depts.stream().forEach(d -> d.cut());
		employees.stream().forEach(d -> d.cut());
	}

	@Override
	public Integer departementDepth()
	{
		Integer depth = depts.stream().reduce(0, (a, d) -> Math.max(a, d.departementDepth()), (a, d) -> Math.max(a, d));
		return 1 + depth;
	}

}
