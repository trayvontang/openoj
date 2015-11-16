package org.openoj.compare;
/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public class SimpleCompare implements OJCompare
{
	/**
	 * 简单的对比，必须完全一致才认为正确，包括一些特殊的字符
	 */
	public boolean compare(String source, String dest)
	{
		return source.equals(dest);
	}

}
