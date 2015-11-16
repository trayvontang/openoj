package org.openoj.compiler;

import java.io.IOException;
import java.net.URI;

import javax.tools.SimpleJavaFileObject;

/**
 * 
 * @author Trayvon
 * @since  2015年4月29日
 * 
 */
public class StringSourceJavaFileObject extends SimpleJavaFileObject
{
	private String content;

	public StringSourceJavaFileObject(String name, String content)
	{
		super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension), Kind.SOURCE);
		this.content = content;
	}
	
	public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException
	{
		return content;
	}
	
}
