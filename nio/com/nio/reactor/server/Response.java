
package com.nio.reactor.server; 

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


/** 

 * @author 作者姓名   cwt
 
 * @version 创建时间：2017年5月18日 下午4:16:28 

 * 通信返回类

 */

public class Response {

	private SocketChannel sc;
	
	public Response(SocketChannel sc) {
		this.sc = sc;
	}
	
	public void send(byte[] data) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(data.length);
		buffer.put(data);
		buffer.flip();
		sc.write(buffer);
	}
}
 