
package com.nio.reactor.server; 

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;





/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年5月18日 下午3:45:32 

 * 读处理

 */

public class Reader extends Thread{

	 private static List<SelectionKey> pool = new LinkedList<SelectionKey>();
	 private static Notifier notifier = Notifier.getNotifier();
	 public void run() {
		 while(true) {
			 try {
				   SelectionKey key = null;
				   synchronized(pool) {
					   while(true) {
					      if(!pool.isEmpty() ) {
						     key = pool.remove(0);
						     break;
					      }
					      pool.wait();
					   }
				   }
				   this.read(key);
			 } catch(Exception e) {			
				 
			 }
		 }
	 }
	 
	 public void read(SelectionKey key) {
		 try {
		      SocketChannel sc = (SocketChannel)key.channel();
		      ByteBuffer msglengthBuffer = ByteBuffer.allocate(1024);
			  sc.read(msglengthBuffer);
			  System.out.println(msglengthBuffer.toString());
			  Request request = (Request)key.attachment();
			  notifier.fireOnRead(request);
			  Server.processWriteRequest(key);
		 }catch (Exception var5) {
			 var5.printStackTrace();
		 }
	 }
	 public static void processRequest(SelectionKey key) {
		 synchronized(pool) {
			 pool.add(key);
			 pool.notifyAll();
		 }
	 }
}
 