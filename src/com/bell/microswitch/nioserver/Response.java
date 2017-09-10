
package com.bell.microswitch.nioserver; 

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年3月13日 下午3:02:11 

 * 类说明 

 */

public class Response {
	private SocketChannel sc;

	public Response(SocketChannel sc) {
		this.sc = sc;
	}

	public void send(byte[] data) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(data.length);
		buffer.put(data, 0, data.length);
		buffer.flip();
		this.sc.write(buffer);
	}
}
 