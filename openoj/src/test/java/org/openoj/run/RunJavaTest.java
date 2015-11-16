package org.openoj.run;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author trayvon
 * @since  2015年11月15日
 * 
 */
public class RunJavaTest
{
	RunJava run ;
	@Before
	public void setUp() throws Exception
	{
		run = new RunJava();
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
