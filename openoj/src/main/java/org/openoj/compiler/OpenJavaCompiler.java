package org.openoj.compiler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;
import org.openoj.configuration.XMLConfiguration;

/**
 * @author trayvon
 * @since  2015年11月15日
 * 
 */
public class OpenJavaCompiler extends AbstractCompiler
{
	/**
	 * java 源文件默认输出的目录
	 */
	private static final String  SOURCE_FILE_DIR = XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE, "javasource");
	
	/**
	 * java 的主类名，为了方便，没有通过解析的方式来得到main方法所在的类名，而是采用配置的方式
	 */
	public  static final String CLASS_NAME = XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE, "javamain");
	private DiagnosticCollector<JavaFileObject> diagnostic;
	
	@Override
	public String getErrorMessage()
	{
		StringBuffer errorInfo = new StringBuffer();
		for(Diagnostic<?> d : diagnostic.getDiagnostics())
		{
			errorInfo.append("错误信息： "+d.getMessage(Locale.CANADA)+"\n");
			errorInfo.append("错误所在的行数： "+d.getLineNumber());

		}
		return errorInfo.toString();
	}

	public boolean compile(String source)
	{
		ensureWorkspace();
		source = source.replaceAll("^package.*", "");//为了简化运行，所以编译的时候先把包名去掉
		if("true".equals(XMLConfiguration.getValueByNodeName(DEFAULT_CONFIG_FILE, "save")))
			writeToFile(source,CLASS_NAME);
		 JavaCompiler compiler;
		 StandardJavaFileManager standardJavaFileManager;
		 CompilationTask task;
		 JavaFileObject sourceObject ;
		 Iterable<? extends JavaFileObject>  fileObject;
		
		 compiler =  ToolProvider.getSystemJavaCompiler();
			if(compiler==null)
				log.error("没有得到系统编译器，请检JAVA_HOME\\lib\\tool.jar是否存在");
			
		 sourceObject = new StringSourceJavaFileObject(CLASS_NAME,source);
		 fileObject = Arrays.asList(sourceObject);
		 standardJavaFileManager =  compiler.getStandardFileManager(null, null, null);
		 diagnostic = new DiagnosticCollector<JavaFileObject>();
		 task = compiler.getTask(null, standardJavaFileManager, diagnostic, Arrays.asList("-d",WORK_DIR,"-encoding","utf-8"), null, fileObject);
		return  task.call();
	}

	@Override
	public boolean writeToFile(String source, String name)
	{
		ensureWorkspace(SOURCE_FILE_DIR);
		File file = new File(SOURCE_FILE_DIR,name+".java");
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
