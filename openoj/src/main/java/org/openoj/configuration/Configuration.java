package org.openoj.configuration;

import java.util.Map;

/**
 * @author trayvon
 * @since  2015年11月13日
 * 
 */
public interface Configuration
{
	/**
	 * 取出所有的叶子节点的键和值
	 * @return 所有叶子节点代表的键值对
	 */
	public Map<String,String> getAllElement();
}
