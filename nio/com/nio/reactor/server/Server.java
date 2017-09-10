package com.nio.reactor.server; 

import java.io.IOException;
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

import com.nio.reactor.server.handler.ServerHandler;


/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年5月18日 下午3:42:30 

 * 服务类

 */

public class Server extends Thread {
	private static final Logger logger = Logger.getLogger(Server.class);
	private String ip;
	private int port;
	private int threadPoolSize;
	private int timeOut;
	
	protected Notifier notifier;
	
	private static Selector     selector;
	private ServerSocketChannel sschannel;
	private InetSocketAddress   inetSocketAddress;
	
	private static List<SelectionKey> wpool = new LinkedList<SelectionKey>();
	static {
		 Notifier notifier = Notifier.getNotifier();
		 notifier.addListener(new ServerHandler());
	}
	
	public Server(String ip,int port,int threadPoolSize,int timeOut) throws IOException {
		this.ip = ip;
		this.port = port;
		this.threadPoolSize = threadPoolSize;
		this.timeOut = timeOut;
		
		this.notifier = Notifier.getNotifier();
		
		for(int i = 0 ;i < this.threadPoolSize; i++) {
			Reader r = new Reader();
			Writer w = new Writer();
			r.start();
			w.start();
		}
		
		selector = Selector.open();
		sschannel = ServerSocketChannel.open();
		this.sschannel.configureBlocking(false);
		
		if(this.ip.equals("*")) {
			inetSocketAddress = new InetSocketAddress(port);
		} else {
			//inetSocketAddress = new InetSocketAddress(ip,port);
			InetAddress var11 = InetAddress.getByName(ip);
			this.inetSocketAddress = new InetSocketAddress(var11, port);
		}
		
		ServerSocket ss = sschannel.socket();
		ss.setSoTimeout(this.timeOut * 1000);
		ss.bind(inetSocketAddress);
		
		this.sschannel.register(selector, SelectionKey.OP_ACCEPT);
		
		
	}
	
	public void run() {
		logger.info("Server started ...");
		logger.info("Server listening on port: " + this.port);
		while(true) {
			try {
				int num = selector.select();
				if(num <= 0 ) {
					this.addRegister();
				} else {
					Set<SelectionKey> keys = selector.selectedKeys();
					Iterator<SelectionKey> iterators = keys.iterator();
					
					while(iterators.hasNext()) {
						SelectionKey key = iterators.next();
						iterators.remove();
						if(key.isAcceptable()) {
							ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
							this.notifier.fireOnAccept();
							
							SocketChannel sc = ssc.accept();	
							sc.configureBlocking(false);
							
							Request request = new Request(sc);
							this.notifier.fireOnAccepted(request);
							
							sc.register(selector, SelectionKey.OP_READ,request);
						} else if(key.isReadable()) {
							Reader.processRequest(key);
							key.cancel();
						} else if(key.isWritable()) {
							Writer.processRequest(key);
							key.cancel();
						}
					}
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				this.notifier.fireOnError("Error occured in Server: " + e.getMessage());
			}
		}
	}
	
	private synchronized void addRegister() {
		SocketChannel schannel = null;
		SelectionKey key = null;
		try {
			while(!wpool.isEmpty()) {
		        key = wpool.remove(0);
			    schannel = (SocketChannel) key.channel();
				schannel.register(selector, SelectionKey.OP_WRITE, key.attachment());
				
			}
		} catch(Exception e) {
			try {
				schannel.finishConnect();
				schannel.close();
		        schannel.socket().close();
		        this.notifier.fireOnClosed((Request)key.attachment());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public static synchronized void processWriteRequest(SelectionKey key) {
		wpool.add(key);
		//wpool.notifyAll();
		selector.wakeup();
	}
}
 