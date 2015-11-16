package org.openoj.compiler;

import java.io.File;
import java.io.InputStream;

/**
 * Compiler 是一个编译器的接口，代表着一个抽象编译器，可以编译String类型的源代码, 并且能够返回错误的信息
 * 
 * @author trayvon
 * @since  2015年11月12日
 * 
 */
public interface Compiler
{
	/**
	 * compile 表示执行编译方法
	 * @param source 代表要编译的代码
	 * @return  如果编译真确则返回true，否则返回 false
	 */
	public boolean compile(String source);
	
	/**
	 * 
	 * @param is 要编译的源代码所代表的输入流
	 * @return 如果编译真确则返回true，否则返回 false
	 */
	public boolean compile(InputStream is);
	
	/**
	 * 
	 * @param sourceFile 元代码说代表的文件
	 * @return 如果编译真确则返回true，否则返回 false
	 */
	public boolean compile(File sourceFile);
	
	/**
	 * 
	 * @param sourceFile 源代码所代表的文件
	 * @param fileEncoding 文件的编码
	 * @return 如果编译真确则返回true，否则返回 false
	 */
	public boolean compile(File sourceFile,String fileEncoding);
	
	/**
	 *  一般先执行compile判断，例如：
	 *  if(!compile(source))
	 *  	getErrorMessage();
	 * @return 代表编译产生的错误信息
	 */
	public String getErrorMessage();
}
