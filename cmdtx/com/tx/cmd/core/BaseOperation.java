/*
 * ==================================================================
 * The Huateng Software License
 *
 * Copyright (c) 2004-2005 Huateng Software System.  All rights
 * reserved.
 * ==================================================================
 */
package com.tx.cmd.core;

import org.springframework.stereotype.Service;


/**
 * @author <a href="mailto:liu_wen@huateng.com">Liu Wen</a>
 * @version $Revision: 1.1 $
 * @date 2005-7-20
 *
 * base operation class of the operations.
 */
public abstract class BaseOperation {
	//是否需要记录日志, 缺省为不记录
	private boolean needLog = false;

	//是否需要记录失败流水，缺省为不记录
	private boolean needFailLog = false;

	/* (non-Javadoc)
	 * @see com.huateng.ebank.framework.operation.BaseOperation#beforeProc(com.huateng.ebank.framework.operation.OperationContext)
	 */
	public abstract void beforeProc(OperationContext context) throws Exception ;

	/* (non-Javadoc)
	 * @see com.huateng.ebank.framework.operation.BaseOperation#execute(com.huateng.ebank.framework.operation.OperationContext)
	 */
	public abstract void execute(OperationContext context) throws Exception ;

	/* (non-Javadoc)
	 * @see com.huateng.ebank.framework.operation.BaseOperation#afterProc(com.huateng.ebank.framework.operation.OperationContext)
	 */
	public abstract void afterProc(OperationContext context) throws Exception;

	/**
	 * @return Returns the needLog.
	 */
	public boolean isNeedLog() {
		return needLog;
	}
	/**
	 * @param needLog The needLog to set.
	 */
	public void setNeedLog(boolean needLog) {
		this.needLog = needLog;
	}

	public boolean isNeedFailLog() {
		return needFailLog;
	}

	public void setNeedFailLog(boolean needFailLog) {
		this.needFailLog = needFailLog;
	}
}