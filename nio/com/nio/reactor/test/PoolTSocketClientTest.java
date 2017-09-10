
package com.nio.reactor.test; 

import java.nio.ByteBuffer;

import com.nio.reactor.client.ConnectionManager;
import com.nio.reactor.client.TSocket;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月24日 下午5:08:34 

 * 类说明 

 */

public class PoolTSocketClientTest {

	public static void main(String args[]) {
		for (int i = 0; i < 3 ; i++) {
			try {
				
                new Thread(new TaskInfo(i)).start();

		} catch (Exception e) {
            e.printStackTrace();
        }
	}
}
	
	
}

class TaskInfo implements Runnable {
    private int name;
	public TaskInfo(int name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		TSocket tSocket = null;
    	try {
			tSocket = ConnectionManager.getTSocketConnetion();
			System.out.println("good"+tSocket.getSocket().getPort()+","+tSocket.getSocket().getLocalPort());
    		ByteBuffer buffer = ByteBuffer.wrap("hello body".getBytes());
    		tSocket.write(buffer.array(), 0, buffer.array().length);
    		tSocket.flush();
    		byte[] buf = new byte[1024];
    		tSocket.read(buf, 0, 20);
    		
    		System.out.println(new String(buf));
            //Thread.sleep(200);
    	} catch (Exception e) {
            e.printStackTrace();
            System.out.println("error socket:"+tSocket);
        }finally{
        	if(tSocket != null ) {
        		 //System.out.println("excute return +"+tSocket);
        		 ConnectionManager.returnTSocketConnetion(tSocket);
        	}
           
        }
		
	}
	
}

 