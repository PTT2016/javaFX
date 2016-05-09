package org.softlang.company.model;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Total;

public class Employee implements Cut, Total
{
	Double salary = 0D;

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
