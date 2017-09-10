
package com.bell.microswitch.nioserver; 

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年3月13日 下午3:01:52 

 * 类说明 

 */

public class Writer extends Thread  {
	 private static List pool = new LinkedList();
	 private static Notifier notifier = Notifier.getNotifier();
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

		        this.write(key);
		      } catch (Exception var4) {
		        ;
		      }
		    }
		  }

		  public void write(SelectionKey key) {
		    try {
		      SocketChannel e = (SocketChannel)key.channel();
		      Response response = new Response(e);
		      notifier.fireOnWrite((Request)key.attachment(), response);
		      e.finishConnect();
		      e.socket().close();
		      e.close();
		      notifier.fireOnClosed((Request)key.attachment());
		    } catch (Exception var4) {
		      notifier.fireOnError("Error occured in Writer: " + var4.getMessage());
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
 