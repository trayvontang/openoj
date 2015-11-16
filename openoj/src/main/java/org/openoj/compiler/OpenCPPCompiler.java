package org.openoj.compiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;
import org.openoj.configuration.XMLConfiguration;

/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class OpenCPPCompiler extends AbstractCompiler
{
	/**
	 * c++默认的输出源代码的目录
	 */
	public static final String  SOURCE_FILE_DIR = XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE, "cppsource");
	
	/**
	 * c++默认输出的文件名
	 */
	public static final String DEFAULT_OUT_FILE_NAME = XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE, "cppoutfile");
	
	/**
	 * c++默认输出的可执行文件名
	 */
	public static final String DEFAULT_EXE_FILE_NAME = XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE, "exefile");
	/**
	 * 编译命令用g++编译指定文件，输出用默认的文件名输出的可执行文件
	 */
	private final String compileCCommand = "g++ "+SOURCE_FILE_DIR+"\\"+DEFAULT_OUT_FILE_NAME+" -o "+SOURCE_FILE_DIR+"\\"+DEFAULT_EXE_FILE_NAME;
	
	public boolean compile(String source)
	{
		writeToFile(source,DEFAULT_OUT_FILE_NAME);
		Runtime run = Runtime.getRuntime();
		Process process = null;
		try
		{
			process = run.exec(compileCCommand);
			InputStream err = process.getErrorStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(err));
			errorMessage=reader.readLine();
			if(errorMessage==null||errorMessage.length()==0) return true;
			else
			{
				String str;
				StringBuffer sb = new StringBuffer();
				while((str=reader.readLine())!=null)
				{
					sb.append(str+"\n");
				}
				errorMessage = errorMessage+sb.toString();
				return false;
			}
			
		} catch (IOException e)
		{
			log.error(e.getMessage()+"编译cpp时的错误流出错");
			return false;
		}
	}

	@Override
	public boolean writeToFile(String source, String name)
		{
			ensureWorkspace(SOURCE_FILE_DIR);
			File file = new File(SOURCE_FILE_DIR,name);
			try
			{
				FileUtils.write(file, source);
			} catch (IOException e)
			{
				e.printStackTrace();
				log.error(e.getMessage()+"可能是文件输出错误");
				return false;
			}
			return true;
	}

}
