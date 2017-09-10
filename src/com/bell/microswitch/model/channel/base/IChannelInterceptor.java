package com.bell.microswitch.model.channel.base;

import com.bell.microswitch.common.Context;
import com.bell.microswitch.model.base.IEntry;

public interface IChannelInterceptor extends IEntry<IChannelInterceptor> {
  void doPreRequest(Context var1) throws Exception;

  void doPostRequest(Context var1) throws Exception;

  void doPreRespone(Context var1) throws Exception;

  void doPostRespone(Context var1) throws Exception;

  void doPreException(Context var1) throws Exception;

  void doPostException(Context var1) throws Exception;
}
