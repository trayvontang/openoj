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
public class OpenCCompiler extends AbstractCompiler
{
	/**
	 * 存放c代码源文件的目录
	 */
	public static final String  SOURCE_FILE_DIR = XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE, "csource");
	
	/**
	 * c代码的输出文件名
	 */
	public static final String DEFAULT_OUT_FILE_NAME = XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE, "coutfile");
	
	/**
	 * c代码的输出的可执行文件名字
	 */
	public static final String DEFAULT_EXE_FILE_NAME = XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE, "exefile");
	/**
	 * 编译命令用gcc编译指定的c源文件，并且输出的可执行文件的命令
	 */
	private final String compileCCommand = "gcc "+SOURCE_FILE_DIR+"\\"+DEFAULT_OUT_FILE_NAME+" -o "+SOURCE_FILE_DIR+"\\"+DEFAULT_EXE_FILE_NAME;
	
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
			log.error(e.getMessage()+"编译c时的错误流出错");
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
