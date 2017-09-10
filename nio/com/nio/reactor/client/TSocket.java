
package com.nio.reactor.client; 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

import org.apache.log4j.Logger;
import org.apache.thrift.transport.TTransportException;


/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年5月24日 下午4:08:39 

 * 连接服务端socket

 */

public class TSocket extends TIOStreamTransport{
	  private static final Logger LOGGER = Logger.getLogger(TSocket.class.getName());
	  private Socket socket_;
	  private String host_;
	  private int port_;
	  private int socketTimeout_;
	  private int connectTimeout_;
	  public TSocket(Socket socket) throws TTransportException {
		    socket_ = socket;
		    try {
		      socket_.setSoLinger(false, 0);
		      socket_.setTcpNoDelay(true);
		      socket_.setKeepAlive(true);
		    } catch (SocketException sx) {
		      LOGGER.warn("Could not configure socket.", sx);
		    }

		    if (isOpen()) {
		      try {
		        inputStream_ = new BufferedInputStream(socket_.getInputStream(), 1024);
		        outputStream_ = new BufferedOutputStream(socket_.getOutputStream(), 1024);
		      } catch (IOException iox) {
		        close();
		        throw new TTransportException(TTransportException.NOT_OPEN, iox);
		      }
		    }
		  }
	  public TSocket(String host, int port) {
		    this(host, port, 0);
	  }
	  
	  public TSocket(String host, int port, int timeout) {
		    this(host, port, timeout, timeout);
	  }
	  
	  public TSocket(String host, int port, int socketTimeout, int connectTimeout) {
		    host_ = host;
		    port_ = port;
		    socketTimeout_ = socketTimeout;
		    connectTimeout_ = connectTimeout;
		    initSocket();
      }
	  
	  private void initSocket() {
		    socket_ = new Socket();
		    try {
		      socket_.setSoLinger(false, 0);
		      socket_.setTcpNoDelay(true);
		      socket_.setKeepAlive(true);
		      socket_.setSoTimeout(socketTimeout_);
		    } catch (SocketException sx) {
		      LOGGER.error("Could not configure socket.", sx);
		    }
		}
	  public void setTimeout(int timeout) {
		    this.setConnectTimeout(timeout);
		    this.setSocketTimeout(timeout);
		  }
	  public void setConnectTimeout(int timeout) {
		    connectTimeout_ = timeout;
		  }
	  public void setSocketTimeout(int timeout) {
		    socketTimeout_ = timeout;
		    try {
		      socket_.setSoTimeout(timeout);
		    } catch (SocketException sx) {
		      LOGGER.warn("Could not set socket timeout.", sx);
		    }
		  }
	  public Socket getSocket() {
		    if (socket_ == null) {
		      initSocket();
		    }
		    return socket_;
		  }
	  
	  public boolean isOpen() {
		    if (socket_ == null) {
		      return false;
		    }
		    return socket_.isConnected();
		  }
	  
	  public void open() throws TTransportException {
		    if (isOpen()) {
		      throw new TTransportException(TTransportException.ALREADY_OPEN, "Socket already connected.");
		    }

		    if (host_ == null || host_.length() == 0) {
		      throw new TTransportException(TTransportException.NOT_OPEN, "Cannot open null host.");
		    }
		    if (port_ <= 0 || port_ > 65535) {
		      throw new TTransportException(TTransportException.NOT_OPEN, "Invalid port " + port_);
		    }

		    if (socket_ == null) {
		      initSocket();
		    }

		    try {
		      socket_.connect(new InetSocketAddress(host_, port_), connectTimeout_);
		      inputStream_ = new BufferedInputStream(socket_.getInputStream(), 1024);
		      outputStream_ = new BufferedOutputStream(socket_.getOutputStream(), 1024);
		    } catch (IOException iox) {
		      close();
		      throw new TTransportException(TTransportException.NOT_OPEN, iox);
		    }
		  }
	  
	  public void close() {
		    // Close the underlying streams
		    super.close();

		    // Close the socket
		    if (socket_ != null) {
		      try {
		        socket_.close();
		      } catch (IOException iox) {
		        LOGGER.warn("Could not close socket.", iox);
		      }
		      socket_ = null;
		    }
		  }
}
 