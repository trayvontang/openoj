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
import org.openoj.compiler.OpenCCompiler;
import org.openoj.run.OpenRun;
import org.openoj.run.RunC;

/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class FirstOpenOjCTest
{
	FirstOpenOj oj ;
	OpenCCompiler compiler;
	OJCompare compare;
	OpenRun run;
	@Before
	public void setUp() throws Exception
	{
		compiler = new OpenCCompiler();
		compare = new SimpleCompare();
		run = new RunC();
		oj = new FirstOpenOj(compiler,run,compare);
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testC()
	{
		String source="",input="",answer="";
		File sourceFile = new File("G:\\oj\\test\\add.c");
		File inputFile = new File("G:\\oj\\test\\input.txt");
		File answerFile = new File("G:\\oj\\test\\answer.txt");
		try
		{
			source = FileUtils.readFileToString(sourceFile);
			input = FileUtils.readFileToString(inputFile);
			answer = FileUtils.readFileToString(answerFile);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = oj.oj(source, input, answer);
		Assert.assertEquals("true", result);
		System.out.println(result);
	}
}
