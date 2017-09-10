
package com.nio.reactor.test; 

import org.apache.thrift.transport.TTransportException;

import com.nio.reactor.tserver.TSimpleServer;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月24日 下午6:27:21 

 * 类说明 

 */

public class TServerTest {

	public static void main(String args[]) throws TTransportException {
		TSimpleServer server = new  TSimpleServer(8081);
		System.out.println("serve port 8081...");
		server.serve();
	}
}
 