package com.bell.buss.interceptors;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.lang.ArrayUtils;

import com.bell.microswitch.common.Context;
import com.bell.microswitch.common.MessageInfo;
import com.bell.microswitch.common.Module;
import com.bell.microswitch.common.Rescode;
import com.bell.microswitch.model.base._Entry;
import com.bell.microswitch.model.channel.base.IChannelInterceptor;
import com.thoughtworks.xstream.XStream;
/**
 * <p>
 * Title: ServerChannelInterceptor
 * </p>
 * <p>
 * Description: 广州农商特殊报文协议头处理--服务端
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: huateng
 * </p>
 *
 * @author shen_antonio
 * @version 1.0
 */
/*
 * 报头块的格式如下表： 相对位移 长度 属性 名称 值域 说明 1． 0 6 n 报文长度 整个报文的长度含这六位 6 4 4 交易码
 * 不同的报文用不同的交易码 10 16 16 报文MAC 跳过报头部分全包算MAC 26 4 4 MAC机器号 30 1 1 结束标识 #
 */
public class ServerChannelInterceptor extends _Entry<IChannelInterceptor>
		implements IChannelInterceptor {	public IChannelInterceptor getIntance() {
			// TODO Auto-generated method stub
			return this;
		}

		public void doPreRequest(Context ctx) throws Exception {
			// TODO Auto-generated method stub
			byte[] inMsgByte = ctx.getInMsgByte();
			// get txn code
			String txnCode = new String(ArrayUtils.subarray(inMsgByte, 0, 4));
			ctx.setInTxnCode(txnCode);
			//通过场景码 获取 template
			InputStream inputStream=ServerChannelInterceptor.class.getClassLoader().getResourceAsStream(txnCode+MessageInfo.XML_SUFFIX);
			XStream xs=new XStream();
			MessageInfo obj =  (MessageInfo) xs.fromXML(new InputStreamReader(inputStream,"UTF-8"));
			ctx.setInMsgTemplate(obj.getReqMsgTemplate());
			ctx.setOutMsgTemplate(obj.getRespMsgTemplate());
			// mac decryption,待现场实现
			byte[] macByte = ArrayUtils.subarray(inMsgByte, 4, 20);
			// 截取有效XML段
			String result = new String( ArrayUtils.subarray(inMsgByte, 25, inMsgByte.length));
			System.out.println("----cwt server doPreRequest: "+result);
			ctx.setInMsgByte(ArrayUtils.subarray(inMsgByte, 25, inMsgByte.length));
			ctx.setInMsg(new String(ctx.getInMsgByte(),"UTF-8"));
			ctx.setEncoding("UTF-8");
		}

		public void doPostRequest(Context ctx) throws Exception {

		}

		public void doPreRespone(Context ctx) throws Exception {

		}

		public void doPostRespone(Context ctx) throws Exception {
			// TODO Auto-generated method stub
			// xml message
			byte[] outMsg = ctx.getOutMsgByte();
			// 交易码
			String txnCode = ctx.getInTxnCode();
			// Mac encryption，待现场实现
			byte[] macByte = new String("1111111111111111").getBytes();
			// mac 机器号
			String macId = "1111";
			// 结束标识
			byte endFlag = '#';

			String xmlhead = "<?xml version=\"1.0\" encoding=\"gb18030\"?>";

			byte[] allHeadMsg = ArrayUtils.addAll(
					ArrayUtils.addAll(
							ArrayUtils.addAll(txnCode.getBytes(), macByte),
							macId.getBytes()), new byte[] { endFlag });
			byte[] allBodyMsg = ArrayUtils.addAll(xmlhead.getBytes(), outMsg);
			ctx.setOutMsgByte(ArrayUtils.addAll(allHeadMsg, allBodyMsg));
			String result = new String( ArrayUtils.addAll(allHeadMsg, allBodyMsg));
			System.out.println("----cwt server doPostRespone:"+result);
			try {
				ctx.setOutMsg(new String(ctx.getOutMsgByte(), ctx.getEncoding()));
			} catch (Exception e) {
				throw new Exception(Module.SYSTEM_MODULE+","+Rescode.DEFAULT_ERROR+","+"ClientChannelInterceptor doPostRequest Fail", e);
			}
		}

		public void doPreException(Context ctx) throws Exception {
			// TODO Auto-generated method stub
		}

		public void doPostException(Context ctx) throws Exception {
			// TODO Auto-generated method stub
			// xml message
			byte[] outMsg = ctx.getOutMsgByte();
			// 交易码
			String txnCode = ctx.getInTxnCode();
			// Mac encryption，待现场实现
			byte[] macByte = new String("1111111111111111").getBytes();
			// mac 机器号
			String macId = "1111";
			// 结束标识
			byte endFlag = '#';

			String xmlhead = "<?xml version=\"1.0\" encoding=\"gb18030\"?>";

			byte[] allHeadMsg = ArrayUtils.addAll(
					ArrayUtils.addAll(
							ArrayUtils.addAll(txnCode.getBytes(), macByte),
							macId.getBytes()), new byte[] { endFlag });
			byte[] allBodyMsg = ArrayUtils.addAll(xmlhead.getBytes(), outMsg);
			ctx.setOutMsgByte(ArrayUtils.addAll(allHeadMsg, allBodyMsg));
			try {
				ctx.setOutMsg(new String(ctx.getOutMsgByte(), ctx.getEncoding()));
			} catch (Exception e) {
				throw new Exception(Module.SYSTEM_MODULE+","+Rescode.DEFAULT_ERROR+","+"ClientChannelInterceptor doPostRequest Fail" + e.getMessage());
			}
		}

		@Override
		public Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return null;
		}
}
