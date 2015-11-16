package org.openoj.oj;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openoj.compare.OJCompare;
import org.openoj.compare.SimpleCompare;
import org.openoj.compiler.OpenCPPCompiler;
import org.openoj.run.OpenRun;
import org.openoj.run.RunCPP;

/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class FirstOpenOjCPPTest
{
	FirstOpenOj oj ;
	OpenCPPCompiler compiler;
	OJCompare compare;
	OpenRun run;
	@Before
	public void setUp() throws Exception
	{
		compiler = new OpenCPPCompiler();
		compare = new SimpleCompare();
		run = new RunCPP();
		oj = new FirstOpenOj(compiler,run,compare);
	}

	@After
	public void tearDown() throws Exception
	{
	}
	@Test
	public void testCpp()
	{
		String source="";
		String input="";
		String answer="";
		File sourceFile = new File("G:\\oj\\test\\add.cpp");
		File inputFile = new File("G:\\oj\\test\\input.txt");
		File answerFile = new File("G:\\oj\\test\\answer.txt");
		try
		{
			source = FileUtils.readFileToString(sourceFile);
			input = FileUtils.readFileToString(inputFile);
			answer = FileUtils.readFileToString(answerFile);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		String result = oj.oj(source, input, answer);
		Assert.assertEquals("true", result);
		System.out.println(result);
	}



}
