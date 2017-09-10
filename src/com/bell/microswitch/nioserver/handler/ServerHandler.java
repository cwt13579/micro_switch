
package com.bell.microswitch.nioserver.handler; 

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.bell.microswitch.common.Context;
import com.bell.microswitch.nioserver.Request;
import com.bell.microswitch.nioserver.Response;
import com.bell.microswitch.nioserver.event.ServerListener;


/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年3月13日 下午3:01:24 

 * 类说明 

 */

public class ServerHandler implements ServerListener{
	private static final Logger logger = Logger.getLogger(ServerHandler.class);
	@Override
	public void onError(String paramString) {
		 System.out.println("#onError() :"+paramString);
		
	}

	@Override
	public void onAccept() throws Exception {
		 System.out.println("#onAccept()");
		
	}

	@Override
	public void onAccepted(Request request) throws Exception {
		System.out.println("#onAccepted()");
	}

	@Override
	public void onRead(Request request) throws Exception {
		 Context ctx = new Context();

		    try {
		      logger.info("ServerChannel[" + request.getSsc().getId() + "] received message");
		      request.attach(ctx);
		      ctx.setInMsgByte(request.getDataInput());
		      request.getSsc().process(ctx);
		    } catch (Exception var4) {
		      logger.error("ServerHandler Fail",var4);
		    }
		
	}

	@Override
	public void onWrite(Request request, Response response)throws Exception {
		 Context ctx = (Context)request.attachment();
		    byte[] rspData = ctx.getOutMsgByte();
		    int msgLength = NumberUtils.toInt(request.getSsc().getAttrValue("msglength"), 5);
		    boolean ishead = BooleanUtils.toBoolean(request.getSsc().getAttrValue("ishead", "false"));
		    int len = rspData.length;
		    if(ishead) {
		      len += msgLength;
		    }

		    String rspMsg = StringUtils.leftPad(String.valueOf(len), msgLength, '0');
		    byte[] sendMsg = ArrayUtils.addAll(rspMsg.getBytes(), rspData);
		    response.send(sendMsg);
		    logger.info("ServerChannel[" + request.getSsc().getId() + "] send message sucess");
		
	}

	@Override
	public void onClosed(Request request) throws Exception {
		System.out.println("#onClosed(): " );
		
	}
}
 