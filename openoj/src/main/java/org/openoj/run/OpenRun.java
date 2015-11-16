package org.openoj.run;

import java.io.File;
import java.io.InputStream;

/**
 * OpenRun 接口是一个运行run方法，为运行的程序提供的是一个输入，可以是输入流，文件，或者String得到输入，返回的是一个
 * 由程序运行之后的输出。
 * @author trayvon
 * @since  2015年11月15日
 * 
 */
public interface OpenRun
{
	/**
	 *  运行的时候是给一个输入，关联到执行程序的输入流上
	 * @param is  给运行程序的输入流
	 * @return 运行得到的结果
	 */
	public String openRun(InputStream is);
	
	/**
	 * 
	 * @param file  把文件作为输入关联到运行程序的输入流上
	 * @return 程序执行的结果
	 */
	
	public String openRun(File file);
	
	/**
	 * 
	 * @param input  把字符串作为运行程序的输入流
	 * @return 程序执行的结果
	 */
	public String openRun(String input);
}
