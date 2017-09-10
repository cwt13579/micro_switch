
package com.nio.reactor.test; 

import java.io.IOException;
import java.nio.channels.SelectionKey;

import com.nio.reactor.server.Server;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月18日 下午5:23:30 

 * 类说明 

 */

public class ServerStartTest {

	public static void main(String args[]) throws IOException {
//		System.out.println(SelectionKey.OP_ACCEPT);//16
//		System.out.println(SelectionKey.OP_CONNECT);//8
//		System.out.println(SelectionKey.OP_READ);//1
//		System.out.println(SelectionKey.OP_WRITE);//4
		Server server = new Server("localhost",8081,1,5);
		server.start();
	}
}
 