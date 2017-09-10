
package com.nio.reactor.client; 

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/** 
 * @author 作者姓名 cwt
 * @version 创建时间：2017年5月24日 下午4:07:48 
 * 连接工厂
 */

public class ConnectionFactory extends BasePooledObjectFactory<TSocket>{
	private String host;
    private int port;
	public ConnectionFactory(String host,int port) {
		this.host = host;
		this.port = port;
	}
	@Override
	public TSocket create() throws Exception {
		 TSocket transport = new TSocket(host, port, 10000); //建立TSocket，根据具体情况可以修改
	     transport.open();
	     System.out.println("##create-"+transport);
	     return transport;
	}

	@Override
	public PooledObject<TSocket> wrap(TSocket obj) {
		return new DefaultPooledObject<TSocket>(obj);
	}
	 @Override
	    public boolean validateObject(org.apache.commons.pool2.PooledObject<TSocket> p){  //校验对象有效性
	        TSocket transport = p.getObject();
	        return transport.isOpen();
	    }
	 @Override
	    public void destroyObject(PooledObject<TSocket> p) throws Exception { //销毁对象，关闭链接
	        if (p.getObject().isOpen()) {
	            p.getObject().close();
	        }
	    }
	 
	 /**  对象激活(borrowObject时触发）
     *
     * @param pooledObject
     * @throws TTransportException
     */
    @Override
    public void activateObject(PooledObject<TSocket> p) throws Exception {
        if (!p.getObject().isOpen()) {
            p.getObject().open();
        }
    }
    
    /**
     * 对象钝化(即：从激活状态转入非激活状态，returnObject时触发）
     *
     * @param pooledObject
     * @throws TTransportException
     */
    @Override
    public void passivateObject(PooledObject<TSocket> p) throws Exception {
        if (p.getObject().isOpen()) {
            p.getObject().flush();
            p.getObject().close();
        }
    }	
}
 