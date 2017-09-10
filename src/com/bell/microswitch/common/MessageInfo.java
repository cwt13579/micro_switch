
package com.bell.microswitch.common; 

/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年3月14日 下午7:49:39 

 * 类说明 

 */

public class MessageInfo {
	public final static String XML_SUFFIX = ".xml";
	/**
	 * 请求报文
	 */
	private String reqMsgTemplate; 
	
	/**
	 * 响应解析模板
	 */
	private String respMsgTemplate; 
	
	/**
	 * 请求参数加密
	 */
	private String reqMsgCode;

	public String getReqMsgTemplate() {
		return reqMsgTemplate;
	}

	public void setReqMsgTemplate(String reqMsgTemplate) {
		this.reqMsgTemplate = reqMsgTemplate;
	}

	public String getRespMsgTemplate() {
		return respMsgTemplate;
	}

	public void setRespMsgTemplate(String respMsgTemplate) {
		this.respMsgTemplate = respMsgTemplate;
	}

	public String getReqMsgCode() {
		return reqMsgCode;
	}

	public void setReqMsgCode(String reqMsgCode) {
		this.reqMsgCode = reqMsgCode;
	}
}
 