package org.softlang.company.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Serialization;
import org.softlang.company.feature.Total;

public class Employee extends CompanyElement implements Cut, Total, Serialization<Employee>
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

	@Override
	public Employee deserialize(File in) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(in));
		Employee result = GSON.fromJson(br, Employee.class);
		return result;
	}

	@Override
	public void serialize(File out) throws IOException
	{
		try (Writer writer = new FileWriter(out))
		{
			GSON.toJson(this, writer);
		}
	}

}
