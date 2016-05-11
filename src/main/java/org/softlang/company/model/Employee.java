package org.softlang.company.model;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Total;

public class Employee extends CompanyElement implements Cut, Total
{
	String address;
	double salary;

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public double getSalary()
	{
		return salary;
	}

	public void setSalary(double salary)
	{
		this.salary = salary;
	}

	/**
	 * the salary of a single employee
	 *
	 * @return the salary
	 */
	@Override
	public double total()
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

	@Override
	public String toString()
	{
		return getName();
	}

}
