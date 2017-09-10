
package com.nio.reactor.tserver; 

/** 

 * @author 作者姓名  E-mail: email地址 

 * @version 创建时间：2017年5月24日 下午6:19:06 

 * 类说明 

 */

public abstract class TServer {

	  protected TServerTransport serverTransport_;
	  protected volatile boolean stopped_ = false;
	  private boolean isServing;
	  
	  public abstract void serve();
	  public void stop() {}

	  public boolean isServing() {
	    return isServing;
	  }

	  protected void setServing(boolean serving) {
	    isServing = serving;
	  }
}
 