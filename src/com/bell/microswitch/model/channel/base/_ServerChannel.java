package com.bell.microswitch.model.channel.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.bell.microswitch.common.Context;
import com.bell.microswitch.ctl.ServerFactory;
import com.bell.microswitch.model.base._Entry;
import com.bell.microswitch.model.server.base.IServer;

public abstract class _ServerChannel extends _Entry<IChannel> implements IChannel {
  private static final Logger logger = Logger.getLogger(_ServerChannel.class);
  private List<IChannelInterceptor> interceptorList = new LinkedList();
  private Map<String, String> endponitMap = new HashMap();

  public _ServerChannel() {
  }

  public IChannel getIntance() {
    return this;
  }

  public void process(Context ctx) throws Exception {
      String var21;
      String var23;
      try {
        if(logger.isInfoEnabled()) {
          logger.info( "Server ReqMsg[" + new String(ctx.getInMsgByte()) + "]");
        }

        for(int e = 0; e < this.getInterceptorList().size(); ++e) {
            IChannelInterceptor var22 = (IChannelInterceptor)this.getInterceptorList().get(e);
            var22.doPreRequest(ctx);
        }
        
        String var20 = this.getEndPoint(ctx.getInTxnCode());
        if(StringUtils.isEmpty(var20)) {
          throw new Exception("InTxnCode is empty");
        }

        var21 = var20.substring(0, var20.indexOf("::"));
        var23 = var20.substring(var20.indexOf("::") + 2);
        IServer var24 = (IServer)ServerFactory.getInstance().getEntry(var21);
        ctx.setServerId(var21);
        ctx.setMethodId(var23);
//      ctx.setInBuffer(var24.getIn(var23));
//      ctx.setOutBuffer(var24.getOut(var23));
      
        IChannelInterceptor interceptor2;
        int var25;
        for(var25 = 0; var25 < this.getInterceptorList().size(); ++var25) {
          interceptor2 = (IChannelInterceptor)this.getInterceptorList().get(var25);
          interceptor2.doPostRequest(ctx);
        }
        
        var24.doProcess(ctx);
        
        for(var25 = 0; var25 < this.getInterceptorList().size(); ++var25) {
            interceptor2 = (IChannelInterceptor)this.getInterceptorList().get(var25);
            interceptor2.doPreRespone(ctx);
        }
        ctx.setSuccess(true);

        for(var25 = 0; var25 < this.getInterceptorList().size(); ++var25) {
            interceptor2 = (IChannelInterceptor)this.getInterceptorList().get(var25);
            interceptor2.doPostRespone(ctx);
        }
        if(logger.isInfoEnabled()) {
          logger.info("Server RspMsg[" + new String(ctx.getOutMsgByte(), ctx.getEncoding()) + "]");
          logger.info("ServerChannel process success");
        }
      } catch (Exception var17) {
    	  ctx.setSuccess(false);

          for(int i = 0; i < this.getInterceptorList().size(); ++i) {
            IChannelInterceptor interceptor = (IChannelInterceptor)this.getInterceptorList().get(i);
            interceptor.doPreException(ctx);
          }
          
          for(int e1 = 0; e1 < this.getInterceptorList().size(); ++e1) {
              IChannelInterceptor interceptor3 = (IChannelInterceptor)this.getInterceptorList().get(e1);
              interceptor3.doPostException(ctx);
          }
    } finally {
 

    }
  }

  public String getProperty(String name) throws Exception {
    return this.getAttrValue(name);
  }

  public void addEndPoint(String key, String uri) throws Exception {
    this.endponitMap.put(key, uri);
  }

  public String getEndPoint(String key) throws Exception {
    return (String)this.endponitMap.get(key);
  }

  public List<IChannelInterceptor> getInterceptorList() {
	    return this.interceptorList;
  }

 public void addInterceptor(IChannelInterceptor interceptor) {
	    this.interceptorList.add(interceptor);
  }

public abstract void start() throws Exception;

  public abstract void stop() throws Exception;

  public abstract void restart() throws Exception;
}
