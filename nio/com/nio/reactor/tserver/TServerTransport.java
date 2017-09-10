
package com.nio.reactor.tserver; 

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月24日 下午6:00:50 

 * 类说明 

 */

import java.io.Closeable;
import java.net.InetSocketAddress;

import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.nio.reactor.client.TSocket;

/**
 * Server transport. Object which provides client transports.
 *
 */
public abstract class TServerTransport implements Closeable {

  public static abstract class AbstractServerTransportArgs<T extends AbstractServerTransportArgs<T>> {
    int backlog = 0; // A value of 0 means the default value will be used (currently set at 50)
    int clientTimeout = 0;
    InetSocketAddress bindAddr;

    public T backlog(int backlog) {
      this.backlog = backlog;
      return (T) this;
    }

    public T clientTimeout(int clientTimeout) {
      this.clientTimeout = clientTimeout;
      return (T) this;
    }

    public T port(int port) {
      this.bindAddr = new InetSocketAddress(port);
      return (T) this;
    }

    public T bindAddr(InetSocketAddress bindAddr) {
      this.bindAddr = bindAddr;
      return (T) this;
    }
  }

  public abstract void listen() throws TTransportException;

  public final TSocket accept() throws TTransportException {
	  TSocket transport = acceptImpl();
    if (transport == null) {
      throw new TTransportException("accept() may not return NULL");
    }
    return transport;
  }

  public abstract void close();

  protected abstract TSocket acceptImpl() throws TTransportException;

  public void interrupt() {}

}

 