
package com.nio.reactor.client; 

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;

/** 

 * @author 作者姓名   cwt

 * @version 创建时间：2017年5月24日 下午5:00:51 

 * 连接操作类

 */

public class ConnectionManager {
	 private static final Logger LGR = Logger.getLogger(ConnectionManager.class.getName());
	 private static AutoClearGenericObjectPool<TSocket> pool;
	 
	 static {
	        ConnectionFactory connectionFactory = new ConnectionFactory("localhost",8081);
	        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
	        config.setMaxIdle(5);   //最大空闲数量
	        config.setMaxTotal(5);  //连接池最大数量
	        config.setMinIdle(1);    //最小空闲数量
	        config.setMaxWaitMillis(2000); //提取对象时最大等待时间，超时会抛出异常
	        config.setMinEvictableIdleTimeMillis(60000); // 最小的空闲对象驱除时间间隔，空闲超过指定的时间的对象，会被清除掉
	        config.setTimeBetweenEvictionRunsMillis(30000);//后台驱逐线程休眠时间
	        config.setNumTestsPerEvictionRun(1); //设置驱逐线程每次检测对象的数量
	        config.setTestOnBorrow(true);  //在从pool中去对象时进行有效性检查，会调用工厂中的validateObject
	        config.setTestWhileIdle(true);  //是否对空闲对象使用PoolableObjectFactory的validateObject校验，
	        //config.setTestOnReturn(true);
	        pool = new AutoClearGenericObjectPool<TSocket>(connectionFactory, config);
	    }
	 
	 public static  TSocket getTSocketConnetion(){
		 TSocket socket;
		 try {
			 System.out.println(String.format("borrowObject active:%d,idea:%d", ConnectionManager.getNumActive(), ConnectionManager.getNumIdle()));
			 socket = pool.borrowObject(5000);
			 System.out.println("#borrow="+socket);
			 return socket;
		 } catch(Exception e) {
			 e.printStackTrace();
		 }
		 return null;
	 }
	 public static void returnTSocketConnetion(TSocket tsocket){
		 System.out.println("#return="+tsocket);
         pool.returnObject(tsocket);
		 System.out.println(String.format("returnObject active:%d,idea:%d", ConnectionManager.getNumActive(), ConnectionManager.getNumIdle()));

     }
	 
	 public static int getNumIdle() {
		 return pool.getNumIdle();
	 }
	 
	 public static int getNumActive() {
		 return pool.getNumActive();
	 }
}
 