package org.openoj.compiler;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class OpenCPPCompilerTest
{


	OpenCPPCompiler compiler;
	@Before
	public void setUp() throws Exception
	{
		compiler = new OpenCPPCompiler();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test()
	{
		String source = null;
		File file = new File("G:\\xhuoj\\oj\\may01.cpp");
		try
		{
			source = FileUtils.readFileToString(file);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println(compiler.getErrorMessage());
		Assert.assertTrue(compiler.compile(source));
		
	}


}
