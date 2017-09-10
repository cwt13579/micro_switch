package com.bell.microswitch.ctl.base;


public interface IEntryFactory<T> {
  T getEntry(String var1) throws Exception;

  void create() throws Exception;

  void parse() throws Exception;

  void addEntry(String var1, T var2) throws Exception;

  void destroy() throws Exception;
}
