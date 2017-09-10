
package com.nio.reactor.test; 

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月18日 下午5:26:44 

 * 类说明 

 */

public class ClientTest {

	public static void main(String[] args) throws IOException {

		 SocketChannel sc = SocketChannel.open();
		 sc.connect(new InetSocketAddress("localhost",8081));
		 ByteBuffer buffer = ByteBuffer.wrap("hello body".getBytes());
		 
	     sc.write(buffer);

	     ByteBuffer msglengthBuffer = ByteBuffer.allocate(1024);
	     sc.read(msglengthBuffer);
		 
		 System.out.println(new String(msglengthBuffer.array()));
	}

}
 