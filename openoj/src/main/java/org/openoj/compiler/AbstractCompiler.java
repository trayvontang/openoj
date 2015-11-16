package org.openoj.compiler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openoj.configuration.XMLConfiguration;


/**
 * @author trayvon
 * @since  2015年11月13日
 * 
 */
public abstract class AbstractCompiler implements Compiler
{
	protected Logger log = LogManager.getLogger();
	/**
	 * 默认的配置文件
	 */
	protected static final String DEFAULT_CONFIG_FILE = "conf/compiler.xml";
	/**
	 * workDir oj系统运行时的工作目录
	 */
	public static final String WORK_DIR = XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE,"workDir");
		
	/**
	 * 编译的错误信息，如果编译正确，errorMessage为空
	 */
	protected  String errorMessage ="";
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
	/**
	 *  确保工作目录存在
	 */
	protected void ensureWorkspace()
	{
		File workspace = new File(WORK_DIR);
		if(!workspace.exists())
			workspace.mkdirs();
	}
	
	protected void ensureWorkspace(String file)
	{
		File workspace = new File(file);
		if(!workspace.exists())
			workspace.mkdirs();
	}
	
	/**
	 *  把代码写入本地文件,默认文件名为当前时间戳
	 * @param source 要写入本地文件的代码
	 */
	protected boolean writeToFile(String source)
	{
		return writeToFile(source,String.valueOf(System.currentTimeMillis()));
	}
	
	
	/**
	 * 把代码写入本地文件用name指定的文件名，如果name为空，则用默认的文件名
	 * @param source 要写入文件的代码
	 * @param name 指定的文件名
	 * @return 返回文件名
	 */
	public abstract boolean writeToFile(String source,String name);
	
	public boolean compile(File file)
	{
		String source;
		try
		{
			source = FileUtils.readFileToString(file);
		} catch (IOException e)
		{
			e.printStackTrace();
			log.error(e.getMessage()+"可能是文件读取错误");
			return false;
		}
		return compile(source);
	}
	
	public boolean compile(File file,String encoding)
	{
		String source;
		try
		{
			source = FileUtils.readFileToString(file,encoding);
		} catch (IOException e)
		{
			e.printStackTrace();
			log.error(e.getMessage()+"可能是文件读取错误");
			return false;
		}
		return compile(source);
	}
	
	public boolean compile(InputStream is)
	{
		String source ;
		try
		{
			source = IOUtils.toString(is);
		} catch (IOException e)
		{
			e.printStackTrace();
			log.error(e.getMessage()+"可能是文件读取错误");
			return false;
		}
		finally{
			IOUtils.closeQuietly(is);
		}
		return compile(source);
	}

}
	