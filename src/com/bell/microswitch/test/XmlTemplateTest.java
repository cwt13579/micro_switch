
package com.bell.microswitch.test; 

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.bell.buss.interceptors.ServerChannelInterceptor;
import com.bell.microswitch.common.MessageInfo;
import com.bell.microswitch.ctl.utils.VelocityHelper;
import com.thoughtworks.xstream.XStream;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年3月14日 下午7:57:08 

 * 类说明 

 */

public class XmlTemplateTest {

	public static void main(String args[]) throws UnsupportedEncodingException {
		InputStream inputStream=ServerChannelInterceptor.class.getClassLoader().getResourceAsStream("1010.xml");
		XStream xs=new XStream();
		MessageInfo obj =  (MessageInfo) xs.fromXML(new InputStreamReader(inputStream,"UTF-8"));
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("userId", "zkl718");
		paramsMap.put("sessionToken", "");
		paramsMap.put("oldPwd", "1234567");
		paramsMap.put("newPwd", "1111111");
		paramsMap.put("confirmNewPwd", "1111111");
		String reqMsg=VelocityHelper.parseTemplate(obj.getReqMsgTemplate(), paramsMap);
		
		System.out.println(reqMsg);
	}
}
 