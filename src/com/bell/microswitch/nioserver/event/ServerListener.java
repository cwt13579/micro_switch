
package com.bell.microswitch.nioserver.event; 

import com.bell.microswitch.nioserver.Request;
import com.bell.microswitch.nioserver.Response;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年3月13日 下午3:00:32 

 * 类说明 

 */

public abstract interface ServerListener {
	
	public abstract void onError(String paramString);

	public abstract void onAccept() throws Exception;

	public abstract void onAccepted(Request paramRequest) throws Exception;

	public abstract void onRead(Request paramRequest) throws Exception;

	public abstract void onWrite(Request paramRequest, Response paramResponse) throws Exception;

	public abstract void onClosed(Request paramRequest) throws Exception;
}
 