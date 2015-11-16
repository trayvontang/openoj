package org.openoj.configuration;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author trayvon
 * @since  2015年11月13日
 * 
 */
public class XMLConfigurationTest
{
	XMLConfiguration config ;
	@Before
	public void setUp() throws Exception
	{
		config = new XMLConfiguration("conf/compiler.xml");
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testGetValueByNodeName1()
	{
		Assert.assertEquals("g:\\oj\\workplace\\java", config.getValueByNodeName("java"));
	}
	@Test
	public void testGetValueByNodeName2()
	{
		Assert.assertEquals("g:\\oj\\workplace\\c", config.getValueByNodeName("cpp"));
	}
	@Test
	public void testGetValueByNodeName3()
	{
		Assert.assertEquals("true", config.getValueByNodeName("save"));
	}
	@Test
	public void testGetAllElement()
	{
		Map<String,String> maps = config.getAllElement();
		Assert.assertNotNull(maps);
		for(Entry<String, String> entry:maps.entrySet())
		{
			System.out.println("key:"+entry.getKey()+"   value:"+entry.getValue());
		}
	}

}
