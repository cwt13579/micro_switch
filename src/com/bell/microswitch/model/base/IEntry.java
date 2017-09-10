package com.bell.microswitch.model.base;

import java.io.Serializable;

public interface IEntry<T> extends Cloneable, Serializable {
  T getIntance();

  String getId();

  String getType();

  String getDesc();

  String getName();

  void setId(String var1);

  void setType(String var1);

  void setDesc(String var1);

  void setName(String var1);

  String toString();

  void setAttrValue(String var1, String var2);

  String getAttrValue(String var1);

  String getAttrValue(String var1, String var2);

  String[] getAttrKeys();

  Object clone() throws CloneNotSupportedException;
}
