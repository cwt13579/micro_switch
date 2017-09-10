package com.bell.microswitch.model.server.base;

import org.dom4j.Element;

import com.bell.microswitch.common.Context;

public interface IClient {
  String getProperty(String var1) throws Exception;

  Context doProcess(Object var1) throws Exception;

  void parse(Element var1) throws Exception;
}
