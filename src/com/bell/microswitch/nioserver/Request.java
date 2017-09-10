
package com.bell.microswitch.nioserver; 

import java.net.InetAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

import com.bell.microswitch.model.channel.SocketServerChannel;

/** 

 * @author cwt

 * @version 创建时间：2017年3月13日 下午3:02:03 

 * 类说明 

 */

public class Request {
	 private SocketChannel sc;
	  private SocketServerChannel ssc;
	  private byte[] dataInput = null;
	  Object obj;

	  public Request(SocketServerChannel ssc, SocketChannel sc) {
	    this.sc = sc;
	    this.ssc = ssc;
	  }

	  public SocketChannel getSc() {
	    return this.sc;
	  }

	  public void setSc(SocketChannel sc) {
	    this.sc = sc;
	  }

	  public SocketServerChannel getSsc() {
	    return this.ssc;
	  }

	  public void setSsc(SocketServerChannel ssc) {
	    this.ssc = ssc;
	  }

	  public InetAddress getAddress() {
	    return this.sc.socket().getInetAddress();
	  }

	  public int getPort() {
	    return this.sc.socket().getPort();
	  }

	  public boolean isConnected() {
	    return this.sc.isConnected();
	  }

	  public boolean isBlocking() {
	    return this.sc.isBlocking();
	  }

	  public boolean isConnectionPending() {
	    return this.sc.isConnectionPending();
	  }

	  public boolean getKeepAlive() throws SocketException {
	    return this.sc.socket().getKeepAlive();
	  }

	  public int getSoTimeout() throws SocketException {
	    return this.sc.socket().getSoTimeout();
	  }

	  public boolean getTcpNoDelay() throws SocketException {
	    return this.sc.socket().getTcpNoDelay();
	  }

	  public boolean isClosed() {
	    return this.sc.socket().isClosed();
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
 