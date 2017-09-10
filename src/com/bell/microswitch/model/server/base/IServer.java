package com.bell.microswitch.model.server.base;

import org.dom4j.Element;

import com.bell.microswitch.common.Context;

public interface IServer {
  String getProperty(String var1) throws Exception;

  void doProcess(Context var1) throws Exception;

  void parse(Element var1) throws Exception;

//  IComplexBuffer getIn(String var1) throws Exception;
//
//  IComplexBuffer getOut(String var1) throws Exception;
}
