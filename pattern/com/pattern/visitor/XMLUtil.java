package com.pattern.visitor;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;
public class XMLUtil
{
//�÷������ڴ�XML�����ļ�����ȡ��������������һ��ʵ�����
	public static Object getBean()
	{
		try
		{
			//�����ĵ�����
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;							
			//doc = builder.parse(new File("E:\\ht\\dev\\micro_switch\\pattern\\com\\pattern\\visitor\\config.xml")); 
			doc = builder.parse(XMLUtil.class.getResourceAsStream("config.xml")); 
		
			//��ȡ��������ı��ڵ�
			NodeList nl = doc.getElementsByTagName("className");
            Node classNode=nl.item(0).getFirstChild();
            String cName=classNode.getNodeValue();
            
            //ͨ���������ʵ����󲢽��䷵��
            Class c=Class.forName(cName);
	  	    Object obj=c.newInstance();
            return obj;
           }   
           	catch(Exception e)
           	{
           		e.printStackTrace();
           		return null;
           	}
		}
}
