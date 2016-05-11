package org.softlang.company.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.softlang.company.MainApp;
import org.softlang.company.model.Company;

public class CutTest
{

	@Test
	public void testCut()
	{
		Company c = MainApp.exampleCompany("test");
		c.cut();
		double total = c.total();
		assertEquals(2100d / 2d, total, 0);
	}

}
