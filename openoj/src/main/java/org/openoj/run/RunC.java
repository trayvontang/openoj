package org.openoj.run;

import java.io.File;

import org.openoj.compiler.OpenCCompiler;

/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class RunC extends AbstractOpenRun
{

	@Override
	public String getCommand()
	{
		if(!checkExist())
			return null;
		String com =  OpenCCompiler.SOURCE_FILE_DIR + "\\"+ OpenCCompiler.DEFAULT_EXE_FILE_NAME;
		return com;
	}
	private boolean checkExist()
	{
		File file = new File(OpenCCompiler.SOURCE_FILE_DIR + "\\"+ OpenCCompiler.DEFAULT_EXE_FILE_NAME);
		 return file.exists();
	}

}
