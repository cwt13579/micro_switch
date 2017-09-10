
package com.bell.microswitch.nioserver; 

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;


/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年3月13日 下午3:01:45 

 * 类说明 

 */

public class Reader extends Thread {
	  private static final Logger logger = Logger.getLogger(Reader.class);
	  private static List pool = new LinkedList();
	  private static Notifier notifier = Notifier.getNotifier();
	  private Server server;
	  public Reader(Server server) {
		    this.server = server;
	  }
	  
	  public void run() {
		    while(true) {
		      try {
		        List var2 = pool;
		        SelectionKey key;
		        synchronized(pool) {
		          while(true) {
		            if(!pool.isEmpty()) {
		              key = (SelectionKey)pool.remove(0);
		              break;
		            }

		            pool.wait();
		          }
		        }

		        this.read(key);
		      } catch (Exception var4) {
		        ;
		      }
		    }
		  }
	  
	  public byte[] readRequest(SocketChannel sc) throws IOException {
		    int msgLength = this.server.getMsgLength();
		    ByteBuffer msglengthBuffer = ByteBuffer.allocate(msgLength);
		    sc.read(msglengthBuffer);
		    int length = NumberUtils.toInt(new String(msglengthBuffer.array()));
		    if(this.server.isIshead()) {
		      length -= msgLength;
		    }

		    ByteBuffer[] returnBuffer = new ByteBuffer[length];

		    int off;
		    for(off = 0; off < length; ++off) {
		      returnBuffer[off] = ByteBuffer.allocate(1);
		      returnBuffer[off].clear();
		    }

		    int ret;
		    for(off = 0; off < length; off += ret) {
		      ret = (int)sc.read(returnBuffer, off, length - off);
		      if(off == -1) {
		        break;
		      }
		    }

		    byte[] var10 = new byte[length];
		    byte[] returnByte = new byte[length];

		    for(int n = 0; n < length; ++n) {
		      var10 = returnBuffer[n].array();
		      System.arraycopy(var10, 0, returnByte, n, 1);
		    }

		    return returnByte;
		  }
	  
	  public void read(SelectionKey key) {
		    Request request = null;

		    try {
		      SocketChannel e = (SocketChannel)key.channel();
		      byte[] clientData = this.readRequest(e);
		      request = (Request)key.attachment();
		      request.setDataInput(clientData);
		      notifier.fireOnRead(request);
		      Server.processWriteRequest(key);
		    } catch (Exception var5) {
		    	 notifier.fireOnError("Error occured in Reader: " + var5.getMessage());
		    }

	  }
	  
	  public static void processRequest(SelectionKey key) {
		    List var1 = pool;
		    synchronized(pool) {
		      pool.add(pool.size(), key);
		      pool.notifyAll();
		    }
	  }
}
 