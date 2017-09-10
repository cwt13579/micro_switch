
package com.nio.reactor.tserver; 

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.apache.thrift.transport.TTransportException;

import com.nio.reactor.client.TSocket;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月24日 下午6:17:11 

 * 类说明 

 */

public class TSimpleServer extends TServer{

	private static final Logger LOGGER = Logger.getLogger(TSimpleServer.class.getName());
	public void stop() {
	    stopped_ = true;
	    serverTransport_.interrupt();
	 }

	public TSimpleServer(int port) throws TTransportException {
		serverTransport_ = new TServerSocket(port);
	}
	@Override
	public void serve() {
		 try {
		      serverTransport_.listen();
		    } catch (TTransportException ttx) {
		      LOGGER.error("Error occurred during listening.", ttx);
		      return;
		    }
		 setServing(true);
		 while (!stopped_) {
			 TSocket client = null;
			 try {
				 client = serverTransport_.accept();
				 if (client != null) {
					 
	             	byte[] buf = new byte[1024];
	             	client.read(buf, 0, 20);
	             		
					 ByteBuffer buffer = ByteBuffer.wrap("hello body1".getBytes());
					 client.write(buffer.array(), 0, buffer.array().length);
					 client.flush();
					 
             		System.out.println(new String(buf));
				 }
			 }  catch (Exception x) {
				 LOGGER.error("Error occurred during processing of message.", x);
			 } finally {
				 //client.close();
			 }
		 }
		 
		   setServing(false);
	}
}
 