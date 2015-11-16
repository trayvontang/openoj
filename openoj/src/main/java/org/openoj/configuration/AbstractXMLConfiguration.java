package org.openoj.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author trayvon
 * @since  2015年11月13日
 * 
 */
public abstract class AbstractXMLConfiguration implements Configuration
{
	/**
	 * 取得一个Document对象，例如：
	 *  DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();     
	 *	DocumentBuilder builder = builderFactory.newDocumentBuilder();
	 *  builder.parse(is);parse的参数非常灵活
	 * @return
	 */ 
	public abstract Document getDocument();
	public Map<String, String> getAllElement() 
	{
		Document document = getDocument();
		Element root = document.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		return getAll(nodes);
	}
	
	/**
	 * 对于给定的NodeList 递归的得到他的所有的叶子节点
	 * @param nodes 根所代表的节点链表，
	 * @return
	 */
	private Map<String,String> getAll(NodeList nodes)
	{
		if(nodes == null)
			return null;
		Map<String,String> kv = new HashMap<String,String>();
		int n = nodes.getLength();
		for(int i=0;i<n;i++)
		{
			Node node = nodes.item(i);
			if(node.getNodeType()== Node.ELEMENT_NODE)
				kv.putAll(getAll(node.getChildNodes()));
			if(node.getNodeType()==Node.TEXT_NODE)
			{
				String key = node.getParentNode().getNodeName();
				String value = node.getTextContent();
				if(StringUtils.isNotEmpty(key.trim())&&StringUtils.isNotEmpty(value.trim()))
					kv.put(key, value);
			}
		}
		return kv;
	}
}
