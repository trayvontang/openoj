package org.openoj.run;

import java.io.File;

import org.openoj.compiler.OpenCPPCompiler;

/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class RunCPP extends AbstractOpenRun
{

	@Override
	public String getCommand()
	{
		if(!checkExist())
			return null;
		String com =  OpenCPPCompiler.SOURCE_FILE_DIR + "\\"+ OpenCPPCompiler.DEFAULT_EXE_FILE_NAME;
		return com;
	}
	private boolean checkExist()
	{
		File file = new File(OpenCPPCompiler.SOURCE_FILE_DIR + "\\"+ OpenCPPCompiler.DEFAULT_EXE_FILE_NAME);
		 return file.exists();
	}

}

