
package com.nio.reactor.client; 

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月25日 上午10:14:47 

 * 类说明 

 */

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
 
/**
 * 
 * @ClassName:     AutoClearGenericObjectPool.java
 * @Description:   连接池
 * 
 * @author          cwt
 * @version         V1.0  
 * @Date           2017年5月25日 上午10:15:05
 */
public class AutoClearGenericObjectPool<T> extends GenericObjectPool<T> {
 
    public AutoClearGenericObjectPool(PooledObjectFactory<T> factory) {
        super(factory);
    }
 
    public AutoClearGenericObjectPool(PooledObjectFactory<T> factory, GenericObjectPoolConfig config) {
        super(factory, config);
    }
 
    @Override
    public void returnObject(T obj) {
        super.returnObject(obj);
        //空闲数>=激活数时，清理掉空闲连接
        if (getNumIdle() > 0 && (getNumIdle() >= getNumActive())) {
            //clear();
        }
    }
 
}
 