package org.openoj.run;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class RunCPPTest
{

	RunCPP run;
	@Before
	public void setUp() throws Exception
	{
		run = new RunCPP(); 
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test()
	{
		String result = run.openRun("");
		Assert.assertNotNull(result);
		System.out.println(result);
	}


}
