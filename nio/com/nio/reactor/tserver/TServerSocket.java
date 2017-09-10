
package com.nio.reactor.tserver; 

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import org.apache.log4j.Logger;
import org.apache.thrift.transport.TTransportException;

import com.nio.reactor.client.TSocket;
import com.nio.reactor.tserver.TServerTransport.AbstractServerTransportArgs;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月24日 下午6:02:05 

 * 类说明 

 */

public class TServerSocket extends TServerTransport{
	private static final Logger LOGGER = Logger.getLogger(TServerSocket.class.getName());
	private ServerSocket serverSocket_ = null;
	private int clientTimeout_ = 0;
	
	public static class ServerSocketTransportArgs extends AbstractServerTransportArgs<ServerSocketTransportArgs> {
	    ServerSocket serverSocket;

	    public ServerSocketTransportArgs serverSocket(ServerSocket serverSocket) {
	      this.serverSocket = serverSocket;
	      return this;
	    }
	  }
	
	 public TServerSocket(ServerSocket serverSocket) throws TTransportException {
		    this(serverSocket, 0);
	 }
	  public TServerSocket(ServerSocket serverSocket, int clientTimeout) throws TTransportException {
		    this(new ServerSocketTransportArgs().serverSocket(serverSocket).clientTimeout(clientTimeout));
		  }
	  public TServerSocket(int port) throws TTransportException {
		    this(port, 0);
	  }
	  public TServerSocket(int port, int clientTimeout) throws TTransportException {
		    this(new InetSocketAddress(port), clientTimeout);
	 }
	  public TServerSocket(InetSocketAddress bindAddr) throws TTransportException {
		    this(bindAddr, 0);
	 }
	  
	  public TServerSocket(InetSocketAddress bindAddr, int clientTimeout) throws TTransportException {
		    this(new ServerSocketTransportArgs().bindAddr(bindAddr).clientTimeout(clientTimeout));
		  }
	  
	  public TServerSocket(ServerSocketTransportArgs args) throws TTransportException {
		    clientTimeout_ = args.clientTimeout;
		    if (args.serverSocket != null) {
		      this.serverSocket_ = args.serverSocket;
		      return;
		    }
		    try {
		      // Make server socket
		      serverSocket_ = new ServerSocket();
		      // Prevent 2MSL delay problem on server restarts
		      serverSocket_.setReuseAddress(true);
		      // Bind to listening port
		      serverSocket_.bind(args.bindAddr, args.backlog);
		    } catch (IOException ioe) {
		      close();
		      throw new TTransportException("Could not create ServerSocket on address " + args.bindAddr.toString() + ".", ioe);
		    }
		  }

	  public void listen() throws TTransportException {
		    // Make sure not to block on accept
		    if (serverSocket_ != null) {
		      try {
		        serverSocket_.setSoTimeout(0);
		      } catch (SocketException sx) {
		        LOGGER.error("Could not set socket timeout.", sx);
		      }
		    }
		  }
	 
	  protected TSocket acceptImpl() throws TTransportException {
		    if (serverSocket_ == null) {
		      throw new TTransportException(TTransportException.NOT_OPEN, "No underlying server socket.");
		    }
		    try {
		      Socket result = serverSocket_.accept();
		      TSocket result2 = new TSocket(result);
		      result2.setTimeout(clientTimeout_);
		      return result2;
		    } catch (IOException iox) {
		      throw new TTransportException(iox);
		    }
		  }
	  
	  public void close() {
		    if (serverSocket_ != null) {
		      try {
		        serverSocket_.close();
		      } catch (IOException iox) {
		        LOGGER.warn("Could not close server socket.", iox);
		      }
		      serverSocket_ = null;
		    }
		  }

		  public void interrupt() {
		    // The thread-safeness of this is dubious, but Java documentation suggests
		    // that it is safe to do this from a different thread context
		    close();
		  }

		  public ServerSocket getServerSocket() {
		    return serverSocket_;
		  }
}
 