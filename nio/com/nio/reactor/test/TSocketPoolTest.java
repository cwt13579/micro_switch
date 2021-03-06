
package com.nio.reactor.test; 

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.nio.reactor.client.AutoClearGenericObjectPool;
import com.nio.reactor.client.ConnectionFactory;
import com.nio.reactor.client.TSocket;

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月25日 上午10:20:41 

 * 类说明 

 */

public class TSocketPoolTest {
	public static void main(String[] args) throws Exception {
		 
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMinIdle(1);
        poolConfig.setTestOnBorrow(true);
 
        ObjectPool<TSocket> pool = new AutoClearGenericObjectPool<>(
                new ConnectionFactory("127.0.0.1", 8081), poolConfig);
 
        List<TSocket> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            TSocket protocol = pool.borrowObject();
            System.out.println(protocol.toString());
            if (i % 2 == 0) {
                //10个连接中，将偶数归还
                pool.returnObject(protocol);
            } else {
                list.add(protocol);
            }
        }
 
        Random rnd = new Random();
        while (true) {
            System.out.println(String.format("active:%d,idea:%d", pool.getNumActive(), pool.getNumIdle()));
            Thread.sleep(5000);
            //每次还一个
            if (list.size() > 0) {
                int i = rnd.nextInt(list.size());
                pool.returnObject(list.get(i));
                list.remove(i);
            }
 
            //直到全部还完
            if (pool.getNumActive() <= 0) {
                break;
            }
        }
 
        System.out.println("------------------------");
 
 
        list.clear();
        //连接池为空，测试是否能重新创建新连接
        for (int i = 1; i <= 10; i++) {
            TSocket protocol = pool.borrowObject();
            System.out.println(protocol.toString());
            if (i % 2 == 0) {
                pool.returnObject(protocol);
            } else {
                list.add(protocol);
            }
        }
 
        while (true) {
            System.out.println(String.format("active:%d,idea:%d", pool.getNumActive(), pool.getNumIdle()));
            Thread.sleep(5000);
            if (list.size() > 0) {
                int i = rnd.nextInt(list.size());
                pool.returnObject(list.get(i));
                list.remove(i);
            }
 
            if (pool.getNumActive() <= 0) {
                pool.close();
                break;
            }
        }
 
    }
}
 