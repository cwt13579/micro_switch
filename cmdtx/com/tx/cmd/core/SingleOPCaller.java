/*
 * Created on 2005-5-11
 *
 */
package com.tx.cmd.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tx.cmd.utils.ApplicationContextUtils;


/**
 * @author yujianjun
 *
 * Operation caller.
 */
public class SingleOPCaller {
	private final static Log log = LogFactory.getLog(SingleOPCaller.class);

	public void callOperation(BaseOperation operation, OperationContext context)
			throws Exception {
		try {
			operation.beforeProc(context);
			operation.execute(context);
			operation.afterProc(context);
		} catch (Exception ce) {
			throw ce;
		} catch (Throwable t) {
			 throw t;
		}
	}

	/**
	 * 调用以beanName指定的Operation，Operation的参数为context.
	 *
	 * @param beanName
	 *            operation's bean name
	 * @param context
	 *            输入输出参数
	 * @throws Exception
	 *             异常发生
	 */
	public static void call(String beanName, OperationContext context)
			throws Exception {
		SingleOPCaller caller = (SingleOPCaller) ApplicationContextUtils
				.getBean("singleCaller");
		BaseOperation operation = (BaseOperation) ApplicationContextUtils
				.getBean(beanName);
		try {
			caller.callOperation(operation, context);
		} catch (Exception cex) {
			throw cex;
		} finally {
		}
	}
}