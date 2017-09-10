package com.cwttech.alert.base;

import java.util.HashMap;
import java.util.Map;

public class Context {

	protected Map<String,Object> attrs = new HashMap<String,Object>();
	protected static final String FROM ="from";
	protected static final String TO ="to";
	protected static final String MSG ="msg";
	
	public Object getFrom() {
		return attrs.get(FROM);
	}
	public void setFrom(Object from) {
		attrs.put(FROM, from);
	}
	public Object getTo() {
		return attrs.get(TO);
	}
	public void setTo(Object to) {
		attrs.put(TO, to);
	}
	public Object getMsg() {
		return attrs.get(MSG);
	}
	public void setMsg(Object msg) {
		attrs.put(MSG, msg);
	}
}
