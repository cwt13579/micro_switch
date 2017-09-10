package com.nio.reactor.server;

import java.nio.channels.SocketChannel;

/**
 * 
 * @author 作者姓名 cwt
 * 
 * @version 创建时间：2017年5月18日 下午5:16:28
 * 
 *          请求类
 */

public class Request {

	private SocketChannel sc;
	private byte[] dataInput = null;
	Object obj;

	public Request(SocketChannel sc) {
		this.sc = sc;
	}

	public SocketChannel getSc() {
		return sc;
	}

	public void setSc(SocketChannel sc) {
		this.sc = sc;
	}

	public void attach(Object obj) {
		this.obj = obj;
	}

	public Object attachment() {
		return this.obj;
	}

	public byte[] getDataInput() {
		return this.dataInput;
	}

	public void setDataInput(byte[] dataInput) {
		this.dataInput = dataInput;
	}
}
