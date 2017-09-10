package com.bell.microswitch.model.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.bell.microswitch.common.Context;
import com.bell.microswitch.ctl.utils.VelocityHelper;
import com.bell.microswitch.ctl.utils.XmlTransformer;
import com.bell.microswitch.model.base._Entry;
import com.bell.microswitch.model.server.base.IServer;
import com.google.gson.Gson;

public class CommonServer extends _Entry<IServer> implements IServer {
  private Map<String, CommonServer.Method> methodMap = new HashMap();

  public CommonServer() {
  }

  public IServer getIntance() {
    return this;
  }

  public Map<String, CommonServer.Method> getMethodMap() {
    return this.methodMap;
  }

  public void setMethodMap(Map<String, CommonServer.Method> methodMap) {
    this.methodMap = methodMap;
  }

  public void addMethod(String id, CommonServer.Method method) {
    this.methodMap.put(id, method);
  }

  public CommonServer.Method getMethod(String id) {
    return (CommonServer.Method)this.methodMap.get(id);
  }

//  public IComplexBuffer getIn(String id) throws Exception {
//    CommonServer.MethodParam inParam = this.getMethod(id).getInParam();
//    return (IComplexBuffer)((IComplexBuffer)BufferFactory.getInstance().getEntry(inParam.getType())).cloneN();
//  }
//
//  public IComplexBuffer getOut(String id) throws Exception {
//    CommonServer.MethodParam outParam = this.getMethod(id).getOutParam();
//    return (IComplexBuffer)((IComplexBuffer)BufferFactory.getInstance().getEntry(outParam.getType())).cloneN();
//  }

  public void doProcess(Context ctx) throws Exception {
    try {
      String e = ctx.getMethodId();
      String impClass = this.getAttrValue("impClass");
      String inParam = this.getMethod(e).getInParam().getAttrValue("impClass");
      String outParam = this.getMethod(e).getOutParam().getAttrValue("impClass");
      Object inParamClass = Class.forName(inParam).newInstance();
      Object outParamClass = Class.forName(outParam).newInstance();
      Class serverClass = Class.forName(impClass);
      //XPathFormatUtils.bufferToBean((Buffer)ctx.getInBuffer(), inParamClass);
      System.out.println(ctx.getInMsgTemplate());
      System.out.println(ctx.getInMsg());
      String result = XmlTransformer.format(ctx.getInMsgTemplate(), ctx.getInMsg());
      Gson gson = new Gson();
      System.out.println(result);
      inParamClass = gson.fromJson(result, inParamClass.getClass());//将json字符串 转换为bean对象
      Object outObject = serverClass.getMethod(e, new Class[]{inParamClass.getClass()}).invoke(serverClass.newInstance(), new Object[]{inParamClass});
      //XPathFormatUtils.beanToBuffer(outObject, (Buffer)ctx.getOutBuffer(), (JXPathContext)null);
      System.out.println(ctx.getOutMsgTemplate());
      System.out.println(outObject);
      String rspMsg=VelocityHelper.parseTemplate(ctx.getOutMsgTemplate(), outObject);
      ctx.setOutMsg(rspMsg);
      ctx.setOutMsgByte(rspMsg.getBytes());
    } catch (Exception var10) {
      throw var10;
    }
  }

  public void parse(Element ele) throws Exception {
    Iterator it = ele.attributeIterator();

    while(it.hasNext()) {
      Attribute methodIt = (Attribute)it.next();
      this.setAttrValue(methodIt.getName(), methodIt.getValue());
    }

    CommonServer.Method m;
    for(Iterator methodIt1 = ele.selectNodes("Methods/Method").iterator(); methodIt1.hasNext(); this.addMethod(m.getId(), m)) {
      Element mele = (Element)methodIt1.next();
      m = new CommonServer.Method();
      Iterator ait = mele.attributeIterator();

      while(ait.hasNext()) {
        Attribute paramIt = (Attribute)ait.next();
        m.setAttrValue(paramIt.getName(), paramIt.getValue());
      }

      Iterator paramIt1 = mele.selectNodes("Param[@id=\'IN\']").iterator();
      Element paramEle;
      CommonServer.MethodParam param;
      Iterator paIt;
      Attribute at;
      if(paramIt1.hasNext()) {
        paramEle = (Element)paramIt1.next();
        param = new CommonServer.MethodParam();
        paIt = paramEle.attributeIterator();

        while(paIt.hasNext()) {
          at = (Attribute)paIt.next();
          param.setAttrValue(at.getName(), at.getValue());
        }

        m.setInParam(param);
      }

      paramIt1 = mele.selectNodes("Param[@id=\'OUT\']").iterator();
      if(paramIt1.hasNext()) {
        paramEle = (Element)paramIt1.next();
        param = new CommonServer.MethodParam();
        paIt = paramEle.attributeIterator();

        while(paIt.hasNext()) {
          at = (Attribute)paIt.next();
          param.setAttrValue(at.getName(), at.getValue());
        }

        m.setOutParam(param);
      }
    }

  }

  public String getProperty(String name) throws Exception {
    return this.getAttrValue(name);
  }

  public Object clone() throws CloneNotSupportedException {
    return null;
  }

  class Method extends _Entry<CommonServer.Method> {
    private CommonServer.MethodParam inParam;
    private CommonServer.MethodParam outParam;

    Method() {
    }

    public CommonServer.MethodParam getInParam() {
      return this.inParam;
    }

    public void setInParam(CommonServer.MethodParam inParam) {
      this.inParam = inParam;
    }

    public CommonServer.MethodParam getOutParam() {
      return this.outParam;
    }

    public void setOutParam(CommonServer.MethodParam outParam) {
      this.outParam = outParam;
    }

    public CommonServer.Method getIntance() {
      return null;
    }

    public Object clone() throws CloneNotSupportedException {
      return null;
    }
  }

  class MethodParam extends _Entry<CommonServer.MethodParam> {
    MethodParam() {
    }

    public CommonServer.MethodParam getIntance() {
      return null;
    }

    public Object clone() throws CloneNotSupportedException {
      return null;
    }
  }
}
