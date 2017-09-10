package com.bell.microswitch.model.channel.base;

import com.bell.microswitch.common.Context;

public interface IChannel {

  void process(Context var1) throws Exception;
  
  void init() throws Exception;

  String getProperty(String var1) throws Exception;
}
