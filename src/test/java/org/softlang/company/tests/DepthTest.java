package org.softlang.company.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.softlang.company.model.Company;
import org.softlang.company.model.Department;
import org.softlang.company.model.Employee;

public class DepthTest
{

	@Test
	public void testDepth()
	{
		Company c = new Company();
		Department d1 = new Department();
		Department d2 = new Department();
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		c.addDepts(d1);
		d1.addDepts(d2);
		d1.addEmployees(e1);
		d2.addEmployees(e2);

		int depth = c.departementDepth();
		assertEquals(2, depth, 0);
	}

}
