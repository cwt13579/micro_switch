package com.bell.microswitch.ctl.utils;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;



/**
 * 项目名称：IUCPTest
 * 类名称：VelocityHelper
 * 类描述：velocity模板处理帮助类
 * 创建人：lifh
 * 创建时间：2015年8月1日下午5:29:53
 * 备注：
 * 版本：@version V1.0
 */
public class VelocityHelper {
	
	public static VelocityEngine ve=new VelocityEngine();
	static{
		ve.init();
	}
	
	
	/**
	 * 方法描述：传参数和模板完成替换
	 * 创建人：lifh
	 * 创建时间：2015年8月1日下午5:31:41
	 * @param faceInfo
	 * @param busiParams
	 * @return
	 * String
	 */
	public static String parseTemplate(String template, Object busiParams){
		
		VelocityContext context = new VelocityContext();
		
		context.put("params", busiParams);
		
		return velocityMerge(template,context);
	}
	
	
	private static String velocityMerge(String template,VelocityContext context ){
		String result="";
		try(StringWriter writer = new StringWriter();) {
			ve.evaluate(context, writer, "", template); 
			writer.flush();
			result= writer.toString();
		} catch (Exception e) {
			result="template parse exception"+e.getMessage();
			e.printStackTrace();
		}
		return result;
	}
}
