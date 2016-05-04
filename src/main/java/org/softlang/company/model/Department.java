package org.softlang.company.model;

import java.io.Serializable;

import org.softlang.company.feature.Cut;
import org.softlang.company.feature.Depth;
import org.softlang.company.feature.Total;

public class Department implements Serializable, Cut, Total, Depth
{

	/**
	 *
	 */
	private static final long serialVersionUID = -1099820314969607769L;

	@Override
	public Double total()
	{
		// TODO: total implementation missing
		return 0D;
	}

	@Override
	public Integer departementDepth()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cut()
	{
		// TODO Auto-generated method stub

	}

}
