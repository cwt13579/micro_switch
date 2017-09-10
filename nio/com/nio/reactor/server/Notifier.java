
package com.nio.reactor.server; 

import java.util.ArrayList;
import java.util.List;

import com.nio.reactor.server.event.ServerListener;

/** 

 * @author 作者姓名 cwt

 * @version 创建时间：2017年5月18日 下午4:03:46 

 * 通知处理器

 */

public class Notifier {

	private static Notifier instance = null;
	private static List<ServerListener> listeners = null;
	
	private Notifier() {
		listeners = new ArrayList<ServerListener>();
	}
	public static synchronized Notifier getNotifier() {
		if(instance == null) {
			instance = new Notifier();
		}

		return instance;
	}
	
	public synchronized void addListener(ServerListener listener) {
		if(!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	public void fireOnError(String paramString) {
		 for(int i = listeners.size() - 1; i >= 0; --i) {
		      ((ServerListener)listeners.get(i)).onError(paramString);
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

	public void fireOnRead(Request request) throws Exception {
		 for(int i = listeners.size() - 1; i >= 0; --i) {
		      ((ServerListener)listeners.get(i)).onRead(request);
		 }
	}

	public void fireOnWrite(Request request, Response response) throws Exception {
		 for(int i = listeners.size() - 1; i >= 0; --i) {
		      ((ServerListener)listeners.get(i)).onWrite(request, response);
		 }
	}

	public void fireOnClosed(Request request) throws Exception {
		 for(int i = listeners.size() - 1; i >= 0; --i) {
		      ((ServerListener)listeners.get(i)).onClosed(request);
		 }
	}
	
	
}
 