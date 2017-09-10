package com.bell.microswitch.model.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public abstract class _Entry<T> implements IEntry<T> {
  private static final long serialVersionUID = -7122316995398921079L;
  private Map<String, String> attrs = new HashMap();

  public _Entry() {
  }

  public String getId() {
    return this.getAttrValue("id");
  }

  public String getType() {
    return this.getAttrValue("type");
  }

  public String getDesc() {
    return this.getAttrValue("desc");
  }

  public String getName() {
    return this.getAttrValue("name");
  }

  public void setId(String id) {
    this.setAttrValue("id", id);
  }

  public void setType(String type) {
    this.setAttrValue("type", type);
  }

  public void setDesc(String desc) {
    this.setAttrValue("desc", desc);
  }

  public void setName(String name) {
    this.setAttrValue("name", name);
  }

  public void setAttrValue(String name, String value) {
    this.attrs.put(name, value);
  }

  public String getAttrValue(String name) {
    return (String)this.attrs.get(name);
  }

  public String getAttrValue(String name, String defaultValue) {
    String val = this.getAttrValue(name);
    return StringUtils.isEmpty(val)?defaultValue:val;
  }

  public String[] getAttrKeys() {
    return (String[])this.attrs.keySet().toArray();
  }

  public abstract Object clone() throws CloneNotSupportedException;
}
