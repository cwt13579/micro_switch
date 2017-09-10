package com.bell.microswitch.common;

import java.util.UUID;

public class Context {
  private String sessionID = UUID.randomUUID().toString();
  private boolean success = false;
  private byte[] inMsgByte;
  private String inMsg;
  private String encoding;
  private byte[] outMsgByte;
  private String outMsg;
  private String inTxnCode;
  private String inTxnLog;
  private String inCode;
  private String inErrMsg;
  private String outTxnLog;
  private String outCode;
  private String outErrMsg;
//  private IComplexBuffer inBuffer;
//  private IComplexBuffer outBuffer;
//  private IComplexBuffer failBuffer;
  private String serverId;
  private String methodId;
  private Object inBean;
  private Object outBean;
  private Object failBean;
  private Object attachment;
  private String inMsgTemplate;
  private String outMsgTemplate;
  
  public Context() {
  }

  public String getInMsgTemplate() {
	return inMsgTemplate;
  }

  public void setInMsgTemplate(String inMsgTemplate) {
	this.inMsgTemplate = inMsgTemplate;
  }

  public String getOutMsgTemplate() {
	return outMsgTemplate;
  }


  public void setOutMsgTemplate(String outMsgTemplate) {
	this.outMsgTemplate = outMsgTemplate;
  } 
  
  public String getSessionID() {
    return this.sessionID;
  }

  public void setSessionID(String sessionID) {
    this.sessionID = sessionID;
  }

  public Object getAttachment() {
    return this.attachment;
  }

  public void setAttachment(Object attachment) {
    this.attachment = attachment;
  }

  public Object getInBean() {
    return this.inBean;
  }

  public void setInBean(Object inBean) {
    this.inBean = inBean;
  }

  public Object getOutBean() {
    return this.outBean;
  }

  public void setOutBean(Object outBean) {
    this.outBean = outBean;
  }

  public Object getFailBean() {
    return this.failBean;
  }

  public void setFailBean(Object failBean) {
    this.failBean = failBean;
  }

//  public IComplexBuffer getFailBuffer() {
//    return this.failBuffer;
//  }
//
//  public void setFailBuffer(IComplexBuffer failBuffer) {
//    this.failBuffer = failBuffer;
//  }

  public String getInCode() {
    return this.inCode;
  }

  public void setInCode(String inCode) {
    this.inCode = inCode;
  }

  public String getInErrMsg() {
    return this.inErrMsg;
  }

  public void setInErrMsg(String inErrMsg) {
    this.inErrMsg = inErrMsg;
  }

  public boolean isSuccess() {
    return this.success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public byte[] getInMsgByte() {
    return this.inMsgByte;
  }

  public void setInMsgByte(byte[] inMsgByte) {
    this.inMsgByte = inMsgByte;
  }

  public String getInMsg() {
    return this.inMsg;
  }

  public void setInMsg(String inMsg) {
    this.inMsg = inMsg;
  }

  public String getEncoding() {
    return this.encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public byte[] getOutMsgByte() {
    return this.outMsgByte;
  }

  public void setOutMsgByte(byte[] outMsgByte) {
    this.outMsgByte = outMsgByte;
  }

  public String getOutMsg() {
    return this.outMsg;
  }

  public void setOutMsg(String outMsg) {
    this.outMsg = outMsg;
  }

  public String getInTxnCode() {
    return this.inTxnCode;
  }

  public void setInTxnCode(String inTxnCode) {
    this.inTxnCode = inTxnCode;
  }

  public String getInTxnLog() {
    return this.inTxnLog;
  }

  public void setInTxnLog(String inTxnLog) {
    this.inTxnLog = inTxnLog;
  }

  public String getOutTxnLog() {
    return this.outTxnLog;
  }

  public void setOutTxnLog(String outTxnLog) {
    this.outTxnLog = outTxnLog;
  }

  public String getOutCode() {
    return this.outCode;
  }

  public void setOutCode(String outCode) {
    this.outCode = outCode;
  }

  public String getOutErrMsg() {
    return this.outErrMsg;
  }

  public void setOutErrMsg(String outErrMsg) {
    this.outErrMsg = outErrMsg;
  }
//
//  public IComplexBuffer getInBuffer() {
//    return this.inBuffer;
//  }
//
//  public void setInBuffer(IComplexBuffer inBuffer) {
//    this.inBuffer = inBuffer;
//  }
//
//  public IComplexBuffer getOutBuffer() {
//    return this.outBuffer;
//  }
//
//  public void setOutBuffer(IComplexBuffer outBuffer) {
//    this.outBuffer = outBuffer;
//  }

  public String getServerId() {
    return this.serverId;
  }

  public void setServerId(String serverId) {
    this.serverId = serverId;
  }

  public String getMethodId() {
    return this.methodId;
  }

  public void setMethodId(String methodId) {
    this.methodId = methodId;
  }
}
