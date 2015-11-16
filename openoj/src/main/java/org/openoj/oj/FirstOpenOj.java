package org.openoj.oj;

import org.openoj.compare.OJCompare;
import org.openoj.compiler.Compiler;
import org.openoj.run.OpenRun;

/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class FirstOpenOj implements OpenOJ
{
	private Compiler compiler;
	private OpenRun run;
	private OJCompare compare;
	
	public FirstOpenOj(Compiler compiler, OpenRun run, OJCompare compare)
	{
		super();
		this.compiler = compiler;
		this.run = run;
		this.compare = compare;
	}

	public Compiler getCompiler()
	{
		return compiler;
	}

	public void setCompiler(Compiler compiler)
	{
		this.compiler = compiler;
	}

	public OpenRun getRun()
	{
		return run;
	}

	public void setRun(OpenRun run)
	{
		this.run = run;
	}

	public OJCompare getCompare()
	{
		return compare;
	}

	public void setCompare(OJCompare compare)
	{
		this.compare = compare;
	}

	public String oj(String source,String input,String answer)
	{
		String result ;
		if(!compiler.compile(source))
			return compiler.getErrorMessage();
		result = run.openRun(input);
		if(compare.compare(result, answer))
			return "true";
		else
		return result;
	}

}
