package org.softlang.company.model;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Total;

public class Employee implements Cut, Total
{
	String name;
	String address;
	Double salary;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public Double getSalary()
	{
		return salary;
	}

	public void setSalary(Double salary)
	{
		this.salary = salary;
	}

	@Override
	public Double total()
	{
		return salary;
	}

	public Double getSalary()
	{
		return salary;
	}

	public void setSalary(Double salary)
	{
		this.salary = salary;
	}

	@Override
	public void cut()
	{
		salary /= 2;
	}

}
