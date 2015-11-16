package org.openoj.run;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author trayvon
 * @since  2015年11月15日
 * 
 */
public abstract class AbstractOpenRun implements OpenRun
{
	protected final Logger log = LogManager.getLogger();
	private final Runtime execution = Runtime.getRuntime();
	public String openRun(InputStream is)
	{
		String input = null;
		try
		{
			input = IOUtils.toString(is);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		finally{
			IOUtils.closeQuietly(is);
		}
		return openRun(input);
	}

	public String openRun(File file)
	{
		String input = null;
		try
		{
			 input = FileUtils.readFileToString(file);
		} catch (IOException e)
		{
			e.printStackTrace();
			log.error(e.getMessage()+"可能是参考输入文件不正确");
			return null;
		}
		return openRun(input);
	}
	
	public String openRun(String input)
	{
		InputStream error = null;
		if(input == null)
		{
			log.warn("参考输入为空，请检查参考输入是否真确");
			return "";
		}
		final String tmp = input;
		try
		{
			String cmd = getCommand();
			if(cmd == null) return "可执行文件，不存在";
			final Process process =execution.exec(cmd);
			Timer timer = new Timer();
	        timer.schedule(new TimerTask() {
	            public void run() {
	            	OutputStream out = process.getOutputStream();
	    			try
					{
						out.write(tmp.getBytes());
						out.close();
					} catch (IOException e)
					{
						e.printStackTrace();
						log.error(e.getMessage()+"可能是外部调用的输入流有问题");
					}
	            }
	        }, 0);
			InputStream in = process.getInputStream();
			error = process.getErrorStream();
			return IOUtils.toString(in);
		} catch (IOException e)
		{
			e.printStackTrace();
			log.error(e.getMessage()+"可能是外部运行时的IO错误");
		}
		if(error!=null)
			try
			{
				return IOUtils.toString(error);
			} catch (IOException e)
			{
				e.printStackTrace();
			}finally{
				IOUtils.closeQuietly(error);
			}
		return "timeout";
	}
	public abstract String getCommand();

}
