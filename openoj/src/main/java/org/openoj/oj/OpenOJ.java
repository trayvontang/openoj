package org.openoj.oj;
/**
 * @author trayvon
 * @since  2015年11月16日
 * 
 */
public interface OpenOJ
{
	/**
	 * 评判程序
	 * @param source 源代码
	 * @param input 用例输入
	 * @param answer 期望结果
	 * @return
	 */
	public String oj(String source,String input,String answer);
}
