
package com.nio.reactor.server.handler; 



import com.nio.reactor.server.Request;
import com.nio.reactor.server.Response;
import com.nio.reactor.server.event.ServerListener;

/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年5月18日 下午5:06:56 

 * 请求处理器

 */

public class ServerHandler implements ServerListener{

	@Override
	public void onError(String paramString) {
		System.out.println("处理错误："+paramString);
		
	}

	@Override
	public void onAccept() throws Exception {
		System.out.println("#onAccept");
	}

	@Override
	public void onAccepted(Request paramRequest) throws Exception {
		System.out.println("#onAccepted");
	}

	@Override
	public void onRead(Request paramRequest) throws Exception {
		System.out.println("#onRead");
	}

	@Override
	public void onWrite(Request request, Response response)throws Exception {
		System.out.println("#onWrite");
		response.send("hello world".getBytes());
	}

	@Override
	public void onClosed(Request paramRequest) throws Exception {
		System.out.println("#onClosed");
	}

}
 