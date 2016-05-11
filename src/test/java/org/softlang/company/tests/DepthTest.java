package org.softlang.company.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.softlang.company.MainApp;
import org.softlang.company.model.Company;

public class DepthTest
{

	@Test
	public void testDepth()
	{
		Company c = MainApp.exampleCompany("test");
		int depth = c.departmentDepth();
		assertEquals(2, depth, 0);
	}

}
