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

	/**
	 * the salary of a single employee
	 * 
	 * @return the salary
	 */
	@Override
	public Double total()
	{
		return salary;
	}

	/**
	 * cuts the salary of a single employee in half
	 */
	@Override
	public void cut()
	{
		salary /= 2;
	}

}
