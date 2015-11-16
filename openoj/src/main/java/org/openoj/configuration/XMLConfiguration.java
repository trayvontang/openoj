package org.openoj.configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author trayvon
 * @since  2015年11月13日
 * 
 */
public class XMLConfiguration  extends AbstractXMLConfiguration
{
	private static final Logger log = LogManager.getLogger();
	protected static final String DEFAULT_CONFIG_FILE = "conf/compiler.xml";
	/**
	 * 缓存一个XMLConfiguration 供静态方法使用
	 */
	private static XMLConfiguration config= null;
    private InputSource is;
	public XMLConfiguration(File file)
	{
		this.is = new InputSource(file.toURI().toASCIIString());
	}
	public XMLConfiguration(String uri)
	{
		this.is = new InputSource(uri);
	}
	public XMLConfiguration(InputStream in)
	{
		this.is = new InputSource(in);
	}
	@Override
	public Document getDocument()
	{
		final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();     
	    DocumentBuilder builder ;
		try
		{
			builder = builderFactory.newDocumentBuilder();
			return builder.parse(is);
		} catch (SAXException e)
		{
			e.printStackTrace();
			log.error("可能是xml解析错误",e.getMessage());
		} catch (IOException e)
		{
			e.printStackTrace();
			log.error("可能是文件流错误",e.getMessage());
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return null;
	}
	public  String getValueByNodeName(String name)
	{
		Map<String,String> kv = getAllElement();
		return kv.get(name);
	}
	public static String getValueByNodeName(String file,String name)
	{
//		if(config == null) //因为配置文件不以样所以不能缓存
		XMLConfiguration config = new XMLConfiguration(file);
		 return config.getValueByNodeName(name);
	}
	public static String getValueByNodeNames(String name)
	{
		if(config == null)
		  config = new XMLConfiguration(DEFAULT_CONFIG_FILE);
		 return config.getValueByNodeName(name);
	}

}
