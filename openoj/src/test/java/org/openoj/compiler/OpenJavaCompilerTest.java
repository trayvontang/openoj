package org.openoj.compiler;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author trayvon
 * @since  2015年11月15日
 * 
 */
public class OpenJavaCompilerTest
{
	OpenJavaCompiler compiler ;
	@Before
	public void setUp() throws Exception
	{
		compiler = new OpenJavaCompiler();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCompileString()
	{
		File file = new File("G:\\xhuoj\\kown2\\src\\kown\\test\\OpenMain.java");
		Assert.assertTrue(compiler.compile(file));
	}

}
