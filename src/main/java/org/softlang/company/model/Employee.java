package org.softlang.company.model;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Total;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employee implements Cut, Total, CompanyElement
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
	public double total()
	{
		return salary;
	}

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
	public ObservableList<CompanyElement> getChildren()
	{
		return FXCollections.observableArrayList(this);
	}

}
