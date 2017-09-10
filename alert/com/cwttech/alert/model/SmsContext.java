package com.cwttech.alert.model;

import com.cwttech.alert.base.Context;

public class SmsContext extends Context{

	//esb接口参数
	private static final String MSGSNDTRGTTP = "MsgSndTrgtTp"; // 短信发送目标类型
	private static final String TELNO = "TelNo";//电话号码
	private static final String MSGCNTNTSRCTP = "MsgCntntSrcTp";//短信内容来源类型
	private static final String MSGSNDTXTCNTNT = "MsgSndTxtCntnt";//短信发送的文本内容
	private static final String INSTNO = "InstNo";//机构编号
	
	public  Object getMsgsndtrgttp() {
		return attrs.get(MSGSNDTRGTTP);
	}
	public  Object getTelno() {
		return attrs.get(TELNO);
	}
	public  Object getMsgcntntsrctp() {
		return attrs.get(MSGCNTNTSRCTP);
	}
	public  Object getMsgsndtxtcntnt() {
		return attrs.get(MSGSNDTXTCNTNT);
	}
	public  Object getInstno() {
		return attrs.get(INSTNO);
	}
	public  void setMsgsndtrgttp(String msgsndtrgttp) {
		 attrs.put(MSGSNDTRGTTP,msgsndtrgttp);
	}
	public  void setTelno(String telno) {
		 attrs.put(TELNO,telno);
	}
	public  void setMsgcntntsrctp(String msgcntntsrctp) {
		 attrs.put(MSGCNTNTSRCTP,msgcntntsrctp);
	}
	public  void setMsgsndtxtcntnt(String msgsndtxtcntnt) {
		 attrs.put(MSGSNDTXTCNTNT,msgsndtxtcntnt);
	}
	public  void setInstno(String instno) {
		 attrs.put(INSTNO,instno);
	}
}
