package org.softlang.company.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Depth;
import org.softlang.company.feature.Total;

/**
 * A company has a name and consists of (possibly nested) departments.
 */
public class Company implements Serializable, Cut, Total, Depth
{

	private static final long serialVersionUID = -200889592677165250L;

	private String name;
	private List<Department> depts = new LinkedList<Department>();

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

	@Override
	public Double total()
	{
		// TODO: total implementation missing
		return 0D;
	}

	@Override
	public void cut()
	{
		// TODO: cut implementation missing
	}

	@Override
	public Integer departementDepth()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
