
package com.bell.microswitch.nioserver; 

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bell.microswitch.model.channel.SocketServerChannel;
import com.bell.microswitch.nioserver.handler.ServerHandler;

/** 

 * @author 作者姓名 cwt

 * @version 创建时间：2017年3月13日 下午2:59:42 

 * 类说明 

 */

public class Server implements Runnable {
	private static final Logger logger = Logger.getLogger(Server.class);
	private static List wpool = new LinkedList();
	private static Selector selector;
	private ServerSocketChannel sschannel;
	private InetSocketAddress address;
	protected Notifier notifier;
	private int port;
	private int msgLength;
	private boolean ishead;
	private SocketServerChannel sc;
	
	static {
		 Notifier notifier = Notifier.getNotifier();
		 notifier.addListener(new ServerHandler());
	}
	 
	public Server(String ip, int port, int threadpoolsize, int timeout, int msgLength, boolean ishead, SocketServerChannel sc) throws Exception{
		 this.port = port;
		 this.sc = sc;
		 this.msgLength = msgLength;
		 this.ishead = ishead;
		 this.notifier = Notifier.getNotifier();
		    
		for(int ss = 0; ss < threadpoolsize; ++ss) {
			 Reader r = new Reader(this);
			 Writer w = new Writer();
			 r.start();
		     w.start();
		}
		
		selector = Selector.open();
		this.sschannel = ServerSocketChannel.open();
		this.sschannel.configureBlocking(false);
		if("*".equals(ip)) {
		    this.address = new InetSocketAddress(port);
	     } else {
		    InetAddress var11 = InetAddress.getByName(ip);
		    this.address = new InetSocketAddress(var11, port);
		 }

		 ServerSocket var12 = this.sschannel.socket();
		 var12.setSoTimeout(timeout * 1000);
		 var12.bind(this.address);
		 this.sschannel.register(selector, 16);
	}
 
	 public void run() {
		    logger.info("Server started ...");
		    logger.info("Server listening on port: " + this.port);

		    while(true) {
		      while(true) {
		        try {
		          while(true) {
		            boolean e = false;
		            int e1 = selector.select();
		            if(e1 <= 0) {
		              this.addRegister();
		            } else {
		              Set selectedKeys = selector.selectedKeys();
		              Iterator it = selectedKeys.iterator();

		              while(it.hasNext()) {
		                SelectionKey key = (SelectionKey)it.next();
		                it.remove();
		                if((key.readyOps() & 16) == 16) {
		                  ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
		                  this.notifier.fireOnAccept();
		                  SocketChannel sc = ssc.accept();
		                  sc.configureBlocking(false);
		                  Request request = new Request(this.sc, sc);
		                  this.notifier.fireOnAccepted(request);
		                  sc.register(selector, 1, request);
		                } else if((key.readyOps() & 1) == 1) {
		                  Reader.processRequest(key);
		                  key.cancel();
		                } else if((key.readyOps() & 4) == 4) {
		                  Writer.processRequest(key);
		                  key.cancel();
		                }
		              }
		            }
		          }
		        } catch (Exception var8) {
		          this.notifier.fireOnError("Error occured in Server: " + var8.getMessage());
		        }
		      }
		    }
		  }
	
	 private void addRegister() {
		    List var1 = wpool;
		    synchronized(wpool) {
		      while(!wpool.isEmpty()) {
		        SelectionKey key = (SelectionKey)wpool.remove(0);
		        SocketChannel schannel = (SocketChannel)key.channel();

		        try {
		          schannel.register(selector, 4, key.attachment());
		        } catch (Exception var7) {
		          try {
		            schannel.finishConnect();
		            schannel.close();
		            schannel.socket().close();
		            this.notifier.fireOnClosed((Request)key.attachment());
		          } catch (Exception var6) {
		            ;
		          }

		          this.notifier.fireOnError("Error occured in addRegister: " + var7.getMessage());
		        }
		      }

		    }
		  }
	 
	 public static void processWriteRequest(SelectionKey key) {
		    List var1 = wpool;
		    synchronized(wpool) {
		      wpool.add(wpool.size(), key);
		      wpool.notifyAll();
		    }

		   selector.wakeup();
	}
	public int getMsgLength() {
		return msgLength;
	}
	public void setMsgLength(int msgLength) {
		this.msgLength = msgLength;
	}
	public boolean isIshead() {
		return ishead;
	}
	public void setIshead(boolean ishead) {
		this.ishead = ishead;
	}	 
}
 