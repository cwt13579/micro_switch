
package com.nio.reactor.server; 

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;



/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年5月18日 下午3:45:39 

 * 写处理

 */

public class Writer extends Thread {
	
   private static List<SelectionKey> pool = new LinkedList<SelectionKey>();
   private static Notifier notifier = Notifier.getNotifier();
	public void run() {
		while(true) {
			try {
				SelectionKey key = null;
                synchronized(pool) {
					if(!pool.isEmpty()) {
						key = pool.remove(0);
						this.write(key);
					}
					pool.wait();
				}
			}catch(Exception e) {
				
			}
		}
	}
	
	public void write(SelectionKey key) {
		try {
			SocketChannel sc = (SocketChannel) key.channel();
			Response rsp = new Response(sc);
			notifier.fireOnWrite((Request)key.attachment(), rsp);
			sc.finishConnect();
			sc.socket().close();
			sc.close();
		}catch (Exception var4) {
			var4.printStackTrace();
		}
		
	}
	public static void processRequest(SelectionKey key) {
		synchronized(pool) {
			pool.add(key);
			pool.notifyAll();
		}
	}
}
 