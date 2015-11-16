package org.openoj.run;

import java.io.File;

import org.openoj.compiler.AbstractCompiler;
import org.openoj.compiler.OpenJavaCompiler;
import org.openoj.configuration.XMLConfiguration;


/**
 * @author trayvon
 * @since  2015年11月15日
 * 
 */
public  class RunJava extends AbstractOpenRun
{
	/**
	 * 外部程序执行的命令
	 */
	private final static String COMMAND = XMLConfiguration.getValueByNodeNames("javacom");

	@Override
	public String getCommand()
	{
		if(!checkExist())
			return null;
		String com = COMMAND +" "+ AbstractCompiler.WORK_DIR + " "+ OpenJavaCompiler.CLASS_NAME;
		return com;
	}
	private boolean checkExist()
	{
		String path = AbstractCompiler.WORK_DIR + "\\"+ OpenJavaCompiler.CLASS_NAME+".class";
		File file = new File(path);
		 return file.exists();
	}
	
}
