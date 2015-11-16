package org.openoj.compare;
/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public interface OJCompare
{
	/**
	 * 对比程序执行的结果和期望的结果
	 * @param source 程序输出的结果
	 * @param dest 期望的输出结果
	 * @return 如果期望的输出结果和程序的输出结果一致则返回true，否则返回false
	 */
	public boolean compare(String source,String dest);
}
