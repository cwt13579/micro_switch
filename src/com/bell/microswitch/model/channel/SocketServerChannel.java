
package com.bell.microswitch.model.channel; 

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.bell.microswitch.model.channel.base._ServerChannel;
import com.bell.microswitch.nioserver.Server;

/** 

 * @author 作者姓名 cwt

 * @version 创建时间：2017年3月14日 上午9:31:10 

 * 类说明 

 */

public class SocketServerChannel  extends _ServerChannel {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SocketServerChannel.class);
	private String ip;
	private int port;
	private int timeout;
	private int msglength;
	private boolean ishead;
	private boolean auto = true;
	private int threadpoolsize;
	private Server server;
	private Thread tServer;
	public SocketServerChannel() {
	}

	public void init() throws Exception {
		this.ip = this.getAttrValue("ip");
		this.port = NumberUtils.toInt(this.getAttrValue("port"));
		this.timeout = NumberUtils.toInt(this.getAttrValue("timeout"), 60);
		this.msglength = NumberUtils.toInt(this.getAttrValue("msglength"), 5);
		this.threadpoolsize = NumberUtils.toInt(this.getAttrValue("threadpoolsize"), 4);
		this.auto = BooleanUtils.toBoolean(this.getAttrValue("auto", "true"));
		this.ishead = BooleanUtils.toBoolean(this.getAttrValue("ishead", "false"));
		//this.getFormat().init();
	}
	@Override
	public void start() throws Exception {
		logger.info("服务开启中");
		try {
			this.server = new Server(this.ip, this.port, this.threadpoolsize, this.timeout, this.msglength, this.ishead,this);
			this.tServer = new Thread(this.server);
			this.tServer.start();
		} catch (Exception var2) {
			logger.error("SocketServerChannel Start Error", var2);
			throw var2;
		}  
		logger.info("服务开启完成");	
	}

	@Override
	public void stop() throws Exception {
	 
		
	}

	@Override
	public void restart() throws Exception {
 
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		 
		return null;
	}

}
 