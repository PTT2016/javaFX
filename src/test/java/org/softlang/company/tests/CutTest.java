package org.softlang.company.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.softlang.company.model.Company;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

public class CutTest
{

	@Test
	public void testCut()
	{
		Company c = new Company();
		Department d1 = new Department();
		Department d2 = new Department();
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		c.addDepartments(d1);
		d1.addDepts(d2);
		d1.addEmployees(e1);
		d2.addEmployees(e2);
		e1.setSalary(122d);
		e2.setSalary(124d);

		c.cut();
		double total = c.total();
		assertEquals(246d / 2d, total, 0);
	}

}
