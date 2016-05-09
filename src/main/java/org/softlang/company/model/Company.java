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
	private List<Department> depts = new LinkedList<>();

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

	@Override
	public Double total()
	{
		return depts.stream().reduce(0D, (a, d) -> a + d.total(), (a, d) -> a + d);
	}

	@Override
	public void cut()
	{
		depts.stream().forEach(d -> d.cut());
	}

	@Override
	public Integer departementDepth()
	{
		return depts.stream().reduce(0, (a, d) -> Math.max(a, d.departementDepth()), (a, d) -> Math.max(a, d));
	}
}
