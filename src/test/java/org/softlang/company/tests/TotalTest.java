package org.softlang.company.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.softlang.company.MainApp;
import org.softlang.company.model.Company;

public class TotalTest
{

	@Test
	public void testTotal()
	{
		Company c = MainApp.exampleCompany("test");
		double total = c.total();
		assertEquals(2100d, total, 0);
	}

}
