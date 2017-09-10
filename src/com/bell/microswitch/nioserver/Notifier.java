
package com.bell.microswitch.nioserver; 

import java.util.ArrayList;

import com.bell.microswitch.nioserver.event.ServerListener;

/** 

 * @author 作者姓名  cwt

 * @version 创建时间：2017年3月13日 下午3:03:04 

 * 类说明 

 */

public class Notifier {
	 private static ArrayList listeners = null;
	  private static Notifier instance = null;

	  private Notifier() {
	    listeners = new ArrayList();
	  }

	  public static synchronized Notifier getNotifier() {
	    if(instance == null) {
	      instance = new Notifier();
	      return instance;
	    } else {
	      return instance;
	    }
	  }

	  public void addListener(ServerListener l) {
	    ArrayList var2 = listeners;
	    synchronized(listeners) {
	      if(!listeners.contains(l)) {
	        listeners.add(l);
	      }

	    }
	  }

	  public void fireOnAccept() throws Exception {
	    for(int i = listeners.size() - 1; i >= 0; --i) {
	      ((ServerListener)listeners.get(i)).onAccept();
	    }

	  }

	  public void fireOnAccepted(Request request) throws Exception {
	    for(int i = listeners.size() - 1; i >= 0; --i) {
	      ((ServerListener)listeners.get(i)).onAccepted(request);
	    }

	  }

	  void fireOnRead(Request request) throws Exception {
	    for(int i = listeners.size() - 1; i >= 0; --i) {
	      ((ServerListener)listeners.get(i)).onRead(request);
	    }

	  }

	  void fireOnWrite(Request request, Response response) throws Exception {
	    for(int i = listeners.size() - 1; i >= 0; --i) {
	      ((ServerListener)listeners.get(i)).onWrite(request, response);
	    }

	  }

	  public void fireOnClosed(Request request) throws Exception {
	    for(int i = listeners.size() - 1; i >= 0; --i) {
	      ((ServerListener)listeners.get(i)).onClosed(request);
	    }

	  }

	  public void fireOnError(String error) {
	    for(int i = listeners.size() - 1; i >= 0; --i) {
	      ((ServerListener)listeners.get(i)).onError(error);
	    }

	  }
}
 