
package com.nio.reactor.server.event; 

import com.nio.reactor.server.Request;
import com.nio.reactor.server.Response;



/** 

 * @author 作者姓名 cwt

 * @version 创建时间：2017年3月13日 下午3:00:32 

 * 处理器

 */

public abstract interface ServerListener {
	
	public abstract void onError(String paramString);

	public abstract void onAccept() throws Exception;

	public abstract void onAccepted(Request paramRequest) throws Exception;

	public abstract void onRead(Request paramRequest) throws Exception;

	public abstract void onWrite(Request paramRequest, Response paramResponse) throws Exception;

	public abstract void onClosed(Request paramRequest) throws Exception;
}
 